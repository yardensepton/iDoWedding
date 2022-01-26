module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.demo3 to javafx.fxml;
    exports com.example.demo3;
    opens Models to javafx.base;
    opens Models.Music to javafx.base;
    opens Models.WeddingHall to javafx.base;
    exports ScenesCommunication;
    opens ScenesCommunication to javafx.fxml;
}