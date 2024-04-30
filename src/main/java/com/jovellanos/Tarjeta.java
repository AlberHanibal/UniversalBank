package com.jovellanos;

import java.time.LocalDate;

public class Tarjeta {
    private int id;
    private String tipo;
    private String numeroTarjeta;
    private Double limiteDiario;
    private String pin;
    private LocalDate fechaCaducidad;
    private boolean cancelada;
    private boolean bloqueada;

    public Tarjeta() {

    }

    public Tarjeta(int id, String tipo, String numeroTarjeta, Double limiteDiario, String pin, LocalDate fechaCaducidad) {
        this.id = id;
        this.tipo = tipo;
        this.numeroTarjeta = numeroTarjeta;
        this.limiteDiario = limiteDiario;
        this.pin = pin;
        this.fechaCaducidad = fechaCaducidad;
        this.cancelada = false;
        this.bloqueada = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setLimiteDiario(Double limiteDiario) {
        this.limiteDiario = limiteDiario;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Double getLimiteDiario() {
        return limiteDiario;
    }

    public String getPin() {
        return pin;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }
}
