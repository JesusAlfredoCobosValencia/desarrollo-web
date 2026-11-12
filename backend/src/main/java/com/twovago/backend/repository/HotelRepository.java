package com.twovago.backend.repository;

import com.twovago.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCiudadIgnoreCaseAndActivoTrue(String ciudad);

    List<Hotel> findByActivoTrue();
}