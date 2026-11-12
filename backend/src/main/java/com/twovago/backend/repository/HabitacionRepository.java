package com.twovago.backend.repository;

import com.twovago.backend.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    List<Habitacion> findByHotel_IdHotel(Long idHotel);

    List<Habitacion> findByHotel_IdHotelAndActivaTrue(Long idHotel);

    List<Habitacion> findByActivaTrue();
}