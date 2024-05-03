package com.jovellanos.controladores;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ControladorLogin {

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private TextField TextFieldContraseña;

    public void initialize() {
        if (App.getScene() != null) {
            App.getScene().getWindow().setWidth(600);
            App.getScene().getWindow().setHeight(400);
        }
    }

    @FXML
    private void Continuar() {
        String username = TextFieldUsuario.getText();
        String contraseña = TextFieldContraseña.getText();

        ControladorMongoDB ControlMongo = new ControladorMongoDB();
        Boolean existe = ControlMongo.ComprobarUsuarioYContraseña(username, contraseña);

        if (existe) {
            Usuario usuario = ControlMongo.buscarUsuarioPorNombre(username);
            App.setUsuario(usuario);
            App.getScene().setRoot(App.cargarEscena("fxml/SelectorCuenta.fxml"));
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El usuario o la contraseña son incorrectos.");
            alert.showAndWait();

            return;
        }
    }

    @FXML
    private void CrearUsuario() {
        App.getScene().setRoot(App.cargarEscena("fxml/Formulario.fxml"));
    }
}
