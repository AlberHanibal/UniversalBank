package com.jovellanos.modelo;

import java.util.Date;

public class Tarjeta {
    private int id;
    private String NumeroTarjeta;
    private String CVV;
    private Double limiteDiario;
    private String pin;
    private Date fechaCaducidad;
    private boolean cancelada;

    public Tarjeta() {

    }

    public Tarjeta(int id, String NumeroTarjeta, String CVV, Double limiteDiario, String pin, Date fechaCaducidad) {
        this.id = id;
        this.NumeroTarjeta = NumeroTarjeta;
        this.CVV = CVV;
        this.limiteDiario = limiteDiario;
        this.pin = pin;
        this.fechaCaducidad = fechaCaducidad;
        this.cancelada = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroTarjeta() {
        return NumeroTarjeta;
    }

    public void setNumeroTarjeta(String NumeroTarjeta) {
        this.NumeroTarjeta = NumeroTarjeta;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
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
