package com.jovellanos;

import java.io.IOException;
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
    private void Continuar() throws IOException {
        

        String nombre = TextFieldUsuario.getText();
        String contraseña = TextFieldContraseña.getText();

        ControladorMongoDB ControlMongo = new ControladorMongoDB();
        Boolean existe = ControlMongo.ComprobarUsuario(nombre, contraseña);

        if (existe) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("SelectorCuenta.fxml"));
            Parent root = loader.load();
            
            App.getScene().setRoot(root);
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
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Formulario.fxml"));
        Parent root = loader.load();

        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root));
        stage2.setTitle("Formulario creación de usuario");

        stage2.show();
    }
}