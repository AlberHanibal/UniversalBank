package com.jovellanos;

import javafx.fxml.FXML;
import javafx.scene.SubScene;

public class ControladorPrincipal {

    @FXML
    private SubScene sceneCentral;

    private void cambiarCentral(String fichero) {
        sceneCentral.setRoot(App.cargarEscena(fichero));
    }

    public void initialize() {
        cambiarCentral("ResumenCuenta.fxml");
    }

    @FXML
    private void clickResumenCuenta() {
        cambiarCentral("ResumenCuenta.fxml");
    }

    @FXML
    private void clickMovimientos() {
        cambiarCentral("Movimientos.fxml");
    }

    @FXML
    private void clickTarjetas() {
        cambiarCentral("Tarjetas.fxml");
    }

    @FXML
    private void clickTransferencias() {
        cambiarCentral("Transferencias.fxml");
    }

    @FXML
    private void clickHipotecas() {
        cambiarCentral("Hipotecas.fxml");
    }

    @FXML
    private void clickPrestamos() {
        cambiarCentral("Prestamos.fxml");
    }

}
