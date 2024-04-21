package com.jovellanos;

import java.util.Date;

public class Movimiento {
    private Double cantidad;
    private String asunto;
    private Date fecha;
    private String tipo;

    public Movimiento() {

    }

    public Movimiento(Double cantidad, String asunto, Date fecha, String tipo) {
        this.cantidad = cantidad;
        this.asunto = asunto;
        this.fecha = fecha;
        this.tipo = tipo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
