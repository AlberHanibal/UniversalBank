package com.jovellanos;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;

public class ControladorPrincipal {
    
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private SubScene sceneCentral;

    public void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("SelectorCuenta.fxml"));
        Parent root = loader.load();
        sceneCentral.setRoot(root);
    }

    @FXML
    private void clickSiguiente1() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Formulario.fxml"));
        Parent root = loader.load();
        sceneCentral.setRoot(root);
    }
    @FXML
    private void clickSiguiente2() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = loader.load();
        sceneCentral.setRoot(root);
    }
    
}
