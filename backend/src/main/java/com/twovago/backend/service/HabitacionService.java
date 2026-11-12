package com.twovago.backend.service;

import com.twovago.backend.model.Habitacion;
import com.twovago.backend.model.Hotel;
import com.twovago.backend.repository.HabitacionRepository;
import com.twovago.backend.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final HotelRepository hotelRepository;

    public HabitacionService(
            HabitacionRepository habitacionRepository,
            HotelRepository hotelRepository) {
        this.habitacionRepository = habitacionRepository;
        this.hotelRepository = hotelRepository;
    }

    @Transactional(readOnly = true)
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findByActivaTrue();
    }

    @Transactional(readOnly = true)
    public List<Habitacion> listarPorHotel(Long idHotel) {
        obtenerHotelActivo(idHotel);
        return habitacionRepository.findByHotel_IdHotelAndActivaTrue(idHotel);
    }

    @Transactional(readOnly = true)
    public Habitacion obtenerHabitacion(Long id) {
        return habitacionRepository.findById(id)
                .filter(h -> Boolean.TRUE.equals(h.getActiva()))
                .orElseThrow(() ->
                        new EntityNotFoundException("Habitación no encontrada"));
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

        existente.setNumero(datos.getNumero());
        existente.setTipo(datos.getTipo());
        existente.setCapacidad(datos.getCapacidad());
        existente.setPrecioNoche(datos.getPrecioNoche());
        existente.setDescripcion(datos.getDescripcion());

        return habitacionRepository.save(existente);
    }

    public void desactivarHabitacion(Long id) {

        Habitacion habitacion = obtenerHabitacion(id);
        habitacion.setActiva(false);

        habitacionRepository.save(habitacion);
    }

    private Hotel obtenerHotelActivo(Long idHotel) {

        return hotelRepository.findById(idHotel)
                .filter(h -> Boolean.TRUE.equals(h.getActivo()))
                .orElseThrow(() ->
                        new EntityNotFoundException("Hotel no encontrado o inactivo"));
    }

    private void validarHabitacion(Habitacion habitacion) {

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