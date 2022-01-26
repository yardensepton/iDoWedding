package com.example.demo3;
import Executors.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    public static Stage mainStage;
    public static Manager manager = new Manager();


    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader startPage = new FXMLLoader(HelloApplication.class.getResource("Start.fxml"));
        Scene scene1 = new Scene(startPage.load(), 710, 470);
        stage.setScene(scene1);
        stage.setTitle(" © Nofar Shlosberg and Yarden Septon ");
        stage.show();
    }


    public static void main(String[] args) throws Exception {
        try {
            launch();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }

}
