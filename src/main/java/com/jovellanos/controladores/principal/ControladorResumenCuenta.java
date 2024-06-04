package com.jovellanos.controladores.principal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jovellanos.App;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Usuario;
import com.jovellanos.modelo.Movimiento;
import com.jovellanos.modelo.Tarjeta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ControladorResumenCuenta {
    private Usuario usuario = App.getUsuario();
    private Cuenta cuenta = App.getCuenta();
    private Tarjeta tarjeta = new Tarjeta();

    private int paginaActual = 1;
    private int tamañoPagina = 10;

    @FXML
    private TableView<Movimiento> tblMovimientos;

    @FXML
    private Label lblID;

    @FXML
    private Label lblBalance;

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
    private Label lblPaginaActual;

    @FXML
    private Label lblTotalPaginas;

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
        tarjeta = RastrearTarjeta("Primera");

        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCantidad.setReorderable(false);
        colCantidad.setResizable(false);

        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        colAsunto.setReorderable(false);
        colAsunto.setResizable(false);

        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setReorderable(false);
        colFecha.setResizable(false);

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colTipo.setReorderable(false);
        colTipo.setResizable(false);

        colFecha.setCellFactory(new Callback<TableColumn<Movimiento, Date>, TableCell<Movimiento, Date>>() {
            @Override
            public TableCell<Movimiento, Date> call(TableColumn<Movimiento, Date> param) {
                return new TableCell<Movimiento, Date>() {
                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            setText(dateFormat.format(item));
                        }
                    }
                };
            }
        });

        mostrarDatosTarjeta();
        mostrarDatosCuenta();
        escaladoFlechas();

        mostrarPaginas();
        actualizarTabla();
    }

    @FXML
    private void CuentaAnterior() {
        cuenta = RastrearCuenta("Anterior");
        App.setCuenta(cuenta);

        mostrarPaginas();
        actualizarTabla();

        tarjeta = RastrearTarjeta("Primera");

        mostrarDatosCuenta();
        mostrarDatosTarjeta();
    }

    @FXML
    private void CuentaSiguiente() {
        cuenta = RastrearCuenta("Siguiente");
        App.setCuenta(cuenta);

        mostrarPaginas();
        actualizarTabla();

        tarjeta = RastrearTarjeta("Primera");

        mostrarDatosCuenta();
        mostrarDatosTarjeta();
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

    @FXML
    private void VerTodosMovimientos() {
        App.cambiarEscenaCentral("fxml/principal/Movimientos.fxml");
    }

    private Cuenta RastrearCuenta(String buscada) { // Iteramos sobre la lista de cuentas, para determinar cual es la siguiente o anterior cuenta.
        List<Cuenta> listaCuentas = usuario.getListaCuentas();
        if (listaCuentas.size() <= 1) {
            // Si hay una sola cuenta, simplemente devuelve esa cuenta
            return cuenta;
        }

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

    private void mostrarDatosCuenta() {
        lblID.setText(String.valueOf("ID: " + cuenta.getId()));
        lblBalance.setText(String.valueOf("Balance: " + cuenta.getBalance() + " €"));
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
        } else {
            // Si hay tarjetas, mostrar los detalles de la tarjeta actual
            lblTipo.setText(String.valueOf("Tipo: " + tarjeta.getTipo()));
            lblNumero.setText(String.valueOf(tarjeta.getNumeroTarjeta()));
            lblCVV.setText(String.valueOf("Código CVV: " + tarjeta.getCVV()));
            lblLimite.setText((String.valueOf("Límite Diario: " + tarjeta.getLimiteDiario())));
            lblPin.setText((String.valueOf("Código Pin: " + tarjeta.getPin())));
            lblCaducidad.setText((String.valueOf("Caduca el: " + tarjeta.getFechaCaducidad())));
        }
    }

    private void actualizarTabla() {
        int startIndex = (paginaActual - 1) * tamañoPagina;
        int endIndex = Math.min(startIndex + tamañoPagina, cuenta.getHistorialMovimientos().size());
        
        ObservableList<Movimiento> movimientosPaginados = FXCollections.observableArrayList(cuenta.getHistorialMovimientos().subList(startIndex, endIndex));
        tblMovimientos.setItems(movimientosPaginados);
    }

    private void mostrarPaginas() {
        int totalPaginas = (int) Math.ceil((double) cuenta.getHistorialMovimientos().size() / tamañoPagina);
        lblPaginaActual.setText(String.valueOf(paginaActual));
        lblTotalPaginas.setText(String.valueOf(totalPaginas));
    }
    
    @FXML
    private void paginaAnterior() {
        if (paginaActual > 1) {
            paginaActual--;

            actualizarTabla();
            mostrarPaginas();
        }
    }
    
    @FXML
    private void paginaSiguiente() {
        int totalPaginas = (int) Math.ceil((double) cuenta.getHistorialMovimientos().size() / tamañoPagina);
        if (paginaActual < totalPaginas) {
            paginaActual++;

            actualizarTabla();
            mostrarPaginas();
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
}
