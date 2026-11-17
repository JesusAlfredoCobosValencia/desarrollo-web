package com.twovago.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.twovago.backend.model.Reservacion;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservacionResponse {

    @JsonProperty("id_reservacion")
    private Long idReservacion;
    private String folio;
    @JsonProperty("id_usuario")
    private Long idUsuario;
    @JsonProperty("id_habitacion")
    private Long idHabitacion;
    @JsonProperty("nombre_hotel")
    private String nombreHotel;
    private String ciudad;
    @JsonProperty("numero_habitacion")
    private String numeroHabitacion;
    @JsonProperty("tipo_habitacion")
    private String tipoHabitacion;
    @JsonProperty("fecha_entrada")
    private LocalDate fechaEntrada;
    @JsonProperty("fecha_salida")
    private LocalDate fechaSalida;
    @JsonProperty("cantidad_huespedes")
    private Integer cantidadHuespedes;
    private BigDecimal total;
    private String estado;
    @JsonProperty("fecha_creacion")
    private LocalDateTime fechaCreacion;
    @JsonProperty("fecha_limite_pago")
    private LocalDateTime fechaLimitePago;
    @JsonProperty("fecha_cancelacion")
    private LocalDateTime fechaCancelacion;
    @JsonProperty("id_pago")
    private Long idPago;

    public ReservacionResponse(Reservacion r, Long idPago) {
        this.idReservacion = r.getIdReservacion();
        this.folio = r.getFolio();
        this.idUsuario = r.getUsuario().getIdUsuario();
        this.idHabitacion = r.getHabitacion().getIdHabitacion();
        this.nombreHotel = r.getHabitacion().getHotel().getNombre();
        this.ciudad = r.getHabitacion().getHotel().getCiudad();
        this.numeroHabitacion = r.getHabitacion().getNumero();
        this.tipoHabitacion = r.getHabitacion().getTipo();
        this.fechaEntrada = r.getFechaEntrada();
        this.fechaSalida = r.getFechaSalida();
        this.cantidadHuespedes = r.getCantidadHuespedes();
        this.total = r.getTotal();
        this.estado = r.getEstado().name();
        this.fechaCreacion = r.getFechaCreacion();
        this.fechaLimitePago = r.getFechaLimitePago();
        this.fechaCancelacion = r.getFechaCancelacion();
        this.idPago = idPago;
    }

    public Long getIdReservacion() { return idReservacion; }
    public String getFolio() { return folio; }
    public Long getIdUsuario() { return idUsuario; }
    public Long getIdHabitacion() { return idHabitacion; }
    public String getNombreHotel() { return nombreHotel; }
    public String getCiudad() { return ciudad; }
    public String getNumeroHabitacion() { return numeroHabitacion; }
    public String getTipoHabitacion() { return tipoHabitacion; }
    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public Integer getCantidadHuespedes() { return cantidadHuespedes; }
    public BigDecimal getTotal() { return total; }
    public String getEstado() { return estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaLimitePago() { return fechaLimitePago; }
    public LocalDateTime getFechaCancelacion() { return fechaCancelacion; }
    public Long getIdPago() { return idPago; }
}
