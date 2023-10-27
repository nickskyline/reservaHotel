package com.ashir.reservarhotel.repository;

import com.ashir.reservarhotel.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
   Habitacion findByTipoHabitacion(TipoHabitacion tipoHabitacion);
}
