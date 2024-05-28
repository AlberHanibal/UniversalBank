package com.jovellanos.controladores.principal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.GastosPeriodicos;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ControladorPrincipal {
    private Usuario usuario = App.getUsuario();
    ControladorMongoDB controlMongo = new ControladorMongoDB();

    @FXML
    private Button botonResumen;

    @FXML
    private Button botonMovimientos;

    @FXML
    private Button botonTarjetas;

    @FXML
    private Button botonPagos;
    
    @FXML
    private SubScene sceneCentral;

    private void cambiarCentral(String fichero) {
        App.cambiarEscenaCentral(fichero);
    }

    public void initialize() {
        App.getScene().getWindow().setWidth(1300);
        App.getScene().getWindow().setHeight(750);
        App.setSubSceneCentral(sceneCentral);

        comprobarPagos();
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
    private void clickPagos() {
        cambiarCentral("fxml/principal/PagosRecurrentes.fxml");
    }

    @FXML
    private void clickPerfil() {
        cambiarCentral("fxml/principal/Perfil.fxml");
    }

    private void comprobarPagos() {
        ArrayList<Cuenta> listaCuentas = usuario.getListaCuentas();

        if (listaCuentas != null && !listaCuentas.isEmpty()) { 
            for (Cuenta c : listaCuentas) { // Itineramos sobre las cuentas del usuario
                ArrayList<GastosPeriodicos> listaGastos = c.getListaGastos();

                if (listaGastos != null && !listaGastos.isEmpty()) {
                    for (GastosPeriodicos gasto : listaGastos) { //Itineramos sobre los gastos de cada cuenta
                        Date fechaActual = new Date();

                        // Comprobamos que hay pagos pendientes y no salimos hasta solucionarlos:
                        while (!fechaActual.before(gasto.getFechaSiguientePago()) && gasto.getFechaSiguientePago() != null && gasto.getCantidadRestante() > 0) {
                            Double DineroPorPlazo = gasto.getPlazosDinero();            // Dinero que hay que pagar en cada plazo
                            Double DineroRestante = gasto.getCantidadRestante();        // Cuando falta por saldar la deuda
                            Double YaPagado = gasto.getCantidadPagada();                // Cuanto a pagado ya
                            Double DineroARestar = 0.0;
                            
                            if ((DineroRestante - DineroPorPlazo) < 0) { // Si da negativo es que este es el ultimo pago
                                DineroARestar = DineroRestante;
                                gasto.setCantidadRestante(0.0);
                            } else {
                                DineroARestar = DineroPorPlazo;
                                double resto = DineroRestante - DineroPorPlazo;
                                
                                System.out.println(resto);
                                gasto.setCantidadRestante(resto);

                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(gasto.getFechaSiguientePago()); // Se suma un mes a la fecha del siguiente pago
                                calendar.add(Calendar.MONTH, 1);
                                Date nuevaFechaSiguientePago = calendar.getTime();

                                gasto.setFechaSiguientePago(nuevaFechaSiguientePago);
                            }
                            System.out.println(DineroARestar + " " + YaPagado);
                            gasto.setCantidadPagada(YaPagado + DineroARestar);

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Pago");
                            alert.setHeaderText(null);
                            alert.setContentText(String.format("Se ha realizado un pago de %.2f en relación a %s.", DineroARestar, gasto.getTipo()));
                            alert.showAndWait();
                        }
                    }
                }
                usuario.actualizarCuenta(c);
            }
        }
        controlMongo.ActualizarUsuario(usuario);
    }
}
