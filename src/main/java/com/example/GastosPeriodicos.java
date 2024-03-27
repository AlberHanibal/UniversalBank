package com.example;

import java.io.Serializable;
import java.time.LocalDate;

public class GastosPeriodicos implements Serializable {
    public Double cantidadTotal;
    public Double cantidadPagada;
    public LocalDate fechaInicio;
    public LocalDate fechaSiguientePago;
    public int plazosTiempo; //numero de dias entre cada plazo
    public Double plazosDinero;

    public GastosPeriodicos(Double cantidadTotal, Double cantidadPagada, LocalDate fechaInicio,
            LocalDate fechaSiguientePago, int plazosTiempo, Double plazosDinero) {
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaSiguientePago() {
        return fechaSiguientePago;
    }

    public void setFechaSiguientePago(LocalDate fechaSiguientePago) {
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
