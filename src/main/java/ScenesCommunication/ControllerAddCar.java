package ScenesCommunication;

import DbConnenction.GetFromDB;
import Models.Couple;
import Models.WeddingCar.*;
import com.example.demo3.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ControllerAddCar {
    ControllerMainScreen mainScreen = new ControllerMainScreen();

    public ControllerAddCar() {

    }

    @FXML
    private CheckBox Balloon;

    @FXML
    private Label ChooseDecorations;

    @FXML
    private Button back;
    @FXML
    private Label ChooseWantedCar;

    @FXML
    private CheckBox Flowers;

    @FXML
    private CheckBox MrAndMrsSign;

    @FXML
    private CheckBox Ribbon;

    @FXML
    private ComboBox<Vehicle> carOptions;

    @FXML
    private ComboBox<Couple> coupleChoiceCombo;
    @FXML
    private Button done;

    @FXML
    private Label coupleChoiceLabel;


    void insertVehiclesToComboBox() throws Exception {
        ArrayList<Vehicle> vehicles = GetFromDB.getVehicleTypes();
        for (Vehicle vehicle : vehicles) {
            carOptions.getItems().add(vehicle);
        }
    }


    void insertCouplesToComboBox() throws Exception {
        ArrayList<Couple> couples = GetFromDB.getAllCouples();
        for (Couple couple : couples) {
            if (couple.getWedding().getCar() == null) {
                coupleChoiceCombo.getItems().add(couple);
            }
        }
        if (coupleChoiceCombo.getItems().isEmpty()) {
            coupleChoiceCombo.setPromptText("All the couples chose car decorations.");
        }
    }

    boolean checks() {
        Alert alert;
        if (coupleChoiceCombo.getItems().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR, "All the couples chose car decorations.");
            alert.showAndWait();
            return false;
        } else if (carOptions.getValue() == null) {
            alert = new Alert(Alert.AlertType.ERROR, "Empty fields");
            alert.showAndWait();
            return false;
        } else if (!(Ribbon.isSelected() || Flowers.isSelected() || MrAndMrsSign.isSelected() || Balloon.isSelected())) {
            alert = new Alert(Alert.AlertType.ERROR, "Empty fields");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }

    @FXML
    void doneAction(MouseEvent event) throws Exception {
        if (!checks()) {
            return;
        }
        ArrayList<CarDecorator> decorations = new ArrayList<>();
        if (Ribbon.isSelected()) {
            RibbonCarDecorator ribbonCarDecorator = new RibbonCarDecorator(carOptions.getValue());
            decorations.add(ribbonCarDecorator);
        }
        if (Flowers.isSelected()) {
            FlowersCarDecorator flowersCarDecorator = new FlowersCarDecorator(carOptions.getValue());
            decorations.add(flowersCarDecorator);
        }
        if (Balloon.isSelected()) {
            BalloonCarDecorator balloonCarDecorator = new BalloonCarDecorator(carOptions.getValue());
            decorations.add(balloonCarDecorator);
        }
        if (MrAndMrsSign.isSelected()) {
            MrAndMrsCarDecorator mrAndMrsCarDecorator = new MrAndMrsCarDecorator(carOptions.getValue());
            decorations.add(mrAndMrsCarDecorator);
        }
        try {
            HelloApplication.manager.chooseCarDecorations(coupleChoiceCombo.getValue(), carOptions.getValue(), decorations);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, coupleChoiceCombo.getValue().toString());
            alert.showAndWait();
            insertCouplesToComboBox();
        } catch (Exception e) {
            String err = e.getMessage();
            Alert error = new Alert(Alert.AlertType.INFORMATION, err);
            error.setTitle("Invalid information");
            error.showAndWait();
        }
    }
}



