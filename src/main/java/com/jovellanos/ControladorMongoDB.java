package com.jovellanos;

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

    // Otros métodos para manipular la colección de MongoDB
}