package com.jovellanos.modelo;

import java.util.Date;

public class GastosPeriodicos {
    private Double cantidadTotal;
    private Double cantidadPagada;
    private Double cantidadRestante;
    private Date fechaInicio;
    private Date fechaSiguientePago;
    private Double plazosDinero; //Dinero por plazo
    private Double interesAnual;
    private int plazosTotales; // En meses
    private String tipo;

    public GastosPeriodicos() {

    }

    public GastosPeriodicos(Double cantidadTotal, Date fechaInicio, String tipo,
            Date fechaSiguientePago, Double plazosDinero, Double interesAnual, int plazosTotales) {
        this.cantidadTotal = cantidadTotal;
        this.cantidadPagada = 0.0;
        this.cantidadRestante = cantidadTotal;
        this.fechaInicio = fechaInicio;
        this.fechaSiguientePago = fechaSiguientePago;
        this.plazosDinero = plazosDinero;
        this.interesAnual = interesAnual;
        this.plazosTotales = plazosTotales;
        this.tipo = tipo;
    }

    public Double getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Double cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Double getCantidadPagada() {
        return cantidadPagada;
    }

    public void setCantidadPagada(Double cantidadPagada) {
        this.cantidadPagada = cantidadPagada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaSiguientePago() {
        return fechaSiguientePago;
    }

    public void setFechaSiguientePago(Date fechaSiguientePago) {
        this.fechaSiguientePago = fechaSiguientePago;
    }

    public Double getPlazosDinero() {
        return plazosDinero;
    }

    public void setPlazosDinero(Double plazosDinero) {
        this.plazosDinero = plazosDinero;
    }
    
    public Double getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(Double interesAnual) {
        this.interesAnual = interesAnual;
    }

    public int getPlazosTotales() {
        return plazosTotales;
    }

    public void setPlazosTotales(int plazosTotales) {
        this.plazosTotales = plazosTotales;
    }

    public Double getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(Double cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
