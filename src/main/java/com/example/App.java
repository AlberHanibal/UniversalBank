package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Plantilla de FXML y SceneBuilder
 */
public class App extends Application {

    private static Scene scene;
    private static Usuario usuario;

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

    public static void main(String[] args) {
        launch();
    }

}
