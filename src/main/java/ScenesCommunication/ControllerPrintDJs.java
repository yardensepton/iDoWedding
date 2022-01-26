package ScenesCommunication;
import DbConnenction.GetFromDB;
import Models.Music.DJ;
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

public class ControllerPrintDJs  implements Initializable {

    ControllerMainScreen mainScreen = new ControllerMainScreen();
    ArrayList <DJ> djs = GetFromDB.getAllDJs();

    public ControllerPrintDJs() throws Exception {
    }

    @FXML
    private Button back;

    @FXML
    private TableColumn<DJ, String> firstName;

    @FXML
    private TableColumn<DJ, String> id;

    @FXML
    private TableColumn<DJ, String> lastName;

    @FXML
    private TableColumn<DJ, String> music;

    @FXML
    private TableView<DJ> tableViewDJS;



    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.setCellValueFactory(new PropertyValueFactory<DJ, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<DJ, String>("lastName"));
        id.setCellValueFactory(new PropertyValueFactory<DJ, String>("id"));
        music.setCellValueFactory(new PropertyValueFactory<DJ, String>("MusicalGenre"));
        tableViewDJS.getItems().addAll(djs);
    }


}