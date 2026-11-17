package com.twovago.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.twovago.backend.model.Pago;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoResponse {

    @JsonProperty("id_pago")
    private Long idPago;
    @JsonProperty("folio_pago")
    private String folioPago;
    private BigDecimal monto;
    @JsonProperty("metodo_simulado")
    private String metodoSimulado;
    private String estado;
    @JsonProperty("fecha_pago")
    private LocalDateTime fechaPago;
    @JsonProperty("id_reservacion")
    private Long idReservacion;
    @JsonProperty("folio_reservacion")
    private String folioReservacion;
    private String mensaje;

    public PagoResponse(Pago pago, String mensaje) {
        this.idPago = pago.getIdPago();
        this.folioPago = pago.getFolioPago();
        this.monto = pago.getMonto();
        this.metodoSimulado = pago.getMetodoSimulado();
        this.estado = pago.getEstado().name();
        this.fechaPago = pago.getFechaPago();
        this.idReservacion = pago.getReservacion().getIdReservacion();
        this.folioReservacion = pago.getReservacion().getFolio();
        this.mensaje = mensaje;
    }

    public Long getIdPago() { return idPago; }
    public String getFolioPago() { return folioPago; }
    public BigDecimal getMonto() { return monto; }
    public String getMetodoSimulado() { return metodoSimulado; }
    public String getEstado() { return estado; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public Long getIdReservacion() { return idReservacion; }
    public String getFolioReservacion() { return folioReservacion; }
    public String getMensaje() { return mensaje; }
}
