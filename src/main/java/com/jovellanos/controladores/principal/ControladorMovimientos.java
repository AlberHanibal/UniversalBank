package com.jovellanos.controladores.principal;

import java.util.Date;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Movimiento;
import com.jovellanos.modelo.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class ControladorMovimientos {
    private Usuario usuario = App.getUsuario();
    private Cuenta cuenta = App.getCuenta();

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtAsunto;

    @FXML
    private DatePicker dtpFecha;

    @FXML
    private TableView<Movimiento> tblMovimientos;

    @FXML
    private TableColumn<Movimiento, Double> colCantidad;

    @FXML
    private TableColumn<Movimiento, String> colAsunto;

    @FXML
    private TableColumn<Movimiento, Date> colFecha;

    @FXML
    private TableColumn<Movimiento, String> colTipo;

    public void initialize() {

        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        ObservableList<Movimiento> movimientosObservable = FXCollections.observableArrayList(cuenta.getHistorialMovimientos());
        tblMovimientos.setItems(movimientosObservable);

        txtCantidad.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevoMovimiento();
            }
        });

        txtTipo.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevoMovimiento();
            }
        });

        txtAsunto.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevoMovimiento();
            }
        });

        dtpFecha.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevoMovimiento();
            }
        });
    }

    @FXML
    private void NuevoMovimiento() {
        if (txtCantidad.getText().isEmpty() || dtpFecha.getValue() == null || txtTipo.getText().isEmpty() || 
            txtAsunto.getText().isEmpty()) {
            return;
        }

        Double cantidad = Double.parseDouble(txtCantidad.getText());
        String asunto = txtAsunto.getText();
        Date fecha = java.sql.Date.valueOf(dtpFecha.getValue());
        String tipo = txtTipo.getText();

        Movimiento nuevoMovimiento = new Movimiento(cantidad, asunto, fecha, tipo);

        cuenta.setBalance(cuenta.getBalance() + cantidad);
        cuenta.getHistorialMovimientos().add(nuevoMovimiento);
        usuario.actualizarCuenta(cuenta);
        tblMovimientos.getItems().setAll(cuenta.getHistorialMovimientos());

        ControladorMongoDB controlMongo = new ControladorMongoDB();
        controlMongo.ActualizarUsuario(usuario);

        App.setCuenta(cuenta);
        App.setUsuario(usuario);
    }
}
