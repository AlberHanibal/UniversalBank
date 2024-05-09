package com.jovellanos.controladores.principal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.jovellanos.App;
import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Movimiento;
import com.jovellanos.modelo.Tarjeta;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.SubScene;

public class ControladorPrincipal {
    private Usuario usuario = App.getUsuario();

    @FXML
    private SubScene sceneCentral;

    private void cambiarCentral(String fichero) {
        sceneCentral.setRoot(App.cargarEscena(fichero));
    }

    public void initialize() {
        App.getScene().getWindow().setWidth(1500);
        App.getScene().getWindow().setHeight(850);

        for (Cuenta c : usuario.getListaCuentas()) {
            if (c.getHistorialMovimientos().isEmpty()) {
                c.setHistorialMovimientos(crearMovimientos()); // Codigo temporal para generar movimientos falsos
                c.setListaTarjetas(crearTarjetas()); // Codigo temporal para generar tarjetas falsas (Pueden salir 0)

                Random random = new Random();
                double balance = random.nextDouble() * 10000;
                balance = Math.round(balance * 100.0) / 100.0;
                c.setBalance(balance);
            }
        }

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
    private void clickHipotecas() {
        cambiarCentral("fxml/principal/Hipotecas.fxml");
    }

    @FXML
    private void clickPrestamos() {
        cambiarCentral("fxml/principal/Prestamos.fxml");
    }

    @FXML
    private void clickPerfil() {
        cambiarCentral("fxml/principal/Perfil.fxml");
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

    private static ArrayList<Tarjeta> crearTarjetas() { // Codigo temporal para generar Tarjetas -------------------------------------------------
        ArrayList<Tarjeta> listaTarjetas = new ArrayList<>();
    
        Random random = new Random();
        int num = random.nextInt(5);
    
        // Array de tipos de tarjetas disponibles
        String[] tiposTarjeta = {"Crédito", "Débito", "Prepago", "Viaje", "Recompensas", "Corporativa"};
    
        for (int i = 0; i < num; i++) {
            int id = i + random.nextInt(1000000000);

            String tipo = tiposTarjeta[random.nextInt(tiposTarjeta.length)];

            String NumeroTarjeta = generateCreditCardNumber();

            String CVV = String.format("%03d", random.nextInt(1000));

            double limiteDiario = random.nextDouble() * 2000;
            limiteDiario = Math.round(limiteDiario * 100.0) / 100.0;

            String pin = generateRandomPin();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, random.nextInt(5) + 1); // Fecha de caducidad aleatoria, entre 1 y 5 años desde la fecha actual
            Date fechaCaducidad = cal.getTime();
    
            Tarjeta tarjeta = new Tarjeta(id, tipo, NumeroTarjeta, CVV, limiteDiario, pin, fechaCaducidad);
            listaTarjetas.add(tarjeta);
        }
    
        return listaTarjetas;
    }

    private static String generateRandomPin() {
        Random random = new Random();
        StringBuilder pinBuilder = new StringBuilder();

        // Genera un PIN de 4 dígitos
        for (int i = 0; i < 4; i++) {
            pinBuilder.append(random.nextInt(10));
        }

        return pinBuilder.toString();
    }

    private static String generateCreditCardNumber() {
        Random random = new Random();
        StringBuilder cardNumberBuilder = new StringBuilder();
    
        // Genera los primeros 15 dígitos (números aleatorios)
        for (int i = 0; i < 16; i++) {
            if (i > 0 && i % 4 == 0) { // Agrega un guion después de cada grupo de cuatro dígitos
                cardNumberBuilder.append("-");
            }
            cardNumberBuilder.append(random.nextInt(10));
        }
    
        // Agrega un dígito de verificación utilizando el algoritmo de Luhn
        String partialCardNumber = cardNumberBuilder.toString();
        int sum = 0;
        boolean doubleDigit = false;
        for (int i = partialCardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(partialCardNumber.charAt(i));
            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            doubleDigit = !doubleDigit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        cardNumberBuilder.append(checkDigit);
    
        return cardNumberBuilder.toString();
    }
}
