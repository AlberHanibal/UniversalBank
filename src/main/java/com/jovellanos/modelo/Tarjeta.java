package com.jovellanos.modelo;

import java.util.Date;

public class Tarjeta {
    private int id;
    private Double limiteDiario;
    private String pin;
    private Date fechaCaducidad;
    private boolean cancelada;

    public Tarjeta() {

    }

    public Tarjeta(int id, Double limiteDiario, String pin, Date fechaCaducidad) {
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

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }
}
