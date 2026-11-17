package com.twovago.backend.controller;

import com.twovago.backend.model.Habitacion;
import com.twovago.backend.service.HabitacionService;
import com.twovago.backend.service.SesionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;
    private final SesionService sesionService;

    public HabitacionController(HabitacionService habitacionService, SesionService sesionService) {
        this.habitacionService = habitacionService;
        this.sesionService = sesionService;
    }

    @GetMapping
    public ResponseEntity<List<Habitacion>> listarHabitaciones(
            @RequestParam(required = false) Long hotelId) {
        List<Habitacion> habitaciones = hotelId != null
                ? habitacionService.listarPorHotel(hotelId)
                : habitacionService.listarHabitaciones();
        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Habitacion>> listarDisponibles(
            @RequestParam Long hotelId,
            @RequestParam LocalDate fechaEntrada,
            @RequestParam LocalDate fechaSalida,
            @RequestParam Integer huespedes) {
        return ResponseEntity.ok(habitacionService.listarDisponibles(
                hotelId, fechaEntrada, fechaSalida, huespedes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerHabitacion(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.obtenerHabitacion(id));
    }

    @PostMapping
    public ResponseEntity<Habitacion> registrarHabitacion(
            @RequestParam Long hotelId,
            @RequestBody Habitacion habitacion,
            HttpSession session) {
        sesionService.requerirAdministrador(session);
        Habitacion nueva = habitacionService.registrarHabitacion(hotelId, habitacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizarHabitacion(
            @PathVariable Long id, @RequestBody Habitacion habitacion, HttpSession session) {
        sesionService.requerirAdministrador(session);
        return ResponseEntity.ok(habitacionService.actualizarHabitacion(id, habitacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Long id, HttpSession session) {
        sesionService.requerirAdministrador(session);
        habitacionService.desactivarHabitacion(id);
        return ResponseEntity.noContent().build();
    }
}
