package com.twovago.backend.controller;

import com.twovago.backend.model.Habitacion;
import com.twovago.backend.service.HabitacionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public ResponseEntity<List<Habitacion>> listarHabitaciones(
            @RequestParam(required = false) Long hotelId) {

        List<Habitacion> habitaciones;

        if (hotelId != null) {
            habitaciones = habitacionService.listarPorHotel(hotelId);
        } else {
            habitaciones = habitacionService.listarHabitaciones();
        }

        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerHabitacion(
            @PathVariable Long id) {

        Habitacion habitacion = habitacionService.obtenerHabitacion(id);

        return ResponseEntity.ok(habitacion);
    }

    @PostMapping
    public ResponseEntity<Habitacion> registrarHabitacion(
            @RequestParam Long hotelId,
            @RequestBody Habitacion habitacion) {

        Habitacion nueva = habitacionService.registrarHabitacion(
                hotelId, habitacion);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizarHabitacion(
            @PathVariable Long id,
            @RequestBody Habitacion habitacion) {

        Habitacion actualizada =
                habitacionService.actualizarHabitacion(id, habitacion);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(
            @PathVariable Long id) {

        habitacionService.desactivarHabitacion(id);

        return ResponseEntity.noContent().build();
    }
}