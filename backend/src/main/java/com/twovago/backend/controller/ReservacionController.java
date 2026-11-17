package com.twovago.backend.controller;

import com.twovago.backend.dto.ReservacionRequest;
import com.twovago.backend.dto.ReservacionResponse;
import com.twovago.backend.service.ReservacionService;
import com.twovago.backend.service.SesionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    private final ReservacionService reservacionService;
    private final SesionService sesionService;

    public ReservacionController(ReservacionService reservacionService, SesionService sesionService) {
        this.reservacionService = reservacionService;
        this.sesionService = sesionService;
    }

    @PostMapping
    public ResponseEntity<ReservacionResponse> crear(
            @Valid @RequestBody ReservacionRequest request,
            HttpSession session) {
        sesionService.requerirCliente(session);
        Long idUsuario = sesionService.requerirUsuario(session);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservacionService.crear(idUsuario, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservacionResponse> obtener(
            @PathVariable Long id,
            HttpSession session) {
        Long idUsuario = sesionService.requerirUsuario(session);
        boolean admin = "ADMIN".equals(session.getAttribute("rol"));
        return ResponseEntity.ok(reservacionService.obtener(id, idUsuario, admin));
    }

    @GetMapping("/mis-reservas")
    public ResponseEntity<List<ReservacionResponse>> misReservas(HttpSession session) {
        Long idUsuario = sesionService.requerirUsuario(session);
        return ResponseEntity.ok(reservacionService.misReservaciones(idUsuario));
    }

    @GetMapping("/historial")
    public ResponseEntity<List<ReservacionResponse>> historial(HttpSession session) {
        Long idUsuario = sesionService.requerirUsuario(session);
        return ResponseEntity.ok(reservacionService.historial(idUsuario));
    }

    @GetMapping("/cancelaciones")
    public ResponseEntity<List<ReservacionResponse>> cancelaciones(HttpSession session) {
        Long idUsuario = sesionService.requerirUsuario(session);
        return ResponseEntity.ok(reservacionService.cancelaciones(idUsuario));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ReservacionResponse>> todas(HttpSession session) {
        sesionService.requerirAdministrador(session);
        return ResponseEntity.ok(reservacionService.todasLasReservaciones());
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ReservacionResponse> cancelar(
            @PathVariable Long id,
            HttpSession session) {
        Long idUsuario = sesionService.requerirUsuario(session);
        boolean admin = "ADMIN".equals(session.getAttribute("rol"));
        return ResponseEntity.ok(reservacionService.cancelar(id, idUsuario, admin));
    }
}
