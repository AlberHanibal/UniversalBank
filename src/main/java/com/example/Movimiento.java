package com.example;

import java.io.Serializable;
import java.time.LocalDate;

public class Movimiento implements Serializable {
    private Double cantidad;
    private String asunto;
    private LocalDate fecha;
    private String tipo;

    public Movimiento(Double cantidad, String asunto, String tipo) {
        this.cantidad = cantidad;
        this.asunto = asunto;
        this.tipo = tipo;

        fecha = LocalDate.now();
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
