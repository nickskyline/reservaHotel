package com.ashir.reservarhotel.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fecha_reserva", nullable = false)
    public LocalDate fechaReserva;

    @Column(name = "precio_total", nullable = false)
    public Double precioTotal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_habitacion", referencedColumnName = "id")
    private Habitacion habitacion;

    public Reserva() {
    }

    public Reserva(Long id, LocalDate fechaReserva, Double precioTotal) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.precioTotal = precioTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
