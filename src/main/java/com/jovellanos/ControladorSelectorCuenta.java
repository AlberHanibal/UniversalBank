package com.jovellanos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javafx.fxml.FXML;

public class ControladorSelectorCuenta {
    Usuario usuario = App.getUsuario();

    @FXML
    private void Continuar() throws IOException {
       
    }

    @FXML
    private void CrearCuenta() throws IOException {
        generarCuenta();
    }

    public Cuenta generarCuenta() {
        int id = generarIDUnico();
        Cuenta cuenta = new Cuenta(id, 0.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        ControladorMongoDB ControlMongo = new ControladorMongoDB();

        usuario.agregarCuenta(cuenta);
        ControlMongo.guardarCuenta(usuario.getUsername(), cuenta);
        
        return cuenta;
    }

    private int generarIDUnico() {
        // Utiliza UUID para generar una id unica
        return UUID.randomUUID().hashCode();
    }
}
