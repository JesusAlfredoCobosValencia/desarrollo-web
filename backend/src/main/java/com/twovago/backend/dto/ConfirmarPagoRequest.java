package com.twovago.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class ConfirmarPagoRequest {

    @NotBlank(message = "El método de pago simulado es obligatorio")
    @JsonProperty("metodo_simulado")
    private String metodoSimulado;

    public String getMetodoSimulado() { return metodoSimulado; }
    public void setMetodoSimulado(String metodoSimulado) { this.metodoSimulado = metodoSimulado; }
}
