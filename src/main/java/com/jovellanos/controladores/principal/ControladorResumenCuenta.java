package com.jovellanos.controladores.principal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.jovellanos.App;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Usuario;
import com.jovellanos.modelo.Movimiento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControladorResumenCuenta {

    private Usuario usuario = App.getUsuario();
    private Cuenta cuenta = App.getCuenta();

    @FXML
    private TableView<Movimiento> tblMovimientos;

    @FXML
    private Label lblID;

    @FXML
    private Label lblBalance;

    @FXML
    private TableColumn<Movimiento, Double> colCantidad;

    @FXML
    private TableColumn<Movimiento, String> colAsunto;

    @FXML
    private TableColumn<Movimiento, Date> colFecha;

    @FXML
    private TableColumn<Movimiento, String> colTipo;

    @FXML
    private Button btnSiguienteTarjeta;

    @FXML
    private Button btnAnteriorTarjeta;

    @FXML
    private Button btnAnteriorCuenta;

    @FXML
    private Button btnSiguienteCuenta;

    @FXML
    private ImageView imgSiguienteTarjeta;
    @FXML
    private ImageView imgAnteriorTarjeta;
    @FXML
    private ImageView imgAnteriorCuenta;
    @FXML
    private ImageView imgSiguienteCuenta;

    public void initialize() {
        App.getScene().getWindow().setWidth(1260);
        App.getScene().getWindow().setHeight(700);

        cuenta.setHistorialMovimientos(crearMovimientos()); // Codigo temporal para generar movimientos falsos

        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        ObservableList<Movimiento> movimientosObservable = FXCollections.observableArrayList(cuenta.getHistorialMovimientos());
        tblMovimientos.setItems(movimientosObservable);

        mostrarDatosCuenta();
        escaladoFlechas();
    }

    @FXML
    private void CuentaAnterior() {
        cuenta = RastrearCuenta("Anterior");
        App.setCuenta(cuenta);

        cuenta.setHistorialMovimientos(crearMovimientos()); //Temporal
        ObservableList<Movimiento> movimientosObservable = FXCollections.observableArrayList(cuenta.getHistorialMovimientos());
        tblMovimientos.setItems(movimientosObservable);

        mostrarDatosCuenta();
    }

    @FXML
    private void CuentaSiguiente() {
        cuenta = RastrearCuenta("Siguiente");
        App.setCuenta(cuenta);

        cuenta.setHistorialMovimientos(crearMovimientos()); //Temporal
        ObservableList<Movimiento> movimientosObservable = FXCollections.observableArrayList(cuenta.getHistorialMovimientos());
        tblMovimientos.setItems(movimientosObservable);

        mostrarDatosCuenta();
    }

    @FXML
    private void TarjetaAnterior() {
        //RastrearTarjeta("Anterior");
    }

    @FXML
    private void TarjetaSiguiente() {
        //RastrearTarjeta("Siguiente");
    }

    private Cuenta RastrearCuenta(String buscada) { // Iteramos sobre la lista de cuentas, para determinar cual es la siguiente o anterior cuenta.
        int idCuentaActual = cuenta.getId();
    
        int indiceActual = -1;
        for (int i = 0; i < usuario.getListaCuentas().size(); i++) {
            Cuenta c = usuario.getListaCuentas().get(i);
            if (c.getId() == idCuentaActual) {
                indiceActual = i;
                break;
            }
        }

        int indiceAnterior = (indiceActual - 1 + usuario.getListaCuentas().size()) % usuario.getListaCuentas().size();
        int indiceSiguiente = (indiceActual + 1) % usuario.getListaCuentas().size();
        
        Cuenta cuentaAnterior = usuario.getListaCuentas().get(indiceAnterior);
        Cuenta cuentaSiguiente = usuario.getListaCuentas().get(indiceSiguiente);

        if (buscada.equals("Siguiente")) {
            return cuentaSiguiente;
        } else {
            return cuentaAnterior;
        }
    }

    private void mostrarDatosCuenta() {
        lblID.setText(String.valueOf("ID: " + cuenta.getId()));
        lblBalance.setText(String.valueOf("Balance: " + cuenta.getBalance() + " €."));
    }


    private void escaladoFlechas() {
        // Escala la imagen a un tamaño mas pequeño al ser presionada --------------
        btnSiguienteTarjeta.setOnMousePressed(event -> {
            imgSiguienteTarjeta.setScaleX(0.8);
            imgSiguienteTarjeta.setScaleY(0.8);
        });

        btnAnteriorTarjeta.setOnMousePressed(event -> {
            imgAnteriorTarjeta.setScaleX(0.8);
            imgAnteriorTarjeta.setScaleY(0.8);
        });

        btnAnteriorCuenta.setOnMousePressed(event -> {
            imgAnteriorCuenta.setScaleX(0.8);
            imgAnteriorCuenta.setScaleY(0.8);
        });

        btnSiguienteCuenta.setOnMousePressed(event -> {
            imgSiguienteCuenta.setScaleX(0.8);
            imgSiguienteCuenta.setScaleY(0.8);
        });

        // Restaura la imagen a su tamaño original ---------------------------------
        btnSiguienteTarjeta.setOnMouseReleased(event -> {
            imgSiguienteTarjeta.setScaleX(1.0);
            imgSiguienteTarjeta.setScaleY(1.0);
        });

        btnAnteriorTarjeta.setOnMouseReleased(event -> {
            imgAnteriorTarjeta.setScaleX(1.0);
            imgAnteriorTarjeta.setScaleY(1.0);
        });

        btnAnteriorCuenta.setOnMouseReleased(event -> {
            imgAnteriorCuenta.setScaleX(1.0);
            imgAnteriorCuenta.setScaleY(1.0);
        });

        btnSiguienteCuenta.setOnMouseReleased(event -> {
            imgSiguienteCuenta.setScaleX(1.0);
            imgSiguienteCuenta.setScaleY(1.0);
        });
        // -------------------------------------------------------------------------
    }

    private static ArrayList<Movimiento> crearMovimientos() { // Codigo temporal para mostrar datos en la tabla movimientos ---------------------
        ArrayList<Movimiento> historialMovimientos = new ArrayList<>();

        Random random = new Random();
        int num = random.nextInt(10) + 3;

        for (int i = 0; i < num; i++) {
            Movimiento movimiento = new Movimiento();

            double cantidad = random.nextDouble() * 15000 - 1000; 
            cantidad = Math.round(cantidad * 100.0) / 100.0;

            movimiento.setCantidad(cantidad);
            movimiento.setAsunto("Asunto " + (i + 1));
            movimiento.setFecha(new Date());
            movimiento.setTipo("Tipo " + (i + 1));
            historialMovimientos.add(movimiento);
        }

        return historialMovimientos;
    }
}
