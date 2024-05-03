package com.jovellanos.controladores;

import java.util.ArrayList;
import java.util.UUID;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Usuario;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;

public class ControladorSelectorCuenta {
    private Usuario usuario = App.getUsuario();

    @FXML
    private AnchorPane anchorPane;

    public void initialize() {
        actualizarEtiquetas();
    }

    @FXML
    private void Continuar() {
        // tamaño de la ventana sin decidir
        App.getScene().setRoot(App.cargarEscena("fxml/principal/Principal.fxml"));
        App.getScene().getWindow().setWidth(1500);
        App.getScene().getWindow().setHeight(800);
    }

    @FXML
    private void CrearCuenta() {
        generarCuenta();
        actualizarEtiquetas();
    }

    private void actualizarEtiquetas() {
        ObservableList<Node> children = anchorPane.getChildren(); // Elimina todas las etiquetas menos la primera
        if (children.size() > 1) {
            children.remove(1, children.size());
        }

        ArrayList<Cuenta> listaCuentas = usuario.getListaCuentas();
        int cont = 0;

        int numColumns = 2; // Número deseado de columnas

        double columnSpacing = 50;
        double anchorPaneWidth = 1200;

        double totalColumnsWidth = numColumns * 300 + (numColumns - 1) * columnSpacing;
        double emptySpace = anchorPaneWidth - totalColumnsWidth;
        double leftMargin = emptySpace / 2;

        for (Cuenta cuenta : listaCuentas) {
            int id = cuenta.getId();
            Double balance = cuenta.getBalance();

            Label label = new Label("ID:  " + id + "\nBalance: " + balance);
            label.setMinWidth(300);
            label.setMaxWidth(300);
            label.setStyle("-fx-alignment: CENTER-LEFT; " + "-fx-background-color: white; " + "-fx-padding: 10px;");

            anchorPane.getChildren().add(label);

            int column = cont % numColumns; // Calculamos la fila y la columna que le toca a cada etiqueta.
            int row = cont / numColumns;

            double leftAnchor = leftMargin + column * (300 + columnSpacing);
            double topAnchor = 50.0 + row * 100;
            AnchorPane.setLeftAnchor(label, leftAnchor);
            AnchorPane.setTopAnchor(label, topAnchor);

            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    App.setCuenta(cuenta);
                    App.getScene().setRoot(App.cargarEscena("fxml/principal/Principal.fxml"));
                }
            });

            cont++;
        }
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
