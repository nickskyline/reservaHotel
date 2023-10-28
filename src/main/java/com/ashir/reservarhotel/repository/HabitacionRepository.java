package com.ashir.reservarhotel.repository;

import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.entities.Reserva;
import com.ashir.reservarhotel.model.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    // Método para buscar todas las habitaciones disponibles por fecha
    @Query("SELECT h FROM Habitacion h WHERE h.id NOT IN (SELECT r.habitacion.id FROM Reserva r WHERE r.fechaReserva = :fechaReserva)")
    List<Habitacion> findHabitacionesDisponiblesPorFecha(@Param("fechaReserva") LocalDate fechaReserva);

    // Método para buscar habitaciones disponibles por tipo y fecha de reserva
    @Query("SELECT h FROM Habitacion h WHERE h.id NOT IN (SELECT r.habitacion.id FROM Reserva r WHERE r.fechaReserva = :fechaReserva) AND h.tipoHabitacion = :tipoHabitacion")
    List<Habitacion> findHabitacionesDisponiblesPorTipoYFecha(@Param("fechaReserva") LocalDate fechaReserva, @Param("tipoHabitacion") TipoHabitacion tipoHabitacion);

    List<Habitacion> findByTipoHabitacion(TipoHabitacion tipoHabitacion);
}
