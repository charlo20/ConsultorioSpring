package com.example.hospitalcitas.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Consultorio {

    @Id
    private Long numeroConsultorio;
    private String piso;

    // Constructor sin parámetros
    public Consultorio() {}

    // Constructor con parámetros
    public Consultorio(Long numeroConsultorio, String piso) {
        this.numeroConsultorio = numeroConsultorio;
        this.piso = piso;
    }

    // Getters y Setters
    public Long getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(Long numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }
}