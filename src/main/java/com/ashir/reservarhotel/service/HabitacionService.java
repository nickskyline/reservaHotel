package com.ashir.reservarhotel.service;

import com.ashir.reservarhotel.Repository.HabitacionRepository;
import com.ashir.reservarhotel.entities.Cliente;
import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.model.TipoHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {
    private HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public List<Habitacion> obtenerTodasLasHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Habitacion ObtenerPorTipo(TipoHabitacion tipoHabitacion) {
        return habitacionRepository.findByTipoHabitacion(tipoHabitacion);
    }

    public boolean actualizarHabitacionPorTipo(TipoHabitacion tipoHabitacion, Habitacion habitacionActualizada) {
        Optional<Habitacion> habitacionOptional = Optional.ofNullable(habitacionRepository.findByTipoHabitacion(tipoHabitacion));

        if (habitacionOptional.isPresent()) {
            Habitacion habitacionExistente = habitacionOptional.get();

            habitacionExistente.setTipoHabitacion(habitacionActualizada.getTipoHabitacion());
            habitacionExistente.setPrecioBase(habitacionActualizada.getPrecioBase());

            habitacionRepository.save(habitacionExistente);
            return true;
        }

        return false;
    }
}
