package com.ashir.reservarhotel.controller;

import com.ashir.reservarhotel.entities.Reserva;
import com.ashir.reservarhotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaService.obtenerTodasLasReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Reserva>> buscarReservasPorCliente(@RequestParam("cedulaCliente") String cedulaCliente) {
        List<Reserva> reservas = Collections.singletonList(reservaService.buscarReservasPorCliente(Long.valueOf(cedulaCliente)));

        if (!reservas.isEmpty()) {
            return ResponseEntity.ok(reservas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigoReserva}")
    public ResponseEntity<String> actualizarReserva(@PathVariable String codigoReserva, @RequestBody Reserva reservaActualizada) {
        boolean actualizado = reservaService.actualizarReserva(codigoReserva, reservaActualizada);

        if (actualizado) {
            return new ResponseEntity<>("Reserva actualizada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reserva no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{codigoReserva}")
    public ResponseEntity<String> eliminarReserva(@PathVariable String codigoReserva) {
        boolean eliminado = reservaService.eliminarReserva(Long.valueOf(codigoReserva));

        if (eliminado) {
            return new ResponseEntity<>("Reserva eliminada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Reserva no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
