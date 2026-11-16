package com.twovago.backend.service;

import com.twovago.backend.dto.ReservacionRequest;
import com.twovago.backend.dto.ReservacionResponse;
import com.twovago.backend.exception.ConflictException;
import com.twovago.backend.model.*;
import com.twovago.backend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReservacionService {

    private final ReservacionRepository reservacionRepository;
    private final HabitacionRepository habitacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagoRepository pagoRepository;

    public ReservacionService(
            ReservacionRepository reservacionRepository,
            HabitacionRepository habitacionRepository,
            UsuarioRepository usuarioRepository,
            PagoRepository pagoRepository) {
        this.reservacionRepository = reservacionRepository;
        this.habitacionRepository = habitacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.pagoRepository = pagoRepository;
    }

    public ReservacionResponse crear(Long idUsuario, ReservacionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Los datos de la reservación son obligatorios");
        }
        validarFechas(request.getFechaEntrada(), request.getFechaSalida());
        if (request.getIdHabitacion() == null || request.getCantidadHuespedes() == null
                || request.getCantidadHuespedes() <= 0) {
            throw new IllegalArgumentException("Habitación y cantidad de huéspedes son obligatorias");
        }

        Habitacion habitacion = habitacionRepository.bloquearPorId(request.getIdHabitacion())
                .orElseThrow(() -> new EntityNotFoundException("Habitación no encontrada"));

        if (!Boolean.TRUE.equals(habitacion.getActiva())
                || !Boolean.TRUE.equals(habitacion.getHotel().getActivo())) {
            throw new ConflictException("La habitación o el hotel no están disponibles");
        }

        if (request.getCantidadHuespedes() > habitacion.getCapacidad()) {
            throw new IllegalArgumentException("La cantidad de huéspedes supera la capacidad de la habitación");
        }

        LocalDateTime ahora = LocalDateTime.now();
        boolean ocupada = reservacionRepository.existeBloqueoDeFechas(
                habitacion.getIdHabitacion(),
                request.getFechaEntrada(),
                request.getFechaSalida(),
                ahora,
                List.of(EstadoReservacion.PENDIENTE_PAGO, EstadoReservacion.CONFIRMADA),
                EstadoReservacion.PENDIENTE_PAGO);

        if (ocupada) {
            throw new ConflictException("La habitación ya está reservada para alguna de esas fechas");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        long noches = ChronoUnit.DAYS.between(request.getFechaEntrada(), request.getFechaSalida());
        BigDecimal total = habitacion.getPrecioNoche()
                .multiply(BigDecimal.valueOf(noches))
                .setScale(2, java.math.RoundingMode.HALF_UP);

        Reservacion reservacion = new Reservacion();
        reservacion.setFolio("TV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        reservacion.setUsuario(usuario);
        reservacion.setHabitacion(habitacion);
        reservacion.setFechaEntrada(request.getFechaEntrada());
        reservacion.setFechaSalida(request.getFechaSalida());
        reservacion.setCantidadHuespedes(request.getCantidadHuespedes());
        reservacion.setTotal(total);
        reservacion.setEstado(EstadoReservacion.PENDIENTE_PAGO);
        reservacion.setFechaCreacion(ahora);
        reservacion.setFechaLimitePago(ahora.plusMinutes(10));
        reservacion = reservacionRepository.saveAndFlush(reservacion);

        Pago pago = new Pago();
        pago.setReservacion(reservacion);
        pago.setFolioPago("PAGO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        pago.setMonto(total);
        pago.setMetodoSimulado("SIN_SELECCIONAR");
        pago.setEstado(EstadoPago.PENDIENTE);
        pago = pagoRepository.save(pago);

        return new ReservacionResponse(reservacion, pago.getIdPago());
    }

    @Transactional(readOnly = true)
    public ReservacionResponse obtener(Long id, Long idUsuario, boolean administrador) {
        Reservacion reservacion = reservacionRepository.buscarConDetalles(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservación no encontrada"));
        verificarPropiedad(reservacion, idUsuario, administrador);
        return convertir(reservacion);
    }

    public List<ReservacionResponse> misReservaciones(Long idUsuario) {
        actualizarEstados();
        List<Reservacion> lista = reservacionRepository
                .findByUsuario_IdUsuarioAndEstadoInOrderByFechaCreacionDesc(
                        idUsuario,
                        List.of(EstadoReservacion.PENDIENTE_PAGO, EstadoReservacion.CONFIRMADA));
        return lista.stream().map(this::convertir).toList();
    }

    public List<ReservacionResponse> historial(Long idUsuario) {
        actualizarEstados();
        List<Reservacion> lista = reservacionRepository
                .findByUsuario_IdUsuarioAndEstadoOrderByFechaEntradaDesc(
                        idUsuario, EstadoReservacion.COMPLETADA);
        return lista.stream().map(this::convertir).toList();
    }

    public List<ReservacionResponse> cancelaciones(Long idUsuario) {
        actualizarEstados();
        List<Reservacion> lista = reservacionRepository
                .findByUsuario_IdUsuarioAndEstadoInOrderByFechaEntradaDesc(
                        idUsuario,
                        List.of(EstadoReservacion.CANCELADA, EstadoReservacion.EXPIRADA));
        return lista.stream().map(this::convertir).toList();
    }

    public List<ReservacionResponse> todasLasReservaciones() {
        actualizarEstados();
        return reservacionRepository.findAllByOrderByFechaCreacionDesc()
                .stream().map(this::convertir).toList();
    }

    public ReservacionResponse cancelar(Long id, Long idUsuario, boolean administrador) {
        Reservacion reservacion = reservacionRepository.bloquearPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservación no encontrada"));
        verificarPropiedad(reservacion, idUsuario, administrador);

        if (reservacion.getEstado() != EstadoReservacion.CONFIRMADA) {
            throw new ConflictException("Solamente se pueden cancelar reservaciones confirmadas");
        }
        if (!LocalDate.now().isBefore(reservacion.getFechaEntrada())) {
            throw new ConflictException("La reservación ya comenzó o la fecha de entrada pasó");
        }

        reservacion.setEstado(EstadoReservacion.CANCELADA);
        reservacion.setFechaCancelacion(LocalDateTime.now());
        reservacionRepository.save(reservacion);
        return convertir(reservacion);
    }

    @Transactional
    public void actualizarEstados() {
        LocalDateTime ahora = LocalDateTime.now();
        List<Reservacion> vencidas = reservacionRepository.findByEstadoAndFechaLimitePagoLessThanEqual(
                EstadoReservacion.PENDIENTE_PAGO, ahora);

        for (Reservacion reservacion : vencidas) {
            reservacion.setEstado(EstadoReservacion.EXPIRADA);
            pagoRepository.findByReservacion_IdReservacion(reservacion.getIdReservacion())
                    .ifPresent(pago -> {
                        if (pago.getEstado() == EstadoPago.PENDIENTE) {
                            pago.setEstado(EstadoPago.EXPIRADO);
                            pagoRepository.save(pago);
                        }
                    });
            reservacionRepository.save(reservacion);
        }

        List<Reservacion> completadas = reservacionRepository.findByEstadoAndFechaSalidaLessThanEqual(
                EstadoReservacion.CONFIRMADA, LocalDate.now());
        for (Reservacion reservacion : completadas) {
            reservacion.setEstado(EstadoReservacion.COMPLETADA);
            reservacionRepository.save(reservacion);
        }
    }

    private void validarFechas(LocalDate entrada, LocalDate salida) {
        if (entrada == null || salida == null) {
            throw new IllegalArgumentException("Las fechas de entrada y salida son obligatorias");
        }
        if (!salida.isAfter(entrada)) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
        if (entrada.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de entrada no puede ser anterior al día de hoy");
        }
    }

    private void verificarPropiedad(Reservacion reservacion, Long idUsuario, boolean administrador) {
        if (!administrador && !reservacion.getUsuario().getIdUsuario().equals(idUsuario)) {
            throw new com.twovago.backend.exception.ForbiddenException(
                    "No tienes permiso para consultar esta reservación");
        }
    }

    private ReservacionResponse convertir(Reservacion reservacion) {
        Long idPago = pagoRepository.findByReservacion_IdReservacion(reservacion.getIdReservacion())
                .map(Pago::getIdPago)
                .orElse(null);
        return new ReservacionResponse(reservacion, idPago);
    }
}
