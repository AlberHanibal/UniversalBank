package com.jovellanos;

import java.util.ArrayList;

public class Usuario {
    private String usuario;
    private String contraseña;
    private ArrayList<Cuenta> listaCuentas;
    private String nombre;
    private String apellidos;

    public Usuario(String usuario, String contraseña, ArrayList<Cuenta> listaCuentas, String nombre,
            String apellidos) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.listaCuentas = listaCuentas;
        this.nombre = (nombre != null) ? nombre : "";
        this.apellidos = (apellidos != null) ? apellidos : "";
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

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
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
