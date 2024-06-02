package com.jovellanos.controladores.principal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

import com.jovellanos.App;
import com.jovellanos.ControladorInformes;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Movimiento;
import com.jovellanos.modelo.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

public class ControladorMovimientos {
    ControladorMongoDB controlMongo = new ControladorMongoDB();
    private Usuario usuario = App.getUsuario();
    private Cuenta cuenta = App.getCuenta();
    
    private final static ArrayList<String> nombreMeses = new ArrayList<String>(Arrays.asList("ene","feb","mar","abr","may","jun","jul","ago","sep","oct","nov","dic"));
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
    private TableColumn<Movimiento, Integer> colId;

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

        tblMovimientos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtCantidad.setText(newValue.getCantidad().toString());
                txtAsunto.setText(newValue.getAsunto());
                txtTipo.setText(newValue.getTipo());
        
                if (newValue.getFecha() != null) {
                    LocalDate localDate = newValue.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dtpFecha.setValue(localDate);
                } else {
                    dtpFecha.setValue(null);
                }
            } else {
                // Si no hay ningún elemento seleccionado, limpiar los campos de texto
                txtCantidad.clear();
                txtAsunto.clear();
                txtTipo.clear();
                dtpFecha.setValue(null);
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
        Date fecha = Date.from(dtpFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String tipo = txtTipo.getText();

        Movimiento nuevoMovimiento = new Movimiento(cantidad, asunto, fecha, tipo);

        cuenta.setBalance(cuenta.getBalance() + cantidad);
        cuenta.getHistorialMovimientos().add(nuevoMovimiento);
        usuario.actualizarCuenta(cuenta);
        tblMovimientos.getItems().setAll(cuenta.getHistorialMovimientos());

        controlMongo.ActualizarUsuario(usuario);

        App.setCuenta(cuenta);
        App.setUsuario(usuario);
    }

    @FXML
    private void ModificarMovimiento() {
        Movimiento seleccionado = tblMovimientos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Double cantidadAnterior = seleccionado.getCantidad();
            Double cantidadNueva = Double.parseDouble(txtCantidad.getText());
            String asunto = txtAsunto.getText();
            Date fecha = Date.from(dtpFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            String tipo = txtTipo.getText();

            seleccionado.setCantidad(cantidadNueva);
            seleccionado.setAsunto(asunto);
            seleccionado.setTipo(tipo);
            seleccionado.setFecha(fecha);

            cuenta.setBalance(cuenta.getBalance() - cantidadAnterior + cantidadNueva);

            usuario.actualizarCuenta(cuenta);
            controlMongo.ActualizarUsuario(usuario);

            App.setCuenta(cuenta);
            App.setUsuario(usuario);

            tblMovimientos.refresh();
        }
    }

    @FXML
    private void EliminarMovimiento() {
        Movimiento seleccionado = tblMovimientos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Double cantidad = seleccionado.getCantidad();
    
            cuenta.setBalance(cuenta.getBalance() - cantidad);
            cuenta.getHistorialMovimientos().remove(seleccionado);
            
            usuario.actualizarCuenta(cuenta);
            controlMongo.ActualizarUsuario(usuario);
    
            App.setCuenta(cuenta);
            App.setUsuario(usuario);
    
            ObservableList<Movimiento> movimientosObservable = FXCollections.observableArrayList(cuenta.getHistorialMovimientos());
            tblMovimientos.setItems(movimientosObservable);
    
            tblMovimientos.refresh();
        }
    }

    @FXML
    private void generarInforme() {
        crearJSONMovimientos();
        ControladorInformes.generarInforme();
    }

    private void crearJSONMovimientos() {
        ArrayList<YearMonth> listaMeses = generarUltimos12Meses();
        ArrayList<Movimiento> listaMovimientos = cuenta.getHistorialMovimientos();
        YearMonth fechaComparar;
        LocalDate fechaMovimiento;
        double ingresos;
        double gastos;
        JSONArray mesesArray = new JSONArray();
        for (YearMonth yearMonth : listaMeses) {
            JSONObject itemJSON = new JSONObject();
            ingresos = 0;
            gastos = 0;
            for (Movimiento movimiento : listaMovimientos) {
                fechaMovimiento = movimiento.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                fechaComparar = YearMonth.of(fechaMovimiento.getYear(), fechaMovimiento.getMonthValue());
                if (yearMonth.equals(fechaComparar)) {
                    if (movimiento.getCantidad() >= 0) {
                        ingresos += movimiento.getCantidad();
                    } else {
                        gastos += Math.abs(movimiento.getCantidad());
                    }
                }
            }
            if (yearMonth.getMonthValue() == 12 || yearMonth.getMonthValue() == 1) {
                itemJSON.put("mes", nombreMeses.get(yearMonth.getMonthValue() - 1) + "-" + yearMonth.getYear());
            } else {
                itemJSON.put("mes", nombreMeses.get(yearMonth.getMonthValue() - 1));
            }
            itemJSON.put("ingresos", ingresos);
            itemJSON.put("gastos", gastos);
            mesesArray.put(itemJSON);
        }

        try (FileWriter file = new FileWriter("./meses.json")) {
            file.write(mesesArray.toString(4)); // '4' para indentación y hacer el JSON más legible
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Fallo al escribir el JSON de los movimientos.");
        }
    }

    private ArrayList<YearMonth> generarUltimos12Meses() {
        ArrayList<YearMonth> listaMeses = new ArrayList<YearMonth>();
        LocalDate fecha = LocalDate.now();
        for (int i = 1; i <= 12; i++) {
            listaMeses.add(0, (YearMonth.of(fecha.getYear(), fecha.getMonthValue())));
            fecha = fecha.minusMonths(1);
        }
        return listaMeses;
    }
}
