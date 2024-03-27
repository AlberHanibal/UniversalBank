package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class ControladorLogin{

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private TextField TextFieldContraseña;

    @FXML
    private void Continuar() throws IOException {
        //FXMLLoader loader = new FXMLLoader(App.class.getResource("Mesa.fxml"));
        //Parent root = loader.load();

        String nombre = TextFieldUsuario.getText();
        String contraseña = TextFieldContraseña.getText();

        System.out.println(nombre + " " + contraseña);

        //Codigo comprobación de usuario----------------

        //App.getScene().setRoot(root);
    }
}
