package com.jovellanos.modelo;

import java.time.LocalDate;

public class Tarjeta {
    private int id;
    private String tipo;
<<<<<<< HEAD
    private String numeroTarjeta;
=======
    private String NumeroTarjeta;
    private String CVV;
>>>>>>> main
    private Double limiteDiario;
    private String pin;
    private LocalDate fechaCaducidad;
    private boolean cancelada;
    private boolean bloqueada;

    public Tarjeta() {

    }

<<<<<<< HEAD
    public Tarjeta(int id, String tipo, String numeroTarjeta, Double limiteDiario, String pin, LocalDate fechaCaducidad) {
        this.id = id;
        this.tipo = tipo;
        this.numeroTarjeta = numeroTarjeta;
=======
    public Tarjeta(int id, String tipo, String NumeroTarjeta, String CVV, Double limiteDiario, String pin, Date fechaCaducidad) {
        this.id = id;
        this.tipo = tipo;
        this.NumeroTarjeta = NumeroTarjeta;
        this.CVV = CVV;
>>>>>>> main
        this.limiteDiario = limiteDiario;
        this.pin = pin;
        this.fechaCaducidad = fechaCaducidad;
        this.cancelada = false;
        this.bloqueada = false;
<<<<<<< HEAD
=======
    }

    public int getId() {
        return id;
>>>>>>> main
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
    public String getTipo() {
        return tipo;
    }

>>>>>>> main
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

<<<<<<< HEAD
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
=======
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
>>>>>>> main
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

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }
}
