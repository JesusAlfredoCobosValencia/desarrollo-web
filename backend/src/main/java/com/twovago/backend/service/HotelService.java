package com.twovago.backend.service;

import com.twovago.backend.model.Hotel;
import com.twovago.backend.repository.HotelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
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
                .orElseThrow(() ->
                        new EntityNotFoundException("Hotel no encontrado"));
    }

    public Hotel registrarHotel(Hotel hotel) {
        hotel.setIdHotel(null);
        hotel.setActivo(true);
        return hotelRepository.save(hotel);
    }

    public Hotel actualizarHotel(Long id, Hotel datos) {
        Hotel existente = obtenerHotel(id);

        existente.setNombre(datos.getNombre());
        existente.setCiudad(datos.getCiudad());
        existente.setDireccion(datos.getDireccion());
        existente.setDescripcion(datos.getDescripcion());
        existente.setCategoria(datos.getCategoria());
        existente.setImagenUrl(datos.getImagenUrl());
        existente.setServicios(datos.getServicios());

        return hotelRepository.save(existente);
    }
    
    public void desactivarHotel(Long id) {
        Hotel hotel = obtenerHotel(id);
        hotel.setActivo(false);
        hotelRepository.save(hotel);
    }
}