package com.jovellanos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Usuario usuario;
    private static Cuenta cuenta;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = loader.load();

        scene = new Scene(root, 600, 350);
        stage.setScene(scene);
        stage.show();
    }

    static Scene getScene() {
        return scene;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario u) {
        usuario = u;
    }

    public static Cuenta getCuenta() {
        return cuenta;
    }

    public static void setCuenta(Cuenta c) {
        cuenta = c;
    }

    public static Parent cargarEscena(String fichero) {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fichero));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static void main(String[] args) {
        launch();
    }

}