package com.jovellanos.controladores.principal;

import com.jovellanos.App;

import javafx.fxml.FXML;
import javafx.scene.SubScene;

public class ControladorPrincipal {

    @FXML
    private SubScene sceneCentral;

    private void cambiarCentral(String fichero) {
        sceneCentral.setRoot(App.cargarEscena(fichero));
    }

    public void initialize() {
        cambiarCentral("fxml/principal/ResumenCuenta.fxml");
    }

    @FXML
    private void clickResumenCuenta() {
        cambiarCentral("fxml/principal/ResumenCuenta.fxml");
    }

    @FXML
    private void clickMovimientos() {
        cambiarCentral("fxml/principal/Movimientos.fxml");
    }

    @FXML
    private void clickTarjetas() {
        cambiarCentral("fxml/principal/Tarjetas.fxml");
    }

    @FXML
    private void clickHipotecas() {
        cambiarCentral("fxml/principal/Hipotecas.fxml");
    }

    @FXML
    private void clickPrestamos() {
        cambiarCentral("fxml/principal/Prestamos.fxml");
    }

    @FXML
    private void clickPerfil() {
        cambiarCentral("fxml/principal/Perfil.fxml");
    }

}
