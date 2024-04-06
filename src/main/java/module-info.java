module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

    opens com.example to javafx.fxml;
    exports com.example;
}
