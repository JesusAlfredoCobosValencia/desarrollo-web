package com.twovago.backend.repository;

import com.twovago.backend.model.Pago;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    @EntityGraph(attributePaths = {"reservacion", "reservacion.usuario", "reservacion.habitacion", "reservacion.habitacion.hotel"})
    Optional<Pago> findByIdPago(Long idPago);

    @EntityGraph(attributePaths = {"reservacion", "reservacion.usuario", "reservacion.habitacion", "reservacion.habitacion.hotel"})
    Optional<Pago> findByReservacion_IdReservacion(Long idReservacion);
}
