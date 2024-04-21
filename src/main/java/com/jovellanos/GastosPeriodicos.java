package com.jovellanos;

import java.util.Date;

public class GastosPeriodicos {
    private Double cantidadTotal;
    private Double cantidadPagada;
    private Date fechaInicio;
    private Date fechaSiguientePago;
    private int plazosTiempo; // numero de dias entre cada plazo
    private Double plazosDinero;

    public GastosPeriodicos() {

    }

    public GastosPeriodicos(Double cantidadTotal, Double cantidadPagada, Date fechaInicio,
            Date fechaSiguientePago, int plazosTiempo, Double plazosDinero) {
        this.cantidadTotal = cantidadTotal;
        this.cantidadPagada = cantidadPagada;
        this.fechaInicio = fechaInicio;
        this.fechaSiguientePago = fechaSiguientePago;
        this.plazosTiempo = plazosTiempo;
        this.plazosDinero = plazosDinero;
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

    public int getPlazosTiempo() {
        return plazosTiempo;
    }

    public void setPlazosTiempo(int plazosTiempo) {
        this.plazosTiempo = plazosTiempo;
    }

    public Double getPlazosDinero() {
        return plazosDinero;
    }

    public void setPlazosDinero(Double plazosDinero) {
        this.plazosDinero = plazosDinero;
    }
}
