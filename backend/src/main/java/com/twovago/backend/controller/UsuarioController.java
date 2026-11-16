package com.twovago.backend.controller;

import com.twovago.backend.dto.ActualizarPerfilRequest;
import com.twovago.backend.dto.LoginRequest;
import com.twovago.backend.dto.RegistroUsuarioRequest;
import com.twovago.backend.dto.UsuarioResponse;
import com.twovago.backend.model.Usuario;
import com.twovago.backend.service.SesionService;
import com.twovago.backend.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final SesionService sesionService;

    public UsuarioController(UsuarioService usuarioService, SesionService sesionService) {
        this.usuarioService = usuarioService;
        this.sesionService = sesionService;
    }

    @PostMapping("/auth/registro")
    public ResponseEntity<UsuarioResponse> registrarUsuario(
            @Valid @RequestBody RegistroUsuarioRequest request) {
        Usuario usuario = usuarioService.registrarUsuario(
                request.getNombre(),
                request.getCorreo(),
                request.getPassword(),
                request.getTelefono());
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioResponse(usuario));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UsuarioResponse> iniciarSesion(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest servletRequest) {
        Usuario usuario = usuarioService.autenticar(request.getCorreo(), request.getPassword());
        HttpSession session = servletRequest.getSession(true);
        servletRequest.changeSessionId();
        session.setAttribute("usuarioId", usuario.getIdUsuario());
        session.setAttribute("rol", usuario.getRol().name());
        session.setAttribute("correo", usuario.getCorreo());
        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Void> cerrarSesion(HttpSession session) {
        sesionService.requerirUsuario(session);
        session.invalidate();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuarios/perfil")
    public ResponseEntity<UsuarioResponse> consultarPerfil(HttpSession session) {
        Long id = sesionService.requerirUsuario(session);
        return ResponseEntity.ok(new UsuarioResponse(usuarioService.obtenerUsuario(id)));
    }

    @PutMapping("/usuarios/perfil")
    public ResponseEntity<UsuarioResponse> actualizarPerfil(
            @Valid @RequestBody ActualizarPerfilRequest request,
            HttpSession session) {
        Long id = sesionService.requerirUsuario(session);
        Usuario usuario = usuarioService.actualizarPerfil(id, request.getNombre(), request.getTelefono());
        return ResponseEntity.ok(new UsuarioResponse(usuario));
    }
}
