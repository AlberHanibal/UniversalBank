package com.jovellanos.controladores;

import java.io.IOException;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorLogin {

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private TextField TextFieldContraseña;

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
    private void CrearUsuario() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Formulario.fxml"));
        Parent root = loader.load();

        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root));
        stage2.setTitle("Formulario creación de usuario");

        stage2.show();
    }
}
