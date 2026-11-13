package com.twovago.backend.repository;

import com.twovago.backend.model.Habitacion;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    @Override
    @EntityGraph(attributePaths = "hotel")
    Optional<Habitacion> findById(Long id);

    @EntityGraph(attributePaths = "hotel")
    List<Habitacion> findByHotel_IdHotel(Long idHotel);

    @EntityGraph(attributePaths = "hotel")
    List<Habitacion> findByHotel_IdHotelAndActivaTrue(Long idHotel);

    @EntityGraph(attributePaths = "hotel")
    List<Habitacion> findByActivaTrue();
}