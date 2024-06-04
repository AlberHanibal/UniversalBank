package com.jovellanos.controladores.principal;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Tarjeta;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class ControladorTarjetas {

    private Usuario usuario = App.getUsuario();
    private Cuenta cuenta = App.getCuenta();
    private Tarjeta tarjeta = new Tarjeta();

    @FXML 
    private Label lblIDTarjeta;

    @FXML 
    private Label lblTipo;
    
    @FXML 
    private Label lblNumero;

    @FXML 
    private Label lblCVV;

    @FXML 
    private Label lblLimite;

    @FXML 
    private Label lblPin;

    @FXML 
    private Label lblCaducidad;

    @FXML
    private Label lblEstado;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtCVV;

    @FXML
    private TextField txtPin;

    @FXML
    private TextField txtLimite;

    @FXML 
    private ComboBox<String> cmbTipo;

    @FXML
    private DatePicker dtpCaducidad;

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

    @FXML
    private Button btnSiguienteTarjeta;

    @FXML
    private Button btnAnteriorTarjeta;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnReactivar;

    @FXML
    private Button btnBloquear;

    @FXML
    private Button btnDesbloquear;

    @FXML
    private ImageView imgSiguienteTarjeta;

    @FXML
    private ImageView imgAnteriorTarjeta;


    public void initialize() {

        tarjeta = RastrearTarjeta("Primera");

        mostrarDatosTarjeta();
        escaladoFlechas();

        cmbTipo.getItems().addAll("Credito", "Debito");
        cmbTipo.getSelectionModel().selectFirst();

        txtNumero.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevaTarjeta();
            }
        });

        txtCVV.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevaTarjeta();
            }
        });

        txtPin.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevaTarjeta();
            }
        });

        txtLimite.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevaTarjeta();
            }
        });

        dtpCaducidad.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                NuevaTarjeta();
            }
        });
    }

    @FXML
    private void NuevaTarjeta() {
        if (txtLimite.getText().isEmpty() || dtpCaducidad.getValue() == null || 
            txtNumero.getText().isEmpty() || txtCVV.getText().isEmpty() || txtPin.getText().isEmpty()) {
            return;
        }

        Random random = new Random();
        int id = random.nextInt(1000000000);

        Double limit = Double.parseDouble(txtLimite.getText());
        Date fechaCaducidad = java.sql.Date.valueOf(dtpCaducidad.getValue());

        Tarjeta nuevaTarjeta = new Tarjeta(id, cmbTipo.getValue(), txtNumero.getText(), txtCVV.getText(), limit, txtPin.getText(), fechaCaducidad);

        cuenta.getListaTarjetas().add(nuevaTarjeta);
        usuario.actualizarCuenta(cuenta);

        ControladorMongoDB controlMongo = new ControladorMongoDB();
        controlMongo.ActualizarUsuario(usuario);

        App.setCuenta(cuenta);
        App.setUsuario(usuario);
    }

    @FXML
    private void CancelarTarjeta() {

        if (tarjeta != null) {
            tarjeta.setCancelada(true);

            mostrarDatosTarjeta();
        }
    }

    @FXML
    private void ReactivarTarjeta() {

        if (tarjeta != null) {
            tarjeta.setCancelada(false);

            mostrarDatosTarjeta();
        }
    }

    @FXML
    private void BloquearTarjeta() {

        if (tarjeta != null) {
            tarjeta.setBloqueada(true);

            mostrarDatosTarjeta();
        }
    }

    @FXML
    private void DesbloquearTarjeta() {

        if (tarjeta != null) {
            tarjeta.setBloqueada(false);

            mostrarDatosTarjeta();
        }
    }

    @FXML
    private void TarjetaAnterior() {
        tarjeta = RastrearTarjeta("Anterior");

        mostrarDatosTarjeta();
    }

    @FXML
    private void TarjetaSiguiente() {
        tarjeta = RastrearTarjeta("Siguiente");

        mostrarDatosTarjeta();
    }

    private Tarjeta RastrearTarjeta(String buscada) {
        List<Tarjeta> listaTarjetas = cuenta.getListaTarjetas();

        if (listaTarjetas.isEmpty()) {
            Tarjeta t = new Tarjeta();
            return t;
        }

        int idTarjetaActual = tarjeta.getId();
        int indiceActual = -1;

        // Buscar el índice de la tarjeta actual
        for (int i = 0; i < listaTarjetas.size(); i++) {
            if (listaTarjetas.get(i).getId() == idTarjetaActual) {
                indiceActual = i;
                break;
            }
        }

        // Obtener los índices de la tarjeta anterior y siguiente
        int indiceAnterior = (indiceActual - 1 + listaTarjetas.size()) % listaTarjetas.size();
        int indiceSiguiente = (indiceActual + 1) % listaTarjetas.size();
        
        // Obtener las tarjetas anterior y siguiente
        Tarjeta tarjetaAnterior = listaTarjetas.get(indiceAnterior);
        Tarjeta tarjetaSiguiente = listaTarjetas.get(indiceSiguiente);

        // Determinar qué tarjeta retornar basado en el valor de "buscada"
        if (buscada.equals("Siguiente")) {
            return tarjetaSiguiente;
        } else if (buscada.equals("Anterior")) {
            return tarjetaAnterior;
        } else {
            // Si "buscada" no es "Siguiente" ni "Anterior", se asume "Primera"
            return listaTarjetas.get(0); // Retorna la primera tarjeta en la lista
        }
    }

    private void mostrarDatosTarjeta() {
        if (cuenta.getListaTarjetas().isEmpty()) {
            // Si no hay tarjetas, mostrar un mensaje en las etiquetas
            lblTipo.setText("");
            lblNumero.setText("Sin tarjetas");
            lblCVV.setText("");
            lblLimite.setText("");
            lblPin.setText("");
            lblCaducidad.setText("");
            lblEstado.setText("");
        } else {
            // Si hay tarjetas, mostrar los detalles de la tarjeta actual
            lblTipo.setText(String.valueOf("Tipo: " + tarjeta.getTipo()));
            lblNumero.setText(String.valueOf(tarjeta.getNumeroTarjeta()));
            lblCVV.setText(String.valueOf("Código CVV: " + tarjeta.getCVV()));
            lblLimite.setText((String.valueOf("Límite Diario: " + tarjeta.getLimiteDiario())));
            lblPin.setText((String.valueOf("Código Pin: " + tarjeta.getPin())));
            lblCaducidad.setText((String.valueOf("Caduca el: " + tarjeta.getFechaCaducidad())));

            if (tarjeta.isBloqueada()) {
                btnBloquear.setVisible(false);
                btnDesbloquear.setVisible(true);
                btnCancelar.setVisible(false);
                btnReactivar.setVisible(false);

                lblEstado.setText(String.valueOf("Estado: Bloqueada"));
                lblEstado.setTextFill(Color.RED);
            } else {
                if (tarjeta.isCancelada()) {
                    btnBloquear.setVisible(false);
                    btnDesbloquear.setVisible(false);
                    btnCancelar.setVisible(false);
                    btnReactivar.setVisible(true);

                    lblEstado.setText(String.valueOf("Estado: Cancelada"));
                    lblEstado.setTextFill(Color.RED);
                } else {
                    btnBloquear.setVisible(true);
                    btnDesbloquear.setVisible(false);
                    btnCancelar.setVisible(true);
                    btnReactivar.setVisible(false);

                    lblEstado.setText(String.valueOf("Estado: Activa"));
                    lblEstado.setTextFill(Color.BLACK);
                }
            }
        }
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

        // Restaura la imagen a su tamaño original ---------------------------------
        btnSiguienteTarjeta.setOnMouseReleased(event -> {
            imgSiguienteTarjeta.setScaleX(1.0);
            imgSiguienteTarjeta.setScaleY(1.0);
        });

        btnAnteriorTarjeta.setOnMouseReleased(event -> {
            imgAnteriorTarjeta.setScaleX(1.0);
            imgAnteriorTarjeta.setScaleY(1.0);
        });
        // -------------------------------------------------------------------------
    }
}