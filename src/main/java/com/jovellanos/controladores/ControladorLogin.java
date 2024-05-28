package com.jovellanos.controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.GastosPeriodicos;
import com.jovellanos.modelo.Movimiento;
import com.jovellanos.modelo.Tarjeta;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class ControladorLogin {
    ControladorMongoDB controlMongo = new ControladorMongoDB();

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtContraseña;

    @FXML
    private PasswordField pswContraseña;

    private boolean mostrarContraseña = false;

    public void initialize() {
        if (App.getScene() != null) {
            App.getScene().getWindow().setWidth(600);
            App.getScene().getWindow().setHeight(400);
        }

        if (!controlMongo.ComprobarUsuario("Administrador")) {
            CrearGrupoUsuarios();
        }

        txtUsuario.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Continuar();
            }
        });

        pswContraseña.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Continuar();
            }
        });

        txtContraseña.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Continuar();
            }
        });
    }

    @FXML
    private void Continuar() {
        String username = txtUsuario.getText();
        String contraseña = mostrarContraseña ? txtContraseña.getText() : pswContraseña.getText();

        ControladorMongoDB ControlMongo = new ControladorMongoDB();
        Boolean existe = ControlMongo.ComprobarUsuarioYContraseña(username, contraseña);

        if (existe) {
            Usuario usuario = ControlMongo.buscarUsuarioPorNombre(username);
            App.setUsuario(usuario);
            App.getScene().setRoot(App.cargarEscena("fxml/SelectorCuenta.fxml"));
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El usuario o la contraseña son incorrectos.");
            alert.showAndWait();

            return;
        }
    }

    @FXML
    private void CrearUsuario() {
        App.getScene().setRoot(App.cargarEscena("fxml/Formulario.fxml"));
    }

    @FXML
    private void mostrarContraseña() {
        mostrarContraseña = !mostrarContraseña;

        if (mostrarContraseña) {
            txtContraseña.setText(pswContraseña.getText());
            txtContraseña.setVisible(true);
            txtContraseña.setManaged(true);
            pswContraseña.setVisible(false);
            pswContraseña.setManaged(false);
        } else {
            pswContraseña.setText(txtContraseña.getText());
            pswContraseña.setVisible(true);
            pswContraseña.setManaged(true);
            txtContraseña.setVisible(false);
            txtContraseña.setManaged(false);
        }
    }

    private void CrearGrupoUsuarios() {
        String username = "usuario";
        String contraseña = "usuario";
        String nombre = "Julián";
        String apellidos = "Palacios García";
        String direccion = "c/ De la Programación 14 2ºB";
        String Telefono = "616190122";
        String CorreoElectronico = "JulianPalacios@Gmail.es";

        ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

        ArrayList<Tarjeta> listaTarjetas = CrearGrupoTarjetas1();
        ArrayList<Movimiento> listaMovimientos = CrearGrupoMovimientos1();
        ArrayList<GastosPeriodicos> listaGastos = CrearGrupoGastos1();

        ArrayList<Tarjeta> listaTarjetas2 = new ArrayList<Tarjeta>();
        ArrayList<Movimiento> listaMovimientos2 = CrearGrupoMovimientos2();
        ArrayList<GastosPeriodicos> listaGastos2 = new ArrayList<GastosPeriodicos>();

        ArrayList<Tarjeta> listaTarjetas3 = CrearGrupoTarjetas3();
        ArrayList<Movimiento> listaMovimientos3 = CrearGrupoMovimientos3();
        ArrayList<GastosPeriodicos> listaGastos3 = new ArrayList<GastosPeriodicos>();

        Cuenta cuenta1 = new Cuenta(673932532, 15310.2, listaTarjetas, listaMovimientos, listaGastos);
        Cuenta cuenta2 = new Cuenta(489301204, 450.99, listaTarjetas2, listaMovimientos2, listaGastos2);
        Cuenta cuenta3 = new Cuenta(062373263, 2100.0, listaTarjetas3, listaMovimientos3, listaGastos3);
        
        listaCuentas.add(cuenta1);
        listaCuentas.add(cuenta2);
        listaCuentas.add(cuenta3);

        Usuario userAdmin = new Usuario(username, contraseña, listaCuentas, nombre, apellidos);

        userAdmin.setDireccion(direccion);
        userAdmin.setTelefono(Telefono);
        userAdmin.setCorreoElectronico(CorreoElectronico);

        username = "a";
        contraseña = "a";

        Usuario a = new Usuario(username, contraseña, listaCuentas, nombre, apellidos);

        a.setDireccion(direccion);
        a.setTelefono(Telefono);
        a.setCorreoElectronico(CorreoElectronico);

        controlMongo.guardarUsuario(userAdmin);
        controlMongo.guardarUsuario(a);
    }

    private ArrayList<Movimiento> CrearGrupoMovimientos1() {
        ArrayList<Movimiento> historialMovimientos = new ArrayList<>();
        //---------------------Movimiento 1----------------------------------------
        Movimiento movimiento = new Movimiento();
        movimiento.setCantidad(5000.0);
        movimiento.setAsunto("Abriendo la cuenta");
        movimiento.setTipo("Ingreso");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse("14/10/2022");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 2----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(2500.0);
        movimiento.setAsunto("Regalo de un amigo");
        movimiento.setTipo("Ingreso");

        try {
            Date date = dateFormat.parse("1/2/2023");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 3----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(-390.5);
        movimiento.setAsunto("Prestamo a pagar");
        movimiento.setTipo("Pago");

        try {
            Date date = dateFormat.parse("20/6/2023");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 4----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(500.0);
        movimiento.setAsunto("Premio recibido");
        movimiento.setTipo("Ingreso");

        try {
            Date date = dateFormat.parse("25/6/2023");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 5----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(-506.69);
        movimiento.setAsunto("Pago de la Hipoteca");
        movimiento.setTipo("Hipoteca");

        try {
            Date date = dateFormat.parse("1/1/2024");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 6----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(-506.69);
        movimiento.setAsunto("Pago de la Hipoteca");
        movimiento.setTipo("Hipoteca");

        try {
            Date date = dateFormat.parse("1/2/2024");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 7----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(9500.0);
        movimiento.setAsunto("Ganancias");
        movimiento.setTipo("Ingreso");

        try {
            Date date = dateFormat.parse("16/4/2024");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 8----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(-286.12);
        movimiento.setAsunto("Deuda a un amigo");
        movimiento.setTipo("Pago");

        try {
            Date date = dateFormat.parse("27/5/2024");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        return historialMovimientos;
    }

    private ArrayList<Movimiento> CrearGrupoMovimientos2() {
        ArrayList<Movimiento> historialMovimientos = new ArrayList<>();
        //---------------------Movimiento 1----------------------------------------
        Movimiento movimiento = new Movimiento();
        movimiento.setCantidad(1000.0);
        movimiento.setAsunto("Abriendo la cuenta");
        movimiento.setTipo("Ingreso");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse("7/2/2024");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 2----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(330.01);
        movimiento.setAsunto("Compra en tienda");
        movimiento.setTipo("Pago");

        try {
            Date date = dateFormat.parse("11/3/2024");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        //---------------------Movimiento 3----------------------------------------
        movimiento = new Movimiento();
        movimiento.setCantidad(-219.0);
        movimiento.setAsunto("Compra en tienda");
        movimiento.setTipo("Pago");

        try {
            Date date = dateFormat.parse("29/3/2023");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        return historialMovimientos;
    }

    private ArrayList<Movimiento> CrearGrupoMovimientos3() {
        ArrayList<Movimiento> historialMovimientos = new ArrayList<>();
        //---------------------Movimiento 1----------------------------------------
        Movimiento movimiento = new Movimiento();
        movimiento.setCantidad(2100.0);
        movimiento.setAsunto("Abriendo la cuenta");
        movimiento.setTipo("Ingreso");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse("12/5/2024");
            movimiento.setFecha(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        historialMovimientos.add(movimiento);

        return historialMovimientos;
    }

    private ArrayList<Tarjeta> CrearGrupoTarjetas1() {
        ArrayList<Tarjeta> listaTarjetas = new ArrayList<>();
        //---------------------Tarjeta 1----------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse("25/8/2026");
            Tarjeta nuevaTarjeta = new Tarjeta(532910252, "Credito", "4728-1250-0192-2495", "831", 500.0, "8102", date);

            listaTarjetas.add(nuevaTarjeta);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //---------------------Tarjeta 2----------------------------------------
        try {
            Date date = dateFormat.parse("15/3/2025");
            Tarjeta nuevaTarjeta = new Tarjeta(216399039, "Credito", "3284-0924-8432-2285", "188", 200.0, "1395", date);
            
            listaTarjetas.add(nuevaTarjeta);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //---------------------Tarjeta 3----------------------------------------
        try {
            Date date = dateFormat.parse("1/1/2025");
            Tarjeta nuevaTarjeta = new Tarjeta(593010298, "Debito", "0992-1374-6431-0192", "741", 250.0, "9412", date);
            
            listaTarjetas.add(nuevaTarjeta);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return listaTarjetas;
    }

    private ArrayList<Tarjeta> CrearGrupoTarjetas3() {
        ArrayList<Tarjeta> listaTarjetas = new ArrayList<>();
        //---------------------Tarjeta 1----------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse("21/3/2026");
            Tarjeta nuevaTarjeta = new Tarjeta(68301002, "Credito", "2860-2012-2068-9175", "108", 500.0, "9376", date);

            listaTarjetas.add(nuevaTarjeta);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listaTarjetas;
    }

    private ArrayList<GastosPeriodicos> CrearGrupoGastos1() {
        ArrayList<GastosPeriodicos> listaGastos = new ArrayList<>();
        //---------------------Gasto 1----------------------------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaInicio = dateFormat.parse("1/1/2024");
            Date fechaSiguientePago = dateFormat.parse("1/6/2024");

            GastosPeriodicos gastoPeriodico = new GastosPeriodicos(100000.0, fechaInicio, "Hipoteca", fechaSiguientePago, 506.69, 4.5, 360);
            gastoPeriodico.setCantidadPagada(1013.38);
            gastoPeriodico.setCantidadRestante(98986.62);

            listaGastos.add(gastoPeriodico);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        
        return listaGastos;
    }
}