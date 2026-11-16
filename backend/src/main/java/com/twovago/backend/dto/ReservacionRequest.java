package com.twovago.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class ReservacionRequest {

    @NotNull(message = "El identificador de habitación es obligatorio")
    @Positive(message = "El identificador de habitación debe ser mayor que cero")
    @JsonProperty("id_habitacion")
    private Long idHabitacion;

    @NotNull(message = "La fecha de entrada es obligatoria")
    @JsonProperty("fecha_entrada")
    private LocalDate fechaEntrada;

    @NotNull(message = "La fecha de salida es obligatoria")
    @JsonProperty("fecha_salida")
    private LocalDate fechaSalida;

    @NotNull(message = "La cantidad de huéspedes es obligatoria")
    @Positive(message = "La cantidad de huéspedes debe ser mayor que cero")
    @JsonProperty("cantidad_huespedes")
    private Integer cantidadHuespedes;

    public Long getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(Long idHabitacion) { this.idHabitacion = idHabitacion; }
    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public void setFechaEntrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public Integer getCantidadHuespedes() { return cantidadHuespedes; }
    public void setCantidadHuespedes(Integer cantidadHuespedes) { this.cantidadHuespedes = cantidadHuespedes; }
}
