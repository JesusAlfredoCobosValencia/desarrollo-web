package com.twovago.backend.controller;

import com.twovago.backend.model.Hotel;
import com.twovago.backend.service.HotelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> listarHoteles(
            @RequestParam(required = false) String ciudad) {

        List<Hotel> hoteles;

        if (ciudad != null && !ciudad.isBlank()) {
            hoteles = hotelService.buscarPorCiudad(ciudad);
        } else {
            hoteles = hotelService.listarHoteles();
        }

        return ResponseEntity.ok(hoteles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerHotel(@PathVariable Long id) {

        Hotel hotel = hotelService.obtenerHotel(id);

        return ResponseEntity.ok(hotel);
    }

    @PostMapping
    public ResponseEntity<Hotel> registrarHotel(
            @RequestBody Hotel hotel) {

        Hotel nuevoHotel = hotelService.registrarHotel(hotel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevoHotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> actualizarHotel(
            @PathVariable Long id,
            @RequestBody Hotel hotel) {

        Hotel actualizado = hotelService.actualizarHotel(id, hotel);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHotel(@PathVariable Long id) {

        hotelService.desactivarHotel(id);

        return ResponseEntity.noContent().build();
    }
}