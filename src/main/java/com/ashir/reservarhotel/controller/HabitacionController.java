package com.ashir.reservarhotel.controller;

import com.ashir.reservarhotel.entities.Cliente;
import com.ashir.reservarhotel.entities.Habitacion;
import com.ashir.reservarhotel.model.TipoHabitacion;
import com.ashir.reservarhotel.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
    private HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }
    @PostMapping
    public ResponseEntity<String> crearHabitacion(@RequestBody Habitacion habitacion) {
        Habitacion nuevaHabitacion = habitacionService.crearHabitacion(habitacion);
        return new ResponseEntity<>("Habitacion creada con exito", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Habitacion>> obtenerTodasLasHabitaciones() {
        List<Habitacion> habitaciones = habitacionService.obtenerTodasLasHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }
    @GetMapping("/buscar")
    public ResponseEntity<Habitacion> buscarPorTipoDeHabitacion(@RequestParam("tipo_habitacion")TipoHabitacion tipoHabitacion) {
        Habitacion habitacion =habitacionService.ObtenerPorTipo(tipoHabitacion);

        if (habitacion != null) {
            return ResponseEntity.ok(habitacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarHabitacion(@RequestParam("tipo_habitacion") TipoHabitacion tipoHabitacion, @RequestBody Habitacion habitacionActualizada) {
        boolean actualizado = habitacionService.actualizarHabitacionPorTipo(tipoHabitacion, habitacionActualizada);

        if (actualizado) {
            return new ResponseEntity<>("Habitacion actualizada con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Habitacion no encontrada", HttpStatus.NOT_FOUND);
        }
    }



}
