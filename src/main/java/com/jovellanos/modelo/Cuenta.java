package com.jovellanos.modelo;

import java.util.ArrayList;

public class Cuenta {
    private int id;
    private Double balance;
    private ArrayList<Tarjeta> listaTarjetas;
    private ArrayList<Movimiento> historialMovimientos;
    private ArrayList<GastosPeriodicos> listaGastos;

    public Cuenta() {
        
    }

    public Cuenta(int id, Double balance, ArrayList<Tarjeta> listaTarjetas,
            ArrayList<Movimiento> historialMovimientos, ArrayList<GastosPeriodicos> listaGastos) {
        this.id = id;
        this.balance = balance;
        this.listaTarjetas = listaTarjetas;
        this.historialMovimientos = historialMovimientos;
        this.listaGastos = listaGastos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ArrayList<Tarjeta> getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(ArrayList<Tarjeta> listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    public ArrayList<Movimiento> getHistorialMovimientos() {
        return historialMovimientos;
    }

    public void setHistorialMovimientos(ArrayList<Movimiento> historialMovimientos) {
        this.historialMovimientos = historialMovimientos;
    }

    public ArrayList<GastosPeriodicos> getListaGastos() {
        return listaGastos;
    }

    public void setListaGastos(ArrayList<GastosPeriodicos> listaGastos) {
        this.listaGastos = listaGastos;
    }
}
