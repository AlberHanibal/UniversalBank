package com.jovellanos;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableView;

public class ControladorTarjetas {

    @FXML
    private TableView<Tarjeta> tablaTarjetas;

    @FXML
    private void Activar() {
    }

    @FXML
    private void Desactivar() {
    }

    @FXML
    private void Perdida() {
    }

    @FXML
    private void Nueva() {
        generarTarjeta();
    }

    public void generarTarjeta() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Visa", "Mastercard", "American Express");
        dialog.setTitle("Seleccionar tipo de tarjeta");
        dialog.setHeaderText("Seleccione el tipo de tarjeta que desea generar:");
        dialog.setContentText("Tipo de tarjeta:");
        Optional<String> resultado = dialog.showAndWait();

        if (resultado.isPresent()) {
            String tipo = resultado.get();
            int id = generarIDUnico();
            String numeroTarjeta = generarNumeroTarjeta();

            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaCaducidad = fechaActual.plusYears(5);

            Tarjeta nuevaTarjeta = new Tarjeta(id, tipo, numeroTarjeta, 1000.0, "1234", fechaCaducidad);

            System.out.println(nuevaTarjeta + "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            tablaTarjetas.getItems().add(nuevaTarjeta);
            tablaTarjetas.refresh(); 
        }
    }

    public static String generarNumeroTarjeta() {
        Random rand = new Random();
        StringBuilder numero = new StringBuilder("4"); // Comienza con el prefijo de Visa

        // Genera los primeros 15 dígitos de manera aleatoria
        for (int i = 0; i < 15; i++) {
            numero.append(rand.nextInt(10)); // Agrega un dígito aleatorio
        }

        // Calcula el dígito de verificación utilizando el algoritmo de Luhn
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            int digit = Character.getNumericValue(numero.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        int checksum = (10 - (sum % 10)) % 10;
        numero.append(checksum);

        return numero.toString();
    }

    private int generarIDUnico() {
        // Utiliza UUID para generar una id unica
        return UUID.randomUUID().hashCode();
    }
}