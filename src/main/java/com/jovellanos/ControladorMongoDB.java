package com.jovellanos;

import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class ControladorMongoDB {
    // Variables de conexión a MongoDB
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public ControladorMongoDB() {
        // Configurar conexión a MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("UniversalDatas");
        collection = database.getCollection("Usuarios");
    }

    public void guardarUsuario(Usuario usuario) {
        // Lógica para guardar un usuario en la base de datos
        Document doc = new Document("username", usuario.getUsername())
                         .append("contraseña", usuario.getContraseña())
                         .append("nombre", usuario.getNombre())
                         .append("apellidos", usuario.getApellidos());
                         
        collection.insertOne(doc);
    }

    public boolean ComprobarUsuario(String username, String contraseña) {
        // Crear una consulta para buscar el usuario por su nombre de usuario y contraseña
        Document query = new Document("username", username)
                            .append("contraseña", contraseña);

        // Ejecutar la consulta en la colección de usuarios
        FindIterable<Document> result = collection.find(query);

        // Iterar sobre los resultados para verificar si el usuario existe
        MongoCursor<Document> cursor = result.iterator();
        boolean encontrado = cursor.hasNext();

        // Cerrar el cursor
        cursor.close();

        return encontrado;
    }

    public Usuario buscarUsuarioPorNombre(String username) {
        Document query = new Document("username", username);
        FindIterable<Document> result = collection.find(query);
        Document usuarioDoc = result.first();

        String contraseña = usuarioDoc.getString("contraseña");
        String nombre = usuarioDoc.getString("nombre");
        String apellidos = usuarioDoc.getString("apellidos");

        ArrayList<Cuenta> listaCuentas = new ArrayList<>();
        ArrayList<Document> cuentasDocs = (ArrayList<Document>) usuarioDoc.get("listaCuentas");
        for (Document cuentaDoc : cuentasDocs) {
            int id = cuentaDoc.getInteger("id");
            Double balance = cuentaDoc.getDouble("balance");

            ArrayList<Tarjeta> listaTarjetas = new ArrayList<>();
            ArrayList<Document> tarjetasDocs = (ArrayList<Document>) cuentaDoc.get("listaTarjetas");
            for (Document tarjetaDoc : tarjetasDocs) {
                int tarjetaId = tarjetaDoc.getInteger("id");
                Double limiteDiario = tarjetaDoc.getDouble("limiteDiario");
                String pin = tarjetaDoc.getString("pin");
                Date fechaCaducidad = tarjetaDoc.getDate("fechaCaducidad");
                listaTarjetas.add(new Tarjeta(tarjetaId, limiteDiario, pin, fechaCaducidad)); 
            }
            
            ArrayList<Movimiento> historialMovimientos = new ArrayList<>();
            ArrayList<Document> MovimientosDocs = (ArrayList<Document>) cuentaDoc.get("historialMovimientos");
            for (Document MovimientosDoc : MovimientosDocs) {
                Double cantidad = MovimientosDoc.getDouble("cantidad");
                String asunto = MovimientosDoc.getString("asunto");
                Date fecha = MovimientosDoc.getDate("fecha");
                String tipo = MovimientosDoc.getString("tipo");
                historialMovimientos.add(new Movimiento(cantidad, asunto, fecha, tipo)); 
            }

            ArrayList<GastosPeriodicos> listaGastos = new ArrayList<>();
            ArrayList<Document> GastosPeriodicosDocs = (ArrayList<Document>) cuentaDoc.get("listaGastos");
            for (Document GastosPeriodicosDoc : GastosPeriodicosDocs) {
                Double cantidadTotal = GastosPeriodicosDoc.getDouble("cantidadTotal");
                Double cantidadPagada = GastosPeriodicosDoc.getDouble("cantidadPagada");
                Date fechaInicio = GastosPeriodicosDoc.getDate("fechaInicio");
                Date fechaSiguientePago = GastosPeriodicosDoc.getDate("fechaSiguientePago");
                Integer plazosTiempo = GastosPeriodicosDoc.getInteger("plazosTiempo");
                Double plazosDinero = GastosPeriodicosDoc.getDouble("plazosDinero");
                listaGastos.add(new GastosPeriodicos(cantidadTotal, cantidadPagada, fechaInicio, fechaSiguientePago, plazosTiempo, plazosDinero)); 
            }

            listaCuentas.add(new Cuenta(id, balance, listaTarjetas, historialMovimientos, listaGastos)); 
        }

        return new Usuario(username, contraseña, listaCuentas, nombre, apellidos);
    }

    // Otros métodos para manipular la colección de MongoDB
}