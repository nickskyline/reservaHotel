package com.ashir.reservarhotel.service;

import com.ashir.reservarhotel.entities.Cliente;
import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.entities.Reserva;
import com.ashir.reservarhotel.model.TipoHabitacion;
import com.ashir.reservarhotel.repository.ClienteRepository;
import com.ashir.reservarhotel.repository.HabitacionRepository;
import com.ashir.reservarhotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private HabitacionRepository habitacionRepository;
    private ClienteRepository clienteRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, HabitacionRepository habitacionRepository, ClienteRepository clienteRepository) {
        this.reservaRepository = reservaRepository;
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
    }

    public Reserva crearReserva(Long habitacionId, LocalDate fechaReserva, String clienteCedula) {
        Cliente cliente = clienteRepository.findByCedula(clienteCedula);

        if (cliente == null) {
            throw new RuntimeException("El cliente no existe");
        }

        if (fechaReserva.isBefore(LocalDate.now())) {
            throw new RuntimeException("La fecha de reserva no puede ser en el pasado.");
        }

        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new RuntimeException("La habitación no existe"));

        if (!esHabitacionDisponible(habitacion, fechaReserva)) {
            throw new RuntimeException("La habitación no está disponible para esa fecha.");
        }

        Reserva reserva = new Reserva();
        reserva.setFechaReserva(fechaReserva);
        double precioBase = habitacion.getPrecioBase();
        TipoHabitacion tipoHabitacion = habitacion.getTipoHabitacion();

        double precioTotal = calcularPrecioTotal(fechaReserva, precioBase, tipoHabitacion);

        reserva.setPrecioTotal(precioTotal);
        reserva.setHabitacion(habitacion);
        reserva.setCliente(cliente);

        reserva = reservaRepository.save(reserva);

        return reserva;
    }


    public Reserva obtenerReservaPorId(Long reservaId) {
        Optional<Reserva> reserva = reservaRepository.findById(reservaId);
        return reserva.orElse(null);
    }

    public Reserva actualizarReserva(Long reservaId, LocalDate nuevaFechaReserva, Long nuevaHabitacionId) {

        Reserva reservaExistente = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("La reserva no existe"));

        if (nuevaFechaReserva.isBefore(LocalDate.now())) {
            throw new RuntimeException("La fecha de reserva no puede ser en el pasado.");
        }

        if (nuevaHabitacionId != null) {

            Habitacion nuevaHabitacion = habitacionRepository.findById(nuevaHabitacionId)
                    .orElseThrow(() -> new RuntimeException("La nueva habitación no existe"));


            if (!nuevaHabitacion.equals(reservaExistente.getHabitacion())) {

                if (!esHabitacionDisponible(nuevaHabitacion, nuevaFechaReserva)) {
                    throw new RuntimeException("La nueva habitación no está disponible para la fecha seleccionada.");
                }
                reservaExistente.setHabitacion(nuevaHabitacion);
            }
        }

        double precioBase = reservaExistente.getHabitacion().getPrecioBase();
        TipoHabitacion tipoHabitacion = reservaExistente.getHabitacion().getTipoHabitacion();
        double nuevoPrecioTotal = calcularPrecioTotal(nuevaFechaReserva, precioBase, tipoHabitacion);

        reservaExistente.setFechaReserva(nuevaFechaReserva);
        reservaExistente.setPrecioTotal(nuevoPrecioTotal);

        reservaRepository.save(reservaExistente);
        return reservaExistente;
    }


    public void eliminarReserva(Long reservaId) {
        reservaRepository.deleteById(reservaId);
    }

    private double calcularPrecioTotal(LocalDate fechaReserva, double precioBase, TipoHabitacion tipoHabitacion) {
        LocalDate currentDate = LocalDate.now();
        long diasDeAnticipacion = currentDate.until(fechaReserva).getDays();

        double precioTotal;
        if (diasDeAnticipacion > 15) {
            precioTotal = 0.8 * precioBase; // Aplicar descuento del 20%
        } else {
            precioTotal = precioBase;
        }

        if (tipoHabitacion == TipoHabitacion.PREMIUM) {
            precioTotal *= 0.95; // Aplicar descuento adicional del 5% para habitaciones premium
        }

        return precioTotal;
    }

    private boolean esHabitacionDisponible(Habitacion habitacion, LocalDate fechaReserva) {
        List<Reserva> reservas = reservaRepository.findByHabitacionAndFechaReserva(habitacion, fechaReserva);


        for (Reserva reserva : reservas) {
            if (fechaReserva.isEqual(reserva.getFechaReserva())) {
                return false;
            }

        }
        return true;
    }
}
