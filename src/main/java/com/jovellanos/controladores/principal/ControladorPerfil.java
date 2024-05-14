package com.jovellanos.controladores.principal;

import java.text.SimpleDateFormat;

import com.jovellanos.App;
import com.jovellanos.modelo.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorPerfil {
    private Usuario usuario = App.getUsuario();

    @FXML 
    private Label lblUsuario;

    @FXML 
    private Label lblNombre;
    
    @FXML 
    private Label lblApellidos;

    @FXML 
    private Label lblDireccion;

    @FXML 
    private Label lblTelefono;

    @FXML 
    private Label lblNacimiento;

    @FXML 
    private Label lblCorreo;

    public void initialize() {
        App.getScene().getWindow().setWidth(1260);
        App.getScene().getWindow().setHeight(700);

        mostrarDatosPerfil();
    }

    @FXML
    private void ModificarPerfil() {
        App.getScene().setRoot(App.cargarEscena("fxml/FormularioPerfil.fxml"));
    }

    private void mostrarDatosPerfil() {
        if (usuario.getUsername() != null && !usuario.getUsername().isEmpty()) {
            lblUsuario.setText(usuario.getUsername());
        }
    
        if (usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
            lblNombre.setText(usuario.getNombre());
        }
    
        if (usuario.getApellidos() != null && !usuario.getApellidos().isEmpty()) {
            lblApellidos.setText(usuario.getApellidos());
        }
    
        if (usuario.getDireccion() != null && !usuario.getDireccion().isEmpty()) {
            lblDireccion.setText(usuario.getDireccion());
        }
    
        if (usuario.getTelefono() != null && !usuario.getTelefono().isEmpty()) {
            lblTelefono.setText(usuario.getTelefono());
        }
    
        if (usuario.getCorreoElectronico() != null && !usuario.getCorreoElectronico().isEmpty()) {
            lblCorreo.setText(usuario.getCorreoElectronico());
        }

        if (usuario.getFechaNacimiento() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = sdf.format(usuario.getFechaNacimiento());
            lblNacimiento.setText(fechaFormateada);
        }
    }
}
