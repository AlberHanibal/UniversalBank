package com.jovellanos.controladores;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Usuario;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

public class ControladorSelectorCuenta {
    private Usuario usuario = App.getUsuario();
    ControladorMongoDB controlMongo = new ControladorMongoDB();

    @FXML
    private AnchorPane anchorPane;

    public void initialize() {
        App.getScene().getWindow().setWidth(1200);
        App.getScene().getWindow().setHeight(750);

        actualizarEtiquetas();
    }

    @FXML
    private void Cancelar() {
        App.getScene().setRoot(App.cargarEscena("fxml/Login.fxml"));
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
    
        GridPane gridPane = new GridPane();
        gridPane.setHgap(columnSpacing);
        gridPane.setVgap(20); // Ajustar el espaciado vertical entre las filas
    
        for (Cuenta cuenta : listaCuentas) {
            int id = cuenta.getId();
            Double balance = cuenta.getBalance();
    
            Label label = new Label("ID:  " + id + "\nBalance: " + balance);
            label.setMinWidth(300);
            label.setMaxWidth(300);
            label.getStyleClass().addAll("card", "cuenta");
    
            Button btnEliminar = new Button("X");
            btnEliminar.setOnAction(event -> eliminarCuenta(cuenta));
    
            HBox hbox = new HBox(label, btnEliminar);
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER); // Alineación centrada horizontalmente
    
            gridPane.add(hbox, cont % numColumns, cont / numColumns);
    
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    App.setCuenta(cuenta);
                    App.getScene().setRoot(App.cargarEscena("fxml/principal/Principal.fxml"));
                }
            });
    
            cont++;
        }
        AnchorPane.setLeftAnchor(gridPane, leftMargin); // Centrar el GridPane en el AnchorPane
        AnchorPane.setTopAnchor(gridPane, 70.0); // Ajustar el espacio superior
    
        anchorPane.getChildren().add(gridPane);
    }

    private void eliminarCuenta(Cuenta cuenta) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que quieres eliminar esta cuenta?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            usuario.getListaCuentas().remove(cuenta);

            App.setUsuario(usuario);
            controlMongo.ActualizarUsuario(usuario);

            actualizarEtiquetas();
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
