package com.ashir.reservarhotel.repository;

import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.habitacion = :habitacion AND r.fechaReserva = :fechaReserva")
    List<Reserva> findByHabitacionAndFechaReserva(@Param("habitacion") Habitacion habitacion, @Param("fechaReserva") LocalDate fechaReserva);

    List<Reserva> findByFechaReserva(LocalDate fechaReserva);

}
