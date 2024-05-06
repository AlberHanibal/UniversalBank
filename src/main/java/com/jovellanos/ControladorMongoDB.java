package com.jovellanos;

import java.util.ArrayList;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.jovellanos.modelo.Cuenta;
import com.jovellanos.modelo.Usuario;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ControladorMongoDB {
    // Variables de conexión a MongoDB
    private MongoCollection<Usuario> collection;

    public ControladorMongoDB() {
        // Configurar conexión a MongoDB
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        MongoClient mongoClient = MongoClients.create(clientSettings);
        MongoDatabase db = mongoClient.getDatabase("UniversalDatas");
        collection = db.getCollection("Usuarios", Usuario.class);
    }

    public void guardarUsuario(Usuario usuario) {
        collection.insertOne(usuario);
    }

    public void guardarCuenta(String username, Cuenta cuenta) {
        Usuario usuario = buscarUsuarioPorNombre(username);
        ArrayList<Cuenta> listaCuentas = usuario.getListaCuentas();
        listaCuentas.add(cuenta);
        collection.replaceOne(eq("username", username), usuario);
    }

    public boolean ComprobarUsuario(String username) {
        Usuario usuario = buscarUsuarioPorNombre(username);
        return usuario != null;
    }

    public boolean ComprobarUsuarioYContraseña(String username, String contraseña) {
        Usuario usuario = collection.find(and(eq("username", username), eq("contraseña", contraseña))).first();

        return usuario != null;
    }

    public Usuario buscarUsuarioPorNombre(String username) {
        Usuario usuario = collection.find(eq("username", username)).first();
        return usuario;
    }

    // Otros métodos para manipular la colección de MongoDB
}