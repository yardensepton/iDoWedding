package ScenesCommunication;
import DbConnenction.GetFromDB;
import Models.Buffet.*;
import Models.Couple;
import com.example.demo3.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ControllerAddMainDish {
    ControllerMainScreen mainScreen = new ControllerMainScreen();
    @FXML
    private Button back;
    @FXML
    private Label ChooseDecorations;

    @FXML
    private ComboBox<Couple> coupleChoiceCombo;

    @FXML
    private Label coupleChoiceLabel;

    @FXML
    private Button done;

    @FXML
    private CheckBox mashedPotatoes;

    @FXML
    private CheckBox pulletSteak;

    @FXML
    private CheckBox rice;

    @FXML
    private CheckBox risotto;

    @FXML
    private CheckBox salomon;

    @FXML
    private CheckBox steak;
    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }
    @FXML
    void doneAction(MouseEvent event) {
        if (!checks()){
            return;
        }
        ArrayList<BuffetDecorator> decorations = new ArrayList<>();
        MainDish mainDish = new MainDish(0);
        if (pulletSteak.isSelected()) {
            PulletSteakBuffetDecorator pulletSteakBuffetDecorator = new PulletSteakBuffetDecorator(mainDish);
            decorations.add(pulletSteakBuffetDecorator);
        }
        if (mashedPotatoes.isSelected()) {
             PureeBuffetDecorator pureeBuffetDecorator = new PureeBuffetDecorator(mainDish);
            decorations.add(pureeBuffetDecorator);
        }
        if (rice.isSelected()) {
            RiceBuffetDecorator riceBuffetDecorator = new RiceBuffetDecorator(mainDish);
            decorations.add(riceBuffetDecorator);
        }
        if (risotto.isSelected()) {
            RisottoBuffetDecorator risottoBuffetDecorator = new RisottoBuffetDecorator(mainDish);
            decorations.add(risottoBuffetDecorator);
        }
        if (salomon.isSelected()) {
            RoastSalmonBuffetDecorator roastSalmonBuffetDecorator = new RoastSalmonBuffetDecorator(mainDish);
            decorations.add(roastSalmonBuffetDecorator);
        }
        if (steak.isSelected()) {
            SteakBuffetDecorator steakBuffetDecorator = new SteakBuffetDecorator(mainDish);
            decorations.add(steakBuffetDecorator);
        }
        try {
            HelloApplication.manager.chooseDishDecorations(coupleChoiceCombo.getValue(), mainDish,decorations);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, coupleChoiceCombo.getValue().toString());
            alert.showAndWait();
        } catch (Exception e) {
            String err = e.getMessage();
            Alert error = new Alert(Alert.AlertType.INFORMATION, err);
            error.setTitle("Invalid information");
            error.showAndWait();
        }
    }

    boolean checks() {
        if (coupleChoiceCombo.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All the couples chose car decorations.");
            alert.showAndWait();
            return false;
        } else if (!(risotto.isSelected()  || rice.isSelected()  || steak.isSelected()  || salomon.isSelected() || pulletSteak.isSelected()  || mashedPotatoes.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty fields");
            alert.showAndWait();
            return false;
        }
        return true;
    }


    void insertCouplesToComboBox() throws Exception {
        ArrayList<Couple> couples = GetFromDB.getAllCouples();
        for (Couple couple : couples) {
            if (couple.getWedding().getMainDish() == null) {
                coupleChoiceCombo.getItems().add(couple);
            }
        }
        if (coupleChoiceCombo.getItems().isEmpty()) {
            coupleChoiceCombo.setPromptText("All the couples chose main course.");
        }
    }

}



