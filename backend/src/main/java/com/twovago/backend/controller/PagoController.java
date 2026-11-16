package com.twovago.backend.controller;

import com.twovago.backend.dto.ConfirmarPagoRequest;
import com.twovago.backend.dto.PagoResponse;
import com.twovago.backend.service.PagoService;
import com.twovago.backend.service.SesionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;
    private final SesionService sesionService;

    public PagoController(PagoService pagoService, SesionService sesionService) {
        this.pagoService = pagoService;
        this.sesionService = sesionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponse> consultar(@PathVariable Long id, HttpSession session) {
        Long idUsuario = sesionService.requerirUsuario(session);
        boolean admin = "ADMIN".equals(session.getAttribute("rol"));
        return ResponseEntity.ok(pagoService.consultar(id, idUsuario, admin));
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<PagoResponse> confirmar(
            @PathVariable Long id,
            @Valid @RequestBody ConfirmarPagoRequest request,
            HttpSession session) {
        sesionService.requerirCliente(session);
        Long idUsuario = sesionService.requerirUsuario(session);
        PagoResponse respuesta = pagoService.confirmar(id, idUsuario, request.getMetodoSimulado());
        if ("EXPIRADO".equals(respuesta.getEstado())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }
        return ResponseEntity.ok(respuesta);
    }
}
