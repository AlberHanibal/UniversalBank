package com.jovellanos.controladores;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class ControladorLogin {

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContraseña;

    @FXML
    private PasswordField pswContraseña;

    private boolean mostrarContraseña = false;

    public void initialize() {
        if (App.getScene() != null) {
            App.getScene().getWindow().setWidth(600);
            App.getScene().getWindow().setHeight(400);
        }

        txtUsuario.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Continuar();
            }
        });

        pswContraseña.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Continuar();
            }
        });

        txtContraseña.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Continuar();
            }
        });
    }

    @FXML
    private void Continuar() {
        String username = txtUsuario.getText();
        String contraseña = mostrarContraseña ? txtContraseña.getText() : pswContraseña.getText();

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

    @FXML
    private void mostrarContraseña() {
        mostrarContraseña = !mostrarContraseña;

        if (mostrarContraseña) {
            txtContraseña.setText(pswContraseña.getText());
            txtContraseña.setVisible(true);
            txtContraseña.setManaged(true);
            pswContraseña.setVisible(false);
            pswContraseña.setManaged(false);
        } else {
            pswContraseña.setText(txtContraseña.getText());
            pswContraseña.setVisible(true);
            pswContraseña.setManaged(true);
            txtContraseña.setVisible(false);
            txtContraseña.setManaged(false);
        }
    }
}
