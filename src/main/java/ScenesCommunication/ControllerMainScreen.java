package ScenesCommunication;

import DbConnenction.GetFromDB;
import Models.Couple;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ControllerMainScreen {
    ControllerAddCouplePage controllerAddCouplePage;
    ControllerAddDJ controllerAddDJ;
    ControllerAddMainDish controllerAddMainDish;
    ControllerAddCar controllerAddCar;
    ControllerPrintData controllerPrintData;
    ControllerPrintDJs controllerPrintDJs;
    ControllerAddInvitation controllerAddInvitation;
    ControllerPrintHalls controllerPrintHalls;
    @FXML
    private Button buttonAddCouple;
    @FXML
    private Button DJsDetails;
    @FXML
    private Button hallDetails;

    @FXML
    private Button details;
    @FXML
    private Button buttonBuffet;
    @FXML
    private Button buttonAddCar;
    @FXML
    private Button buttonInvitation;

    void back() throws Exception {
        Stage stage = Main.mainStage;
        FXMLLoader startPage = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene1 = new Scene(startPage.load());
        stage.setScene(scene1);
        stage.show();
    }

    void insertCouplesToComboBox(ComboBox coupleChoiceCombo) throws Exception {
        ArrayList<Couple> couples = GetFromDB.getAllCouples();
        for (Couple couple : couples) {
            coupleChoiceCombo.getItems().add(couple);
        }
    }


    @FXML
    void OpenPrintDJsPage(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PrintDJs.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = Main.mainStage;
            controllerPrintDJs = fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }

    @FXML
    void OpenPrintHallsPage(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PrintHalls.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = Main.mainStage;
            controllerPrintHalls = fxmlLoader.getController();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }

    @FXML
    void openAddCouplePage(MouseEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddCoupleScreen.fxml"));
            AnchorPane root1 = (AnchorPane) fxmlLoader.load();
            Stage stage = Main.mainStage;
            controllerAddCouplePage = fxmlLoader.getController();
            controllerAddCouplePage.insertTypeHallsToComboBox();
            controllerAddCouplePage.getNumGuests().setEditable(true);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }

    }

    @FXML
    void openAddCarScreen(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("AddCarScreen.fxml"));
            AnchorPane root3 = (AnchorPane) fxmlLoader2.load();
            Stage stage = Main.mainStage;
            controllerAddCar = fxmlLoader2.getController();
            insertCouplesToComboBox(controllerAddCar.getCoupleChoiceCombo());
            controllerAddCar.insertVehiclesToComboBox();
            stage.setScene(new Scene(root3));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }

    @FXML
    void OpenAddDjPage(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("AddDJ.fxml"));
            AnchorPane root2 = (AnchorPane) fxmlLoader1.load();
            Stage stage = Main.mainStage;
            controllerAddDJ = fxmlLoader1.getController();
            insertCouplesToComboBox(controllerAddDJ.getCoupleChoiceCombo());
            stage.setScene(new Scene(root2));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }

    @FXML
    void openInvitationPage(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(Main.class.getResource("AddInvitation.fxml"));
            AnchorPane root4 = (AnchorPane) fxmlLoader3.load();
            Stage stage = Main.mainStage;
            controllerAddInvitation = fxmlLoader3.getController();
            controllerAddInvitation.insertToDelivery();
            insertCouplesToComboBox(controllerAddInvitation.getCoupleChoiceCombo());
            stage.setScene(new Scene(root4));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }

    @FXML
    void openAddMainDish(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader3 = new FXMLLoader(Main.class.getResource("AddWeddingMainDish.fxml"));
            AnchorPane root4 = (AnchorPane) fxmlLoader3.load();
            Stage stage = Main.mainStage;
            controllerAddMainDish = fxmlLoader3.getController();
            insertCouplesToComboBox(controllerAddMainDish.getCoupleChoiceCombo());
            stage.setScene(new Scene(root4));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }

    @FXML
    void OpenPrintPage(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader4 = new FXMLLoader(Main.class.getResource("PrintingData.fxml"));
            AnchorPane root5 = (AnchorPane) fxmlLoader4.load();
            Stage stage = Main.mainStage;
            controllerPrintData = fxmlLoader4.getController();
            controllerPrintData.insertCouplesToComboBox();
            stage.setScene(new Scene(root5));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }


}