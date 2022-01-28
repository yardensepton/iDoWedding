package ScenesCommunication;

import DbConnenction.GetFromDB;
import Models.Buffet.MainDish;
import Models.Couple;
import Models.Invitation.InvitationFactory;
import Models.Music.DJ;
import Models.Wedding;
import Models.WeddingCar.CarDecorator;
import Models.WeddingHall.WeddingHall;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPrintData implements Initializable {
    ControllerMainScreen mainScreen = new ControllerMainScreen();
    ArrayList<Couple> couples = GetFromDB.getAllCouples();

    public ControllerPrintData() throws Exception {
    }

    @FXML
    private TableColumn<Wedding, CarDecorator> car;

    @FXML
    private Button choose;
    @FXML
    private ComboBox<Couple> coupleChoiceCombo;

    @FXML
    private Label coupleChoiceLabel;

    @FXML
    private TableColumn<Wedding, LocalDate> date;

    @FXML
    private TableColumn<Wedding, DJ> dj;


    @FXML
    private TableColumn<Wedding, Integer> guests;

    @FXML
    private TableColumn<Wedding, WeddingHall> hall;

    @FXML
    private TableColumn<Wedding, MainDish> mainCourse;
    @FXML
    private TableColumn<Wedding, InvitationFactory> invitation;
    @FXML
    private TableView<Wedding> tableViewCouple;
    @FXML
    private Button back;

    public ComboBox<Couple> getCoupleChoiceCombo() {
        return coupleChoiceCombo;
    }

    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guests.setCellValueFactory(new PropertyValueFactory<Wedding, Integer>("numOfGuests"));
        hall.setCellValueFactory(new PropertyValueFactory<Wedding, WeddingHall>("weddingHall"));
        dj.setCellValueFactory(new PropertyValueFactory<Wedding, DJ>("dj"));
        mainCourse.setCellValueFactory(new PropertyValueFactory<Wedding, MainDish>("mainDish"));
        car.setCellValueFactory(new PropertyValueFactory<Wedding, CarDecorator>("car"));
        date.setCellValueFactory(new PropertyValueFactory<Wedding, LocalDate>("weddingDate"));
        invitation.setCellValueFactory(new PropertyValueFactory<Wedding, InvitationFactory>("invitation"));
    }


    @FXML
    void choose(MouseEvent event) throws Exception {
        if (coupleChoiceCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty fields..");
            alert.showAndWait();
        } else {
            if (!tableViewCouple.getItems().isEmpty()) {
                deleteFromTable();
            }
            Wedding wedding = coupleChoiceCombo.getValue().getWedding();
            tableViewCouple.getItems().add(wedding);
        }
    }

    void deleteFromTable() {
        tableViewCouple.getItems().removeAll(tableViewCouple.getItems());
    }

    void insertCouplesToComboBox() throws Exception {
        for (Couple couple : couples) {
            coupleChoiceCombo.getItems().add(couple);
        }
    }
}
