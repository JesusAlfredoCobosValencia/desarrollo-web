package com.twovago.backend.service;

import com.twovago.backend.exception.ConflictException;
import com.twovago.backend.exception.UnauthorizedException;
import com.twovago.backend.model.RolUsuario;
import com.twovago.backend.model.Usuario;
import com.twovago.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(String nombre, String correo, String password, String telefono) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (password.length() < 8 || password.length() > 72) {
            throw new IllegalArgumentException("La contraseña debe tener entre 8 y 72 caracteres");
        }

        String correoNormalizado = correo.trim().toLowerCase();
        if (usuarioRepository.existsByCorreo(correoNormalizado)) {
            throw new ConflictException("El correo ya se encuentra registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre.trim());
        usuario.setCorreo(correoNormalizado);
        usuario.setPasswordHash(passwordEncoder.encode(password));
        usuario.setTelefono(telefono == null || telefono.isBlank() ? null : telefono.trim());
        usuario.setRol(RolUsuario.CLIENTE);
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario autenticar(String correo, String password) {
        String normalizado = correo == null ? "" : correo.trim().toLowerCase();
        Usuario usuario = usuarioRepository.findByCorreo(normalizado)
                .orElseThrow(() -> new UnauthorizedException("Correo o contraseña incorrectos"));

        if (password == null || !passwordEncoder.matches(password, usuario.getPasswordHash())) {
            throw new UnauthorizedException("Correo o contraseña incorrectos");
        }
        return usuario;
    }

    @Transactional(readOnly = true)
    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    public Usuario actualizarPerfil(Long id, String nombre, String telefono) {
        Usuario usuario = obtenerUsuario(id);
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        usuario.setNombre(nombre.trim());
        usuario.setTelefono(telefono == null || telefono.isBlank() ? null : telefono.trim());
        return usuarioRepository.save(usuario);
    }
}
