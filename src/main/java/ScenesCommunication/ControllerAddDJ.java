package ScenesCommunication;

import DbConnenction.GetFromDB;
import Models.Couple;
import Models.Music.DJ;
import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class ControllerAddDJ {

    ControllerMainScreen mainScreen = new ControllerMainScreen();

    @FXML
    private Button checkValidation;

    @FXML
    private ComboBox<DJ> djOptions;

    @FXML
    private Button done;

    @FXML
    private Label labelMusic;

    @FXML
    private CheckBox middleEast;

    @FXML
    private CheckBox pop;

    @FXML
    private CheckBox rock;

    @FXML
    private Label wantedDj;
    @FXML
    private Button back;
    @FXML
    private ComboBox<Couple> coupleChoiceCombo;

    public ComboBox<Couple> getCoupleChoiceCombo() {
        return coupleChoiceCombo;
    }

    @FXML
    private Label coupleChoiceLabel;

    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }


    @FXML
    void checkValidation(MouseEvent event) throws Exception {
        List<DJ> djsPop = GetFromDB.getAllDJsByGenre(Main.manager.getPop().toString());
        List<DJ> djsRock = GetFromDB.getAllDJsByGenre(Main.manager.getRock().toString());
        List<DJ> djsMiddleEast = GetFromDB.getAllDJsByGenre(Main.manager.getMiddleEast().toString());

        if (pop.isSelected()) {
            insertToComboBox(djsPop);
        }
        if (rock.isSelected()) {
            insertToComboBox(djsRock);
        }
        if (middleEast.isSelected()) {
            insertToComboBox(djsMiddleEast);
        }
        if (!pop.isSelected()) {
            deleteFromComboBox(djsPop);
        }
        if (!rock.isSelected()) {
            deleteFromComboBox(djsRock);
        }
        if (!middleEast.isSelected()) {
            deleteFromComboBox(djsMiddleEast);
        }
        pop.setSelected(false);
        rock.setSelected(false);
        middleEast.setSelected(false);
    }

    void insertToComboBox(List<DJ> djs) {
        for (DJ dj : djs) {
            djOptions.getItems().add(dj);
        }
    }

    void deleteFromComboBox(List<DJ> djs) {
        for (DJ dj : djs) {
            djOptions.getItems().remove(dj);
        }
    }


    @FXML
    void doneAction(MouseEvent event) throws Exception {
        if (coupleChoiceCombo.getValue() == null || djOptions.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty fields..");
            alert.showAndWait();
        } else {
            try {
                DJ dj = djOptions.getValue();
                Couple couple = coupleChoiceCombo.getValue();
                Main.manager.chooseDJ(couple, dj);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, djOptions.getValue().toString());
                alert.showAndWait();
            } catch (Exception e) {
                String err = e.getMessage();
                Alert error = new Alert(Alert.AlertType.INFORMATION, err);
                error.setTitle("Invalid information");
                error.showAndWait();
            }

        }
    }

}
