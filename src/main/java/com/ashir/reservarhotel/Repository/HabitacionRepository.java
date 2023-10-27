package com.ashir.reservarhotel.Repository;

import com.ashir.reservarhotel.entities.Cliente;
import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.model.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
  Habitacion findByTipoHabitacion(TipoHabitacion tipoHabitacion);

}
