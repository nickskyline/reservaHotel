<<<<<<< HEAD
package com.ashir.reservarhotel.service;

public class ReservaService {
}
=======
package com.ashir.reservarhotel.service;

import com.ashir.reservarhotel.entities.Reserva;
import com.ashir.reservarhotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository; // Asegúrate de inyectar el repositorio de reservas

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarReservasPorCliente(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }


    public boolean actualizarReserva(String codigoReserva, Reserva reservaActualizada) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(reservaActualizada.getId());

        if (reservaOptional.isPresent()) {
            Reserva reservaExistente = reservaOptional.get();

            // Actualiza los atributos de la reserva según sea necesario
            reservaExistente.setFechaReserva(reservaActualizada.getFechaReserva());
            reservaExistente.setPrecioTotal(reservaActualizada.getPrecioTotal());
            reservaExistente.setId(reservaActualizada.getId());
            reservaExistente.setId_habitacion(reservaActualizada.getId_habitacion());

            reservaRepository.save(reservaExistente);
            return true;
        }

        return false;
    }

    public boolean eliminarReserva(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            reservaRepository.delete(reservaOptional.get());
            return true;
        }

        return false;
    }
}
>>>>>>> origin/main
