
package com.twovago.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.twovago.backend.model.Usuario;

public class UsuarioResponse {

    @JsonProperty("id_usuario")
    private Long idUsuario;

    private String nombre;
    private String correo;
    private String rol;

    public UsuarioResponse(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.correo = usuario.getCorreo();
        this.rol = usuario.getRol().name();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }
}
