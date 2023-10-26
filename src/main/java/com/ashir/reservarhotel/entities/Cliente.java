package com.ashir.reservarhotel.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nombres", nullable = false)
    public String nombres;

    @Column(name = "apellidos", nullable = false)
    public String apellidos;

    @Column(name = "cedula", nullable = false)
    public String cedula;

    @Column(name = "direccion", nullable = false)
    public String direccion;

    @Column(name = "edad", nullable = false)
    public Integer edad;

    @Column(name = "email", nullable = false)
    public String email;

    @OneToOne(mappedBy = "cliente")
    private Reserva reserva;
    public Cliente() {
    }

    public Cliente(Long id, String nombres, String apellidos, String cedula, String direccion, Integer edad, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.direccion = direccion;
        this.edad = edad;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
