package com.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable {
    public int id;
    public Double balance;
    public ArrayList<Tarjeta> listaTarjetas[];
    public ArrayList<Movimiento> historialMovimientos[];
    public ArrayList<GastosPeriodicos> listaGastos[];

    public Cuenta(int id, Double balance, ArrayList<Tarjeta>[] listaTarjetas,
            ArrayList<Movimiento>[] historialMovimientos, ArrayList<GastosPeriodicos>[] listaGastos) {
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

    public ArrayList<Tarjeta>[] getListaTarjetas() {
        return listaTarjetas;
    }

    public void setListaTarjetas(ArrayList<Tarjeta>[] listaTarjetas) {
        this.listaTarjetas = listaTarjetas;
    }

    public ArrayList<Movimiento>[] getHistorialMovimientos() {
        return historialMovimientos;
    }

    public void setHistorialMovimientos(ArrayList<Movimiento>[] historialMovimientos) {
        this.historialMovimientos = historialMovimientos;
    }

    public ArrayList<GastosPeriodicos>[] getListaGastos() {
        return listaGastos;
    }

    public void setListaGastos(ArrayList<GastosPeriodicos>[] listaGastos) {
        this.listaGastos = listaGastos;
    }
}
