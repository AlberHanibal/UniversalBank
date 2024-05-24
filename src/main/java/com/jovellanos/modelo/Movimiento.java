package com.jovellanos.modelo;

import java.util.Date;
import java.util.UUID;

public class Movimiento {
    private int id;
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
        this.id = generarIDUnico();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    private int generarIDUnico() {
        int nuevaId = UUID.randomUUID().hashCode();

        if (nuevaId < 0) {
            nuevaId = nuevaId * -1;
        }
        
        return nuevaId;
    }
}
