package ScenesCommunication;
import DbConnenction.GetFromDB;
import Models.Couple;
import Models.Invitation.Invitation;
import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ControllerAddInvitation {
    ControllerMainScreen mainScreen = new ControllerMainScreen();

    @FXML
    private Button back;

    @FXML
    private Label chooseWantedDelivery;

    @FXML
    private ComboBox<Couple> coupleChoiceCombo;

    @FXML
    private Label coupleChoiceLabel;

    @FXML
    private ComboBox<Invitation> deliveryMethod;

    @FXML
    private Button done;

    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }

    public ComboBox<Couple> getCoupleChoiceCombo() {
        return coupleChoiceCombo;
    }

    @FXML
    void doneAction(MouseEvent event) {
        if (coupleChoiceCombo.getValue() == null || deliveryMethod.getValue()== null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty fields..");
            alert.showAndWait();
        }
        else {
            try {
                Invitation invitation = deliveryMethod.getValue();
                Couple couple = coupleChoiceCombo.getValue();
                Main.manager.chooseInvitation(couple, invitation);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, deliveryMethod.getValue().toString());
                alert.showAndWait();
            }catch (Exception e){
                String err =  e.getMessage();
                Alert error = new Alert(Alert.AlertType.INFORMATION, err);
                error.setTitle("Invalid information");
                error.showAndWait();
            }

        }

    }

    void insertToDelivery() throws Exception {
        ArrayList<String> names = GetFromDB.getInvitationType();
        for (String name:names){
            deliveryMethod.getItems().add(Main.manager.getInvitationFactory().getInvitation(name));
        }
    }


}
