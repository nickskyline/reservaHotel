package com.ashir.reservarhotel.model;

import java.time.LocalDate;

public class ActualizarReservaDto {
    private Long habitacionId;
    private LocalDate fechaReserva;

    public ActualizarReservaDto() {
    }

    public ActualizarReservaDto(Long habitacionId, LocalDate fechaReserva) {
        this.habitacionId = habitacionId;
        this.fechaReserva = fechaReserva;
    }

    public Long getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(Long habitacionId) {
        this.habitacionId = habitacionId;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
