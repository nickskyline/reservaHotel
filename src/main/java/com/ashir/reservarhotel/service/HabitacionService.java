package com.ashir.reservarhotel.service;

import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.entities.Reserva;
import com.ashir.reservarhotel.model.TipoHabitacion;
import com.ashir.reservarhotel.repository.HabitacionRepository;
import com.ashir.reservarhotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;
    private ReservaRepository reservaRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository, ReservaRepository reservaRepository) {
        this.habitacionRepository = habitacionRepository;
        this.reservaRepository = reservaRepository;
    }

    public Habitacion crearHabitacion(TipoHabitacion tipoHabitacion, Double precioBase) {
        Habitacion habitacion = new Habitacion();
        habitacion.setTipoHabitacion(tipoHabitacion);
        habitacion.setPrecioBase(precioBase);

        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("El tipo de habitación no puede ser nulo");
        }

        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio base debe ser mayor que 0");
        }

        return habitacionRepository.save(habitacion);
    }

    public List<Habitacion> obtenerTodasLasHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Habitacion obtenerHabitacionPorId(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    public Habitacion actualizarHabitacion(Long id, TipoHabitacion tipoHabitacion, Double precioBase) {
        Habitacion habitacion = habitacionRepository.findById(id).orElse(null);
        if (habitacion != null) {

            if (tipoHabitacion == null) {
                throw new IllegalArgumentException("El tipo de habitación no puede ser nulo");
            }

            if (precioBase <= 0) {
                throw new IllegalArgumentException("El precio base debe ser mayor que 0");
            }

            habitacion.setTipoHabitacion(tipoHabitacion);
            habitacion.setPrecioBase(precioBase);
            return habitacionRepository.save(habitacion);
        }
        return null;
    }

    public void eliminarHabitacion(Long id) {
        habitacionRepository.deleteById(id);
    }

    public List<Habitacion> buscarHabitacionesDisponiblesPorFecha(LocalDate fechaReserva) {
        return habitacionRepository.findHabitacionesDisponiblesPorFecha(fechaReserva);
    }

    public List<Habitacion> buscarHabitacionesDisponiblesPorTipoYFecha(LocalDate fechaReserva, TipoHabitacion tipoHabitacion) {
        return habitacionRepository.findHabitacionesDisponiblesPorTipoYFecha(fechaReserva, tipoHabitacion);
    }

}
