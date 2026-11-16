package com.twovago.backend.service;

import com.twovago.backend.dto.PagoResponse;
import com.twovago.backend.exception.ConflictException;
import com.twovago.backend.exception.ForbiddenException;
import com.twovago.backend.model.*;
import com.twovago.backend.repository.PagoRepository;
import com.twovago.backend.repository.ReservacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional
public class PagoService {

    private static final Set<String> METODOS_PERMITIDOS = Set.of(
            "TARJETA_SIMULADA",
            "TRANSFERENCIA_SIMULADA",
            "EFECTIVO_SIMULADO"
    );

    private final PagoRepository pagoRepository;
    private final ReservacionRepository reservacionRepository;

    public PagoService(PagoRepository pagoRepository, ReservacionRepository reservacionRepository) {
        this.pagoRepository = pagoRepository;
        this.reservacionRepository = reservacionRepository;
    }

    @Transactional(readOnly = true)
    public PagoResponse consultar(Long idPago, Long idUsuario, boolean administrador) {
        Pago pago = pagoRepository.findByIdPago(idPago)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado"));
        if (!administrador && !pago.getReservacion().getUsuario().getIdUsuario().equals(idUsuario)) {
            throw new ForbiddenException("No tienes permiso para consultar este pago");
        }
        return new PagoResponse(pago, "Consulta de pago realizada");
    }

    public PagoResponse confirmar(Long idPago, Long idUsuario, String metodoSimulado) {
        Pago pago = pagoRepository.findByIdPago(idPago)
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado"));

        Reservacion reservacion = reservacionRepository.bloquearPorId(
                pago.getReservacion().getIdReservacion())
                .orElseThrow(() -> new EntityNotFoundException("Reservación no encontrada"));

        if (!reservacion.getUsuario().getIdUsuario().equals(idUsuario)) {
            throw new ForbiddenException("No tienes permiso para confirmar este pago");
        }
        if (reservacion.getEstado() != EstadoReservacion.PENDIENTE_PAGO
                || pago.getEstado() != EstadoPago.PENDIENTE) {
            throw new ConflictException("El pago ya no se encuentra pendiente");
        }

        String metodo = metodoSimulado == null ? "" : metodoSimulado.trim().toUpperCase();
        if (!METODOS_PERMITIDOS.contains(metodo)) {
            throw new IllegalArgumentException(
                    "Método inválido. Usa TARJETA_SIMULADA, TRANSFERENCIA_SIMULADA o EFECTIVO_SIMULADO");
        }

        LocalDateTime ahora = LocalDateTime.now();
        if (!ahora.isBefore(reservacion.getFechaLimitePago())) {
            reservacion.setEstado(EstadoReservacion.EXPIRADA);
            pago.setEstado(EstadoPago.EXPIRADO);
            reservacionRepository.save(reservacion);
            pagoRepository.save(pago);
            return new PagoResponse(pago, "El plazo de 10 minutos terminó. La reservación expiró");
        }

        pago.setMetodoSimulado(metodo);
        pago.setEstado(EstadoPago.APROBADO);
        pago.setFechaPago(ahora);
        reservacion.setEstado(EstadoReservacion.CONFIRMADA);
        reservacionRepository.save(reservacion);
        pago = pagoRepository.save(pago);

        return new PagoResponse(pago, "Pago simulado confirmado");
    }
}
