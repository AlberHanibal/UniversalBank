package com.jovellanos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.bson.Document;

public class App extends Application {
    // Variables de conexión a MongoDB
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public void ControladorMongoDB() {
        // Configurar conexión a MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mi_base_de_datos");
        collection = database.getCollection("mi_coleccion");
        List<Document> documents = new ArrayList<>();
        Document doc1 = new Document("name", "Halley's Comet").append("officialName", "1P/Halley").append("orbitalPeriod", 75).append("radius", 3.4175).append("mass", 2.2e14);
        Document doc2 = new Document("name", "Wild2").append("officialName", "81P/Wild").append("orbitalPeriod", 6.41).append("radius", 1.5534).append("mass", 2.3e13);
        Document doc3 = new Document("name", "Comet Hyakutake").append("officialName", "C/1996 B2").append("orbitalPeriod", 17000).append("radius", 0.77671).append("mass", 8.8e12);
        documents.add(doc1);
        documents.add(doc2);
        documents.add(doc3);
        InsertManyResult result = collection.insertMany(documents);
    }
    
    @Override
    public void start(Stage primaryStage) {
        ControladorMongoDB();
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
