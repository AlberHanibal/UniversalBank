package com.jovellanos.modelo;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    private String username;
    private String contraseña;
    private ArrayList<Cuenta> listaCuentas;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;
    private String correoElectronico;

    public Usuario() {

    }

    public Usuario(String username, String contraseña, ArrayList<Cuenta> listaCuentas, String nombre,
            String apellidos) {
        this.username = username;
        this.contraseña = contraseña;
        this.listaCuentas = listaCuentas;
        this.nombre = (nombre != null) ? nombre : "";
        this.apellidos = (apellidos != null) ? apellidos : "";
        this.direccion = "";
        this.telefono = "";
        this.fechaNacimiento = null;
        this.correoElectronico = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void agregarCuenta(Cuenta cuenta) {
        listaCuentas.add(cuenta);
    }

    public int obtenerNumeroCuentas() {
        return listaCuentas.size();
    }

    public void actualizarCuenta(Cuenta cuentaActualizada) {
        for (int i = 0; i < listaCuentas.size(); i++) {
            if (listaCuentas.get(i).getId() == cuentaActualizada.getId()) {
                listaCuentas.set(i, cuentaActualizada);
                return;
            }
        }
    }
}
