package com.jovellanos.controladores.principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Tarjeta;
import com.jovellanos.modelo.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorTarjetas {

    private Usuario usuario = App.getUsuario();
    private Cuenta cuenta = App.getCuenta();
    private Tarjeta tarjeta = new Tarjeta();

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtCVV;

    @FXML
    private TextField txtPin;

    @FXML
    private TextField txtLimite;

    @FXML
    private TextField txtCaducidad;

    @FXML
    private TableView<Tarjeta> tblTarjetas;

    @FXML
    private TableColumn<Tarjeta, String> colTipo;

    @FXML
    private TableColumn<Tarjeta, String> colNumero;

    @FXML
    private TableColumn<Tarjeta, String> colCVV;

    @FXML
    private TableColumn<Tarjeta, String> colPin;

    @FXML
    private TableColumn<Tarjeta, Double> colLimite;

    @FXML
    private TableColumn<Tarjeta, Date> colCaducidad;

    @FXML
    private TableColumn<Tarjeta, Boolean> colCancelada;

    @FXML
    private TableColumn<Tarjeta, Boolean> colBloqueada;

    public void initialize() {
        App.getScene().getWindow().setWidth(1260);
        App.getScene().getWindow().setHeight(700);

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("NumeroTarjeta"));
        colCVV.setCellValueFactory(new PropertyValueFactory<>("CVV"));
        colPin.setCellValueFactory(new PropertyValueFactory<>("pin"));
        colLimite.setCellValueFactory(new PropertyValueFactory<>("limiteDiario"));
        colCaducidad.setCellValueFactory(new PropertyValueFactory<>("fechaCaducidad"));
        colCancelada.setCellValueFactory(new PropertyValueFactory<>("cancelada"));
        colBloqueada.setCellValueFactory(new PropertyValueFactory<>("bloqueada"));

        ObservableList<Tarjeta> tarjetasObservable = FXCollections.observableArrayList(cuenta.getListaTarjetas());
        tblTarjetas.setItems(tarjetasObservable);
    }

    @FXML
private void NuevaTarjeta() {
    if (txtLimite.getText().isEmpty() || txtCaducidad.getText().isEmpty() || txtTipo.getText().isEmpty() || 
        txtNumero.getText().isEmpty() || txtCVV.getText().isEmpty() || txtPin.getText().isEmpty()) {
        return;
    }

    Random random = new Random();
    int id = random.nextInt(1000000000);

    Double limit = Double.parseDouble(txtLimite.getText());
    Date fechaCaducidad = null;
    try {
        String textoCaducidad = txtCaducidad.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaCaducidad = sdf.parse(textoCaducidad);
    } catch (ParseException e) {
        return;
    }

    Tarjeta nuevaTarjeta = new Tarjeta(id, txtTipo.getText(), txtNumero.getText(), txtCVV.getText(), limit, txtPin.getText(), fechaCaducidad);

    cuenta.getListaTarjetas().add(nuevaTarjeta);
    usuario.actualizarCuenta(cuenta);
    tblTarjetas.getItems().setAll(cuenta.getListaTarjetas());

    ControladorMongoDB controlMongo = new ControladorMongoDB();
    controlMongo.ActualizarUsuario(usuario);
}

    @FXML
    private void CancelarTarjeta() {
        tarjeta = tblTarjetas.getSelectionModel().getSelectedItem();

        if (tarjeta != null) {
            tarjeta.setCancelada(true);

            tblTarjetas.refresh();
        }
    }

    @FXML
    private void ReactivarTarjeta() {
        tarjeta = tblTarjetas.getSelectionModel().getSelectedItem();

        if (tarjeta != null) {
            tarjeta.setCancelada(false);

            tblTarjetas.refresh();
        }
    }

    @FXML
    private void BloquearTarjeta() {
        tarjeta = tblTarjetas.getSelectionModel().getSelectedItem();

        if (tarjeta != null) {
            tarjeta.setBloqueada(true);

            tblTarjetas.refresh();
        }
    }

    @FXML
    private void DesbloquearTarjeta() {
        tarjeta = tblTarjetas.getSelectionModel().getSelectedItem();

        if (tarjeta != null) {
            tarjeta.setBloqueada(false);

            tblTarjetas.refresh();
        }
    }
}