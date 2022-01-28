package ScenesCommunication;

import DbConnenction.GetFromDB;
import Models.WeddingHall.WeddingHall;
import Models.WeddingHall.eTypesOfHall;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPrintHalls implements Initializable {

    ControllerMainScreen mainScreen = new ControllerMainScreen();
    ArrayList<WeddingHall> halls = GetFromDB.getAllWeddingHalls();

    public ControllerPrintHalls() throws Exception {
    }

    @FXML
    private TableColumn<WeddingHall, String> address;

    @FXML
    private Button back;

    @FXML
    private TableColumn<WeddingHall, String> canopy;

    @FXML
    private TableColumn<WeddingHall, Integer> guests;

    @FXML
    private TableColumn<WeddingHall, Integer> id;

    @FXML
    private TableColumn<WeddingHall, String> name;

    @FXML
    private TableView<WeddingHall> tableViewHalls;

    @FXML
    private TableColumn<WeddingHall, eTypesOfHall> type;


    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guests.setCellValueFactory(new PropertyValueFactory<WeddingHall, Integer>("maxNumOfGuests"));
        name.setCellValueFactory(new PropertyValueFactory<WeddingHall, String>("nameOfHall"));
        canopy.setCellValueFactory(new PropertyValueFactory<WeddingHall, String>("typeCanopy"));
        type.setCellValueFactory(new PropertyValueFactory<WeddingHall, eTypesOfHall>("type"));
        address.setCellValueFactory(new PropertyValueFactory<WeddingHall, String>("address"));
        tableViewHalls.getItems().addAll(halls);
    }

}
