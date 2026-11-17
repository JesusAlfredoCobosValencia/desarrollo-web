package com.twovago.backend.service;

import com.twovago.backend.exception.ForbiddenException;
import com.twovago.backend.exception.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SesionService {

    public Long requerirUsuario(HttpSession session) {
        if (session == null) {
            throw new UnauthorizedException("Debes iniciar sesión");
        }

        Object id = session.getAttribute("usuarioId");
        if (!(id instanceof Number)) {
            throw new UnauthorizedException("Debes iniciar sesión");
        }
        return ((Number) id).longValue();
    }

    public void requerirCliente(HttpSession session) {
        requerirUsuario(session);
        String rol = (String) session.getAttribute("rol");
        if (!"CLIENTE".equals(rol)) {
            throw new ForbiddenException("Esta operación es exclusiva para clientes");
        }
    }

    public void requerirAdministrador(HttpSession session) {
        requerirUsuario(session);
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            throw new ForbiddenException("Se requiere el rol ADMIN");
        }
    }

    public void requerirPropietarioOAdministrador(Long idPropietario, HttpSession session) {
        Long idActual = requerirUsuario(session);
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol) && !idActual.equals(idPropietario)) {
            throw new ForbiddenException("No tienes permiso para consultar este recurso");
        }
    }
}
