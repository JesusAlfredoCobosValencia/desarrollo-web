package com.twovago.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.twovago.backend.model.Usuario;
import java.time.LocalDateTime;

public class UsuarioResponse {

    @JsonProperty("id_usuario")
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private String rol;
    @JsonProperty("fecha_registro")
    private LocalDateTime fechaRegistro;

    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.telefono = usuario.getTelefono();
        this.rol = usuario.getRol().name();
        this.fechaRegistro = usuario.getFechaRegistro();
    }

    public Long getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getRol() { return rol; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
}
