package com.ashir.reservarhotel.controller;

import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.entities.Reserva;
import com.ashir.reservarhotel.model.HabitacionDto;
import com.ashir.reservarhotel.model.TipoHabitacion;
import com.ashir.reservarhotel.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    private HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearHabitacion(@RequestBody HabitacionDto request) {
        try {
            Habitacion habitacion = habitacionService.crearHabitacion(request.getTipoHabitacion(), request.getPrecioBase());
            return ResponseEntity.ok(habitacion);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }


    @GetMapping("/todas")
    public ResponseEntity<List<Habitacion>> obtenerTodasLasHabitaciones() {
        List<Habitacion> habitaciones = habitacionService.obtenerTodasLasHabitaciones();
        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping("/{habitacionId}")
    public ResponseEntity<Habitacion> obtenerHabitacionPorId(@PathVariable Long habitacionId) {
        Habitacion habitacion = habitacionService.obtenerHabitacionPorId(habitacionId);
        if (habitacion != null) {
            return ResponseEntity.ok(habitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Habitacion>> obtenerHabitacionesDisponiblesPorFecha(
            @RequestParam("fechaReserva") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva) {
        List<Habitacion> habitaciones = habitacionService.buscarHabitacionesDisponiblesPorFecha(fechaReserva);
        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping("/disponibles/tipo")
    public ResponseEntity<List<Habitacion>> obtenerHabitacionesDisponiblesPorTipoYFecha(
            @RequestParam("fechaReserva") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaReserva,
            @RequestParam("tipoHabitacion") TipoHabitacion tipoHabitacion) {
        List<Habitacion> habitaciones = habitacionService.buscarHabitacionesDisponiblesPorTipoYFecha(fechaReserva, tipoHabitacion);
        return ResponseEntity.ok(habitaciones);
    }

    @PutMapping("/actualizar/{habitacionId}")
    public ResponseEntity<Habitacion> actualizarHabitacion(@PathVariable Long habitacionId, @RequestBody HabitacionDto request) {
        Habitacion habitacion = habitacionService.actualizarHabitacion(habitacionId, request.getTipoHabitacion(), request.getPrecioBase());
        if (habitacion != null) {
            return ResponseEntity.ok(habitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{habitacionId}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Long habitacionId) {
        habitacionService.eliminarHabitacion(habitacionId);
        return ResponseEntity.ok("Habitación eliminada con éxito");
    }
}
