
package com.twovago.backend.controller;

import com.twovago.backend.dto.RegistroUsuarioRequest;
import com.twovago.backend.dto.UsuarioResponse;
import com.twovago.backend.model.Usuario;
import com.twovago.backend.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(
            @Valid @RequestBody RegistroUsuarioRequest request) {

        try {
            Usuario usuario = usuarioService.registrarUsuario(
                    request.getNombre(),
                    request.getCorreo(),
                    request.getPassword(),
                    request.getTelefono()
            );

            UsuarioResponse respuesta = new UsuarioResponse(usuario);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(respuesta);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("mensaje", e.getMessage()));
        }
    }
}
