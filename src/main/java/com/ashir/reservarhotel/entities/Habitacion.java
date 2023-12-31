package com.ashir.reservarhotel.entities;


import com.ashir.reservarhotel.model.TipoHabitacion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "tipo_habitacion", nullable = false)
    public TipoHabitacion tipoHabitacion;

    @Column(name = "precio_base", nullable = false)
    public Double precioBase;

    @OneToMany(mappedBy = "habitacion")
    @JsonManagedReference
    private List<Reserva> reservas = new ArrayList<>();

    public Habitacion() {
    }

    public Habitacion(Long id, TipoHabitacion tipoHabitacion, Double precioBase) {
        this.id = id;
        this.tipoHabitacion = tipoHabitacion;
        this.precioBase = precioBase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

}
