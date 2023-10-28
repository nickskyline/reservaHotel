package com.ashir.reservarhotel.model;

import java.time.LocalDate;

public class ReservaDto {
    private Long habitacionId;
    private LocalDate fechaReserva;
    private String clienteCedula;


    public ReservaDto() {
    }

    public ReservaDto(Long habitacionId, LocalDate fechaReserva, String clienteCedula) {
        this.habitacionId = habitacionId;
        this.fechaReserva = fechaReserva;
        this.clienteCedula = clienteCedula;
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

    public String getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(String clienteCedula) {
        this.clienteCedula = clienteCedula;
    }
}