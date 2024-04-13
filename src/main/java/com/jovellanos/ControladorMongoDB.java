package com.jovellanos;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ControladorMongoDB {
    // Variables de conexión a MongoDB
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public ControladorMongoDB() {
        // Configurar conexión a MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mi_base_de_datos");
        collection = database.getCollection("mi_coleccion");
    }

    public void guardarUsuario(Usuario usuario) {
        // Lógica para guardar un usuario en la base de datos
        Document doc = new Document("nombre", usuario.getNombre())
                         .append("usuario", usuario.getUsuario())
                         .append("contraseña", usuario.getContraseña());
        collection.insertOne(doc);
    }

    // Otros métodos para manipular la colección de MongoDB
}