package com.twovago.backend.service;

import com.twovago.backend.model.*;
import com.twovago.backend.repository.HabitacionRepository;
import com.twovago.backend.repository.HotelRepository;
import com.twovago.backend.repository.ReservacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;
    private final ReservacionRepository reservacionRepository;

    public HabitacionService(
            HabitacionRepository habitacionRepository,
            HotelRepository hotelRepository,
            ReservacionRepository reservacionRepository) {
        this.habitacionRepository = habitacionRepository;
        this.hotelRepository = hotelRepository;
        this.reservacionRepository = reservacionRepository;
    }

    @Transactional(readOnly = true)
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findByActivaTrueAndHotel_ActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Habitacion> listarPorHotel(Long idHotel) {
        obtenerHotelActivo(idHotel);
        return habitacionRepository.findByHotel_IdHotelAndActivaTrue(idHotel);
    }

    @Transactional(readOnly = true)
    public List<Habitacion> listarDisponibles(
            Long idHotel, LocalDate entrada, LocalDate salida, Integer huespedes) {
        if (idHotel == null || entrada == null || salida == null || huespedes == null || huespedes <= 0) {
            throw new IllegalArgumentException("Hotel, fechas y cantidad de huéspedes son obligatorios");
        }
        obtenerHotelActivo(idHotel);
        if (!salida.isAfter(entrada)) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada");
        }
        if (entrada.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de entrada no puede ser anterior al día de hoy");
        }

        List<Habitacion> candidatas = habitacionRepository.findByHotel_IdHotelAndActivaTrue(idHotel);
        LocalDateTime ahora = LocalDateTime.now();

        return candidatas.stream()
                .filter(h -> Boolean.TRUE.equals(h.getHotel().getActivo()))
                .filter(h -> h.getCapacidad() != null && h.getCapacidad() >= huespedes)
                .filter(h -> !reservacionRepository.existeBloqueoDeFechas(
                        h.getIdHabitacion(),
                        entrada,
                        salida,
                        ahora,
                        List.of(EstadoReservacion.PENDIENTE_PAGO, EstadoReservacion.CONFIRMADA),
                        EstadoReservacion.PENDIENTE_PAGO))
                .toList();
    }

    @Transactional(readOnly = true)
    public Habitacion obtenerHabitacion(Long id) {
        return habitacionRepository.findById(id)
                .filter(h -> Boolean.TRUE.equals(h.getActiva()))
                .filter(h -> Boolean.TRUE.equals(h.getHotel().getActivo()))
                .orElseThrow(() -> new EntityNotFoundException("Habitación no encontrada"));
    }

    public Habitacion registrarHabitacion(Long idHotel, Habitacion habitacion) {
        Hotel hotel = obtenerHotelActivo(idHotel);
        validarHabitacion(habitacion);
        habitacion.setIdHabitacion(null);
        habitacion.setHotel(hotel);
        habitacion.setActiva(true);
        return habitacionRepository.save(habitacion);
    }

    public Habitacion actualizarHabitacion(Long id, Habitacion datos) {
        Habitacion existente = obtenerHabitacion(id);
        validarHabitacion(datos);
        existente.setNumero(datos.getNumero().trim());
        existente.setTipo(datos.getTipo().trim());
        existente.setCapacidad(datos.getCapacidad());
        existente.setPrecioNoche(datos.getPrecioNoche());
        existente.setDescripcion(datos.getDescripcion());
        return habitacionRepository.save(existente);
    }

    public void desactivarHabitacion(Long id) {
        Habitacion habitacion = obtenerHabitacion(id);
        boolean tieneReservas = reservacionRepository.existeReservacionActivaParaHabitacion(
                id,
                LocalDate.now(),
                LocalDateTime.now(),
                List.of(EstadoReservacion.PENDIENTE_PAGO, EstadoReservacion.CONFIRMADA),
                EstadoReservacion.PENDIENTE_PAGO);
        if (tieneReservas) {
            throw new com.twovago.backend.exception.ConflictException(
                    "No se puede desactivar la habitación porque tiene reservaciones vigentes");
        }
        habitacion.setActiva(false);
        habitacionRepository.save(habitacion);
    }

    private Hotel obtenerHotelActivo(Long idHotel) {
        return hotelRepository.findById(idHotel)
                .filter(h -> Boolean.TRUE.equals(h.getActivo()))
                .orElseThrow(() -> new EntityNotFoundException("Hotel no encontrado o inactivo"));
    }

    private void validarHabitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("Los datos de la habitación son obligatorios");
        }
        if (habitacion.getNumero() == null || habitacion.getNumero().isBlank()) {
            throw new IllegalArgumentException("El número de habitación es obligatorio");
        }
        if (habitacion.getTipo() == null || habitacion.getTipo().isBlank()) {
            throw new IllegalArgumentException("El tipo de habitación es obligatorio");
        }
        if (habitacion.getCapacidad() == null || habitacion.getCapacidad() <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
        }
        BigDecimal precio = habitacion.getPrecioNoche();
        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio por noche debe ser mayor que cero");
        }
    }
}
