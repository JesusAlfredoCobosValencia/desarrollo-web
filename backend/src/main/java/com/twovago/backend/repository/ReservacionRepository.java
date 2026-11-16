package com.twovago.backend.repository;

import com.twovago.backend.model.EstadoReservacion;
import com.twovago.backend.model.Reservacion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReservacionRepository extends JpaRepository<Reservacion, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Reservacion r where r.idReservacion = :id")
    Optional<Reservacion> bloquearPorId(@Param("id") Long id);

    @EntityGraph(attributePaths = {"usuario", "habitacion", "habitacion.hotel"})
    @Query("select r from Reservacion r where r.idReservacion = :id")
    Optional<Reservacion> buscarConDetalles(@Param("id") Long id);

    @EntityGraph(attributePaths = {"usuario", "habitacion", "habitacion.hotel"})
    List<Reservacion> findByUsuario_IdUsuarioAndEstadoInOrderByFechaCreacionDesc(
            Long idUsuario, Collection<EstadoReservacion> estados);

    @EntityGraph(attributePaths = {"usuario", "habitacion", "habitacion.hotel"})
    List<Reservacion> findByUsuario_IdUsuarioAndEstadoOrderByFechaEntradaDesc(
            Long idUsuario, EstadoReservacion estado);

    @EntityGraph(attributePaths = {"usuario", "habitacion", "habitacion.hotel"})
    List<Reservacion> findByUsuario_IdUsuarioAndEstadoInOrderByFechaEntradaDesc(
            Long idUsuario, Collection<EstadoReservacion> estados);

    @Query("""
        select case when count(r) > 0 then true else false end
        from Reservacion r
        where r.habitacion.idHabitacion = :idHabitacion
          and r.estado in :estados
          and r.fechaEntrada < :fechaSalida
          and r.fechaSalida > :fechaEntrada
          and (r.estado <> :pendiente or r.fechaLimitePago > :ahora)
        """)
    boolean existeBloqueoDeFechas(
            @Param("idHabitacion") Long idHabitacion,
            @Param("fechaEntrada") LocalDate fechaEntrada,
            @Param("fechaSalida") LocalDate fechaSalida,
            @Param("ahora") LocalDateTime ahora,
            @Param("estados") Collection<EstadoReservacion> estados,
            @Param("pendiente") EstadoReservacion pendiente);

    @EntityGraph(attributePaths = {"usuario", "habitacion", "habitacion.hotel"})
    List<Reservacion> findAllByOrderByFechaCreacionDesc();

    @EntityGraph(attributePaths = {"usuario", "habitacion", "habitacion.hotel"})
    List<Reservacion> findByEstadoAndFechaLimitePagoLessThanEqual(
            EstadoReservacion estado, LocalDateTime fechaLimite);

    @EntityGraph(attributePaths = {"usuario", "habitacion", "habitacion.hotel"})
    List<Reservacion> findByEstadoAndFechaSalidaLessThanEqual(
            EstadoReservacion estado, LocalDate fechaSalida);

    @Query("""
        select case when count(r) > 0 then true else false end
        from Reservacion r
        where r.habitacion.idHabitacion = :idHabitacion
          and r.estado in :estados
          and r.fechaSalida > :hoy
          and (r.estado <> :pendiente or r.fechaLimitePago > :ahora)
        """)
    boolean existeReservacionActivaParaHabitacion(
            @Param("idHabitacion") Long idHabitacion,
            @Param("hoy") LocalDate hoy,
            @Param("ahora") LocalDateTime ahora,
            @Param("estados") Collection<EstadoReservacion> estados,
            @Param("pendiente") EstadoReservacion pendiente);

    @Query("""
        select case when count(r) > 0 then true else false end
        from Reservacion r
        where r.habitacion.hotel.idHotel = :idHotel
          and r.estado in :estados
          and r.fechaSalida > :hoy
          and (r.estado <> :pendiente or r.fechaLimitePago > :ahora)
        """)
    boolean existeReservacionActivaParaHotel(
            @Param("idHotel") Long idHotel,
            @Param("hoy") LocalDate hoy,
            @Param("ahora") LocalDateTime ahora,
            @Param("estados") Collection<EstadoReservacion> estados,
            @Param("pendiente") EstadoReservacion pendiente);

    @Modifying
    @Query("""
        update Reservacion r
        set r.estado = :nuevoEstado
        where r.estado = :estadoActual and r.fechaSalida <= :fecha
        """)
    int actualizarEstanciasCompletadas(
            @Param("estadoActual") EstadoReservacion estadoActual,
            @Param("nuevoEstado") EstadoReservacion nuevoEstado,
            @Param("fecha") LocalDate fecha);
}
