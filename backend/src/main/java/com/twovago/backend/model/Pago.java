package com.twovago.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_pago_reservacion", columnNames = "id_reservacion"),
        @UniqueConstraint(name = "uk_pago_folio", columnNames = "folio_pago")
})
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservacion", nullable = false)
    private Reservacion reservacion;

    @Column(name = "folio_pago", length = 30, nullable = false)
    private String folioPago;

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "metodo_simulado", length = 30, nullable = false)
    private String metodoSimulado = "SIN_SELECCIONAR";

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20, nullable = false)
    private EstadoPago estado = EstadoPago.PENDIENTE;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    public Pago() {
    }

    public Long getIdPago() { return idPago; }
    public void setIdPago(Long idPago) { this.idPago = idPago; }
    public Reservacion getReservacion() { return reservacion; }
    public void setReservacion(Reservacion reservacion) { this.reservacion = reservacion; }
    public String getFolioPago() { return folioPago; }
    public void setFolioPago(String folioPago) { this.folioPago = folioPago; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getMetodoSimulado() { return metodoSimulado; }
    public void setMetodoSimulado(String metodoSimulado) { this.metodoSimulado = metodoSimulado; }
    public EstadoPago getEstado() { return estado; }
    public void setEstado(EstadoPago estado) { this.estado = estado; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
}
