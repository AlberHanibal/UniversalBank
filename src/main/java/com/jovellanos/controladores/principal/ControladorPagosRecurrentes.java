package com.jovellanos.controladores.principal;
import java.util.Calendar;
import java.util.Date;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.GastosPeriodicos;
import com.jovellanos.modelo.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorPagosRecurrentes {
    private Usuario usuario = App.getUsuario();
    private Cuenta cuenta = App.getCuenta();

    @FXML
    private TextField txtCantidadTotal;

    @FXML
    private TextField txtInteresAnual;

    @FXML
    private TextField txtPlazos;

    @FXML
    private TextField txtPlazosDinero;

    @FXML
    private DatePicker dtpFechaInicio;

    @FXML 
    private ComboBox<String> cmbPlazos;

    @FXML 
    private ComboBox<String> cmbTipo;

    @FXML
    private TableView<GastosPeriodicos> tblPagosRecorrentes;

    @FXML
    private TableColumn<GastosPeriodicos, Double> colCantidadTotal;

    @FXML
    private TableColumn<GastosPeriodicos, Double> colCantidadPagada;

    @FXML
    private TableColumn<GastosPeriodicos, Double> colCantidadRestante;

    @FXML
    private TableColumn<GastosPeriodicos, Double> colDineroPlazo;

    @FXML
    private TableColumn<GastosPeriodicos, Date> colProximoPlazo;

    @FXML
    private TableColumn<GastosPeriodicos, Date> colCantidadTotal1;

    public void initialize() {
        colCantidadTotal.setCellValueFactory(new PropertyValueFactory<>("cantidadTotal"));
        colCantidadPagada.setCellValueFactory(new PropertyValueFactory<>("cantidadPagada"));
        colCantidadRestante.setCellValueFactory(new PropertyValueFactory<>("cantidadRestante"));
        colDineroPlazo.setCellValueFactory(new PropertyValueFactory<>("plazosDinero"));
        colProximoPlazo.setCellValueFactory(new PropertyValueFactory<>("fechaSiguientePago"));
        colCantidadTotal1.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        ObservableList<GastosPeriodicos> PagosRecurrentesObservable = FXCollections.observableArrayList(cuenta.getListaGastos());
        tblPagosRecorrentes.setItems(PagosRecurrentesObservable);

        cmbPlazos.getItems().addAll("Meses", "Años");
        cmbPlazos.getSelectionModel().selectFirst();

        cmbTipo.getItems().addAll("Prestamo", "Hipoteca");
        cmbTipo.getSelectionModel().selectFirst();
    }

    @FXML
    public void CalcularPagoMensual() {
        if (txtCantidadTotal.getText().isEmpty() || txtInteresAnual.getText().isEmpty() ||
            txtPlazos.getText().isEmpty()) {

                txtPlazosDinero.setText("0.0");

            return;
        }
 
        Double total = Double.parseDouble(txtCantidadTotal.getText());
        Double interesAnual = Double.parseDouble(txtInteresAnual.getText());
        int plazos = Integer.parseInt(txtPlazos.getText());
        String mesesOAños = cmbPlazos.getValue();

        // Convertir la tasa de interés anual a mensual
        Double interesMensual = interesAnual / 12 / 100;
        
        if (mesesOAños.equals("Años")) {
            plazos *= 12; // Convertir años a meses
        }
        
        Double pagoMensual = total * (interesMensual * Math.pow(1 + interesMensual, plazos)) / (Math.pow(1 + interesMensual, plazos) - 1);
        String pagoMensualFormateado = String.format("%.2f", pagoMensual);
        pagoMensualFormateado = pagoMensualFormateado.replace(',', '.'); // Reemplazar coma con punto
        txtPlazosDinero.setText(pagoMensualFormateado);
    }

    @FXML
    public void NuevoPagoMensual() {
        if (txtCantidadTotal.getText().isEmpty() || txtInteresAnual.getText().isEmpty() ||
            txtPlazos.getText().isEmpty() || dtpFechaInicio.getValue() == null ||
            txtPlazosDinero.getText().isEmpty() || txtPlazosDinero.getText().equals("0.0")) {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Tienes que rellenar todos los campos.");
            alert.showAndWait();

            return;
        }

        Double total = Double.parseDouble(txtCantidadTotal.getText());
        Double interesAnual = Double.parseDouble(txtInteresAnual.getText());
        int plazos = Integer.parseInt(txtPlazos.getText());
        String mesesOAños = cmbPlazos.getValue();
        Date fechaInicio = java.sql.Date.valueOf(dtpFechaInicio.getValue());
        Double PlazosPorDinero = Double.parseDouble(txtPlazosDinero.getText());
        String tipo = cmbTipo.getValue();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.MONTH, 1); // Agregar un mes a la fecha de inicio
        Date fechaSiguientePago = calendar.getTime();

        if (mesesOAños.equals("Años")) {
            plazos *= 12; // Convertir años a meses
        }

        GastosPeriodicos gp = new GastosPeriodicos(total, fechaInicio, tipo, fechaSiguientePago, PlazosPorDinero, interesAnual, plazos);

        cuenta.getListaGastos().add(gp);
        usuario.actualizarCuenta(cuenta);

        ControladorMongoDB controlMongo = new ControladorMongoDB();
        controlMongo.ActualizarUsuario(usuario);

        App.setCuenta(cuenta);
        App.setUsuario(usuario);

        tblPagosRecorrentes.getItems().setAll(cuenta.getListaGastos());
    }
}
