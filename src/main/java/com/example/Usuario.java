package com.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    public String usuario;
    public String contraseña;
    public ArrayList<Cuenta> listaCuentas[];
    public String nombre;
    public String apellidos;

    public Usuario(String usuario, String contraseña, ArrayList<Cuenta>[] listaCuentas, String nombre,
            String apellidos) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.listaCuentas = listaCuentas;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ArrayList<Cuenta>[] getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta>[] listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
