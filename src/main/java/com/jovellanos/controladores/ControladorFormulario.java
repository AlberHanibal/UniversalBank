package com.jovellanos.controladores;

import java.util.ArrayList;

import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControladorFormulario {

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private PasswordField TextFieldContraseña;

    @FXML
    private PasswordField TextFieldConfirmarPass;

    @FXML
    private TextField TextFieldNombre;

    @FXML
    private TextField TextFieldApellidos;

    @FXML
    private Label LabelUsuario;

    @FXML
    private Label LabelContraseña;

    @FXML
    private Label LabelConfirmarPass;

    @FXML
    private void Continuar() {
        String username = TextFieldUsuario.getText();
        String contraseña = TextFieldContraseña.getText();
        String confirmarPass = TextFieldConfirmarPass.getText();
        String nombre = TextFieldNombre.getText();
        String apellidos = TextFieldApellidos.getText();

        // Comprobar campos obligatorios y poner en rojo los que esten vacios
        if (username.isEmpty() || contraseña.isEmpty() || confirmarPass.isEmpty()) {
            if (username.isEmpty()) {
                LabelUsuario.setTextFill(Color.RED);
            } else {
                LabelUsuario.setTextFill(Color.BLACK);
            }

            if (contraseña.isEmpty()) {
                LabelContraseña.setTextFill(Color.RED);
            } else {
                LabelContraseña.setTextFill(Color.BLACK);
            }

            if (confirmarPass.isEmpty()) {
                LabelConfirmarPass.setTextFill(Color.RED);
            } else {
                LabelConfirmarPass.setTextFill(Color.BLACK);
            }

            return;
        }
        ControladorMongoDB ControlMongo = new ControladorMongoDB();
        Boolean existe = ControlMongo.ComprobarUsuario(username);

        if (existe) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Usuario duplicado.");
            alert.showAndWait();

            return;
        }

        // Se comprueba que las contraseñas coincidan
        if (!contraseña.equals(confirmarPass)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Las contraseñas no coinciden.");
            alert.showAndWait();

            return;
        }

        ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
        Usuario u = new Usuario(username, contraseña, lista, nombre, apellidos);

        ControlMongo.guardarUsuario(u);

        Stage stage = (Stage) TextFieldUsuario.getScene().getWindow(); // Cerrar la ventana una vez creado el usuario
        stage.close();
    }
}