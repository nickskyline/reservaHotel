package com.ashir.reservarhotel.controller;

import com.ashir.reservarhotel.entities.Reserva;
import com.ashir.reservarhotel.model.ActualizarReservaDto;
import com.ashir.reservarhotel.model.ReservaDto;
import com.ashir.reservarhotel.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

    @RestController
    @RequestMapping("/api/reservas")
    public class ReservaController {

        @Autowired
        private ReservaService reservaService;

        @PostMapping("/crear")
        public ResponseEntity<?> crearReserva(@RequestBody ReservaDto nuevaReserva) {
            try {
                Reserva reserva = reservaService.crearReserva(nuevaReserva.getHabitacionId(), nuevaReserva.getFechaReserva(), nuevaReserva.getClienteCedula());
                return ResponseEntity.ok(reserva);
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            }
        }

        @PutMapping("/actualizar/{reservaId}")
        public ResponseEntity<?> actualizarReserva(
                @PathVariable Long reservaId,
                @RequestBody ActualizarReservaDto actualizarReserva) {

            try {
                Reserva reserva = reservaService.actualizarReserva(reservaId, actualizarReserva.getFechaReserva(), actualizarReserva.getHabitacionId());

                if (reserva != null) {
                    return ResponseEntity.ok(reserva);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            }

        }


        @GetMapping("/{reservaId}")
        public ResponseEntity<Reserva> obtenerReserva(@PathVariable Long reservaId) {
            Reserva reserva = reservaService.obtenerReservaPorId(reservaId);
            if (reserva != null) {
                return ResponseEntity.ok(reserva);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/eliminar/{reservaId}")
        public ResponseEntity<String> eliminarReserva(@PathVariable Long reservaId) {
            reservaService.eliminarReserva(reservaId);
            return ResponseEntity.ok("Reserva eliminada con éxito");
        }

    }
