
package com.twovago.backend.service;

import com.twovago.backend.model.RolUsuario;
import com.twovago.backend.model.Usuario;
import com.twovago.backend.repository.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario registrarUsuario(
            String nombre,
            String correo,
            String password,
            String telefono) {

      
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        String correoNormalizado = correo.trim().toLowerCase();

        if (usuarioRepository.existsByCorreo(correoNormalizado)) {
            throw new IllegalArgumentException(
                    "El correo ya se encuentra registrado");
        }

    
        Usuario usuario = new Usuario();

        usuario.setNombre(nombre.trim());
        usuario.setCorreo(correoNormalizado);
        usuario.setPasswordHash(
                passwordEncoder.encode(password));
        usuario.setTelefono(
                telefono == null || telefono.isBlank()
                        ? null
                        : telefono.trim());

        usuario.setRol(RolUsuario.CLIENTE);

        return usuarioRepository.save(usuario);
    }
}
