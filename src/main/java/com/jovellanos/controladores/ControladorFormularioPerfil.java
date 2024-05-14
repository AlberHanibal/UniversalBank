package com.jovellanos.controladores;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.jovellanos.App;
import com.jovellanos.ControladorMongoDB;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorFormularioPerfil {
    private Usuario usuario = App.getUsuario();

    @FXML
    private TextField TextFieldUsuario;

    @FXML
    private PasswordField TextFieldContraseña;

    @FXML
    private PasswordField TextFieldConfirmarPass;

    @FXML
    private TextField TextFieldNombre;

    @FXML
    private TextField TextFieldApellidos;

    @FXML
    private TextField TextFieldDireccion;

    @FXML
    private TextField TextFieldTelefono;

    @FXML
    private TextField TextFieldCorreo;

    @FXML
    private DatePicker DatePickerNacimiento;

    public void initialize() {
        App.getScene().getWindow().setWidth(800);
        App.getScene().getWindow().setHeight(600);

        mostrarDatosPerfil();
    }

    @FXML
    private void Guardar() {
        String contraseña = TextFieldContraseña.getText();
        String confirmarPass = TextFieldConfirmarPass.getText();

        // Se comprueba que las contraseñas coincidan
        if (!contraseña.equals(confirmarPass)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Las contraseñas no coinciden.");
            alert.showAndWait();

            return;
        }

        usuario.setUsername(TextFieldUsuario.getText());
        usuario.setNombre(TextFieldNombre.getText());
        usuario.setApellidos(TextFieldApellidos.getText());
        usuario.setDireccion(TextFieldDireccion.getText());
        usuario.setTelefono(TextFieldTelefono.getText());
        usuario.setCorreoElectronico(TextFieldCorreo.getText());
        usuario.setContraseña(TextFieldContraseña.getText());

        LocalDate localDate = DatePickerNacimiento.getValue();
        if (localDate != null) {
            Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(instant);
            usuario.setFechaNacimiento(date);
        }

        ControladorMongoDB ControlMongo = new ControladorMongoDB();
        ControlMongo.ActualizarUsuario(usuario);

        App.setUsuario(usuario);
        App.getScene().setRoot(App.cargarEscena("fxml/principal/Principal.fxml"));
    }

    @FXML
    private void Cancelar() {
        App.getScene().setRoot(App.cargarEscena("fxml/principal/Principal.fxml"));
    }

    private void mostrarDatosPerfil() {
        String username = usuario.getUsername();
        if (username != null && !username.isEmpty()) {
            TextFieldUsuario.setText(username);
        }
    
        String nombre = usuario.getNombre();
        if (nombre != null && !nombre.isEmpty()) {
            TextFieldNombre.setText(nombre);
        }
    
        String apellidos = usuario.getApellidos();
        if (apellidos != null && !apellidos.isEmpty()) {
            TextFieldApellidos.setText(apellidos);
        }
    
        String direccion = usuario.getDireccion();
        if (direccion != null && !direccion.isEmpty()) {
            TextFieldDireccion.setText(direccion);
        }
    
        String telefono = usuario.getTelefono();
        if (telefono != null && !telefono.isEmpty()) {
            TextFieldTelefono.setText(telefono);
        }

        String correoElectronico = usuario.getCorreoElectronico();
        if (correoElectronico != null && !correoElectronico.isEmpty()) {
            TextFieldCorreo.setText(correoElectronico);
        }

        String contraseña = usuario.getContraseña();
        if (contraseña != null && !contraseña.isEmpty()) {
            TextFieldContraseña.setText(contraseña);
            TextFieldConfirmarPass.setText(contraseña);
        }

        if (usuario.getFechaNacimiento() != null) {
            Instant instant = usuario.getFechaNacimiento().toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            DatePickerNacimiento.setValue(localDate);
        }
    }
}