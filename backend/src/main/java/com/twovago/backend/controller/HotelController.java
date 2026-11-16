package com.twovago.backend.controller;

import com.twovago.backend.model.Hotel;
import com.twovago.backend.service.HotelService;
import com.twovago.backend.service.SesionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
public class HotelController {

    private final HotelService hotelService;
    private final SesionService sesionService;

    public HotelController(HotelService hotelService, SesionService sesionService) {
        this.hotelService = hotelService;
        this.sesionService = sesionService;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> listarHoteles(
            @RequestParam(required = false) String ciudad) {
        List<Hotel> hoteles = (ciudad != null && !ciudad.isBlank())
                ? hotelService.buscarPorCiudad(ciudad)
                : hotelService.listarHoteles();
        return ResponseEntity.ok(hoteles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerHotel(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.obtenerHotel(id));
    }

    @PostMapping
    public ResponseEntity<Hotel> registrarHotel(
            @RequestBody Hotel hotel, HttpSession session) {
        sesionService.requerirAdministrador(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.registrarHotel(hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> actualizarHotel(
            @PathVariable Long id, @RequestBody Hotel hotel, HttpSession session) {
        sesionService.requerirAdministrador(session);
        return ResponseEntity.ok(hotelService.actualizarHotel(id, hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHotel(@PathVariable Long id, HttpSession session) {
        sesionService.requerirAdministrador(session);
        hotelService.desactivarHotel(id);
        return ResponseEntity.noContent().build();
    }
}
