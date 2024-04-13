package com.jovellanos;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControladorFormulario {

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private PasswordField TextFieldContraseña;

    @FXML
    private TextField TextFieldNombre;

    @FXML
    private TextField TextFieldApellidos;

    @FXML
    private Label LabelUsuario;

    @FXML
    private Label LabelContraseña;

    @FXML
    private void Continuar() throws IOException {
        String usuario = TextFieldUsuario.getText();
        String contraseña = TextFieldContraseña.getText();
        String nombre = TextFieldNombre.getText();
        String apellidos = TextFieldApellidos.getText();

        // Comprobar campos obligatorios y poner en rojo los que esten vacios
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            if (usuario.isEmpty()) {
                LabelUsuario.setTextFill(Color.RED);
            } else {
                LabelUsuario.setTextFill(Color.BLACK);
            }

            if (contraseña.isEmpty()) {
                LabelContraseña.setTextFill(Color.RED);
            } else {
                LabelContraseña.setTextFill(Color.BLACK);
            }

            return;
        }

        ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
        Usuario u = new Usuario(usuario, contraseña, lista, nombre, apellidos);

        System.out.println(u.getUsuario() + " " + u.getContraseña() + " " + u.getNombre() + " " + u.getApellidos()); // Codigo
                                                                                                                     // temporal
                                                                                                                     // para
                                                                                                                     // comprobar
                                                                                                                     // que
                                                                                                                     // se
                                                                                                                     // crea
                                                                                                                     // el
                                                                                                                     // usuario
                                                                                                                     // bien

        Stage stage = (Stage) TextFieldUsuario.getScene().getWindow(); // Cerrar la ventana una vez creado el usuario
        stage.close();
    }
}