package com.twovago.backend.service;

import com.twovago.backend.model.Hotel;
import com.twovago.backend.repository.HotelRepository;
import com.twovago.backend.repository.ReservacionRepository;
import com.twovago.backend.model.EstadoReservacion;
import com.twovago.backend.exception.ConflictException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ReservacionRepository reservacionRepository;

    public HotelService(HotelRepository hotelRepository, ReservacionRepository reservacionRepository) {
        this.hotelRepository = hotelRepository;
        this.reservacionRepository = reservacionRepository;
    }

    @Transactional(readOnly = true)
    public List<Hotel> listarHoteles() {
        return hotelRepository.findByActivoTrue();
    }

    @Transactional(readOnly = true)
    public List<Hotel> buscarPorCiudad(String ciudad) {
        if (ciudad == null || ciudad.isBlank()) {
            return listarHoteles();
        }
        return hotelRepository.findByCiudadIgnoreCaseAndActivoTrue(ciudad.trim());
    }

    @Transactional(readOnly = true)
    public Hotel obtenerHotel(Long id) {
        return hotelRepository.findById(id)
                .filter(hotel -> Boolean.TRUE.equals(hotel.getActivo()))
                .orElseThrow(() -> new EntityNotFoundException("Hotel no encontrado"));
    }

    public Hotel registrarHotel(Hotel hotel) {
        validarHotel(hotel);
        hotel.setIdHotel(null);
        hotel.setActivo(true);
        hotel.setNombre(hotel.getNombre().trim());
        hotel.setCiudad(hotel.getCiudad().trim());
        hotel.setDireccion(hotel.getDireccion().trim());
        return hotelRepository.save(hotel);
    }

    public Hotel actualizarHotel(Long id, Hotel datos) {
        Hotel existente = obtenerHotel(id);
        validarHotel(datos);
        existente.setNombre(datos.getNombre().trim());
        existente.setCiudad(datos.getCiudad().trim());
        existente.setDireccion(datos.getDireccion().trim());
        existente.setDescripcion(datos.getDescripcion());
        existente.setCategoria(datos.getCategoria());
        existente.setImagenUrl(datos.getImagenUrl());
        existente.setServicios(datos.getServicios());
        return hotelRepository.save(existente);
    }

    public void desactivarHotel(Long id) {
        Hotel hotel = obtenerHotel(id);
        boolean tieneReservas = reservacionRepository.existeReservacionActivaParaHotel(
                id,
                LocalDate.now(),
                LocalDateTime.now(),
                List.of(EstadoReservacion.PENDIENTE_PAGO, EstadoReservacion.CONFIRMADA),
                EstadoReservacion.PENDIENTE_PAGO);
        if (tieneReservas) {
            throw new ConflictException("No se puede desactivar el hotel porque tiene reservaciones vigentes");
        }
        hotel.setActivo(false);
        hotelRepository.save(hotel);
    }

    private void validarHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Los datos del hotel son obligatorios");
        }
        if (hotel.getNombre() == null || hotel.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del hotel es obligatorio");
        }
        if (hotel.getCiudad() == null || hotel.getCiudad().isBlank()) {
            throw new IllegalArgumentException("La ciudad es obligatoria");
        }
        if (hotel.getDireccion() == null || hotel.getDireccion().isBlank()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (hotel.getCategoria() != null && (hotel.getCategoria() < 1 || hotel.getCategoria() > 5)) {
            throw new IllegalArgumentException("La categoría debe estar entre 1 y 5");
        }
    }
}
