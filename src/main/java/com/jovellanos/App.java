package com.jovellanos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.stage.Stage;

import java.io.IOException;

import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Usuario;

public class App extends Application {

    private static Scene scene;
    private static SubScene subSceneCentral;
    private static Usuario usuario;
    private static Cuenta cuenta;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/Login.fxml"));
        Parent root = loader.load();

        scene = new Scene(root, 600, 350);
        scene.getStylesheets().add(App.class.getResource("css/estilos.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setSubSceneCentral(SubScene subScene) {
        subSceneCentral = subScene;
    }

    public static void cambiarEscenaCentral(String fxml) {
        try {
            Parent root = loadFXML(fxml);
            if (subSceneCentral != null) {
                subSceneCentral.setRoot(root);
            } else {
                System.err.println("SubScene central is not set!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
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