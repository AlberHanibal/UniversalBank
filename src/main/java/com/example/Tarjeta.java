package com.example;

import java.io.Serializable;
import java.time.LocalDate;

public class Tarjeta implements Serializable {
    private int id;
    private Double limiteDiario;
    private String pin;
    private LocalDate fechaCaducidad;
    private boolean cancelada;

    public Tarjeta(int id, Double limiteDiario, String pin, LocalDate fechaCaducidad) {
        this.id = id;
        this.limiteDiario = limiteDiario;
        this.pin = pin;
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getLimiteDiario() {
        return limiteDiario;
    }

    public void setLimiteDiario(Double limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}
