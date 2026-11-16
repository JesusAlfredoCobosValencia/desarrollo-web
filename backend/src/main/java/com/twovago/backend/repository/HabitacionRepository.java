package com.twovago.backend.repository;

import com.twovago.backend.model.Habitacion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    @Override
    @EntityGraph(attributePaths = "hotel")
    Optional<Habitacion> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select h from Habitacion h join fetch h.hotel where h.idHabitacion = :id")
    Optional<Habitacion> bloquearPorId(@Param("id") Long id);

    @EntityGraph(attributePaths = "hotel")
    List<Habitacion> findByHotel_IdHotelAndActivaTrue(Long idHotel);

    @EntityGraph(attributePaths = "hotel")
    List<Habitacion> findByActivaTrueAndHotel_ActivoTrue();
}
