package ScenesCommunication;
import Models.WeddingHall.eTypesOfHall;
import com.example.demo3.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.time.LocalDate;

public class ControllerAddCouplePage {
    ControllerMainScreen mainScreen = new ControllerMainScreen();
    public ControllerAddCouplePage() {

    }

    @FXML
    private Button checkValidation;

    @FXML
    private TextField firstSpouseID;

    @FXML
    private TextField firstSpouseLastName;

    @FXML
    private TextField firstSpouseName;

    @FXML
    private ComboBox<eTypesOfHall> hallOptions;
    @FXML
    private Spinner<Integer> numGuests;

    @FXML
    private TextField secondSpouseID;

    @FXML
    private TextField secondSpouseLastName;

    @FXML
    private TextField secondSpouseName;
    @FXML
    private Button back;
    @FXML
    private DatePicker weddingDate;

    @FXML
    void backAction(MouseEvent event) throws Exception {
        mainScreen.back();
    }
    public void insertTypeHallsToComboBox() {
        for (eTypesOfHall type : eTypesOfHall.values()) {
            hallOptions.getItems().add(type);
        }
    }

    public Spinner<Integer> getNumGuests() {
        return numGuests;
    }

    public void setNumGuests() {
        this.numGuests = new Spinner<>(1, 1, 1000);
    }

    public void getHallOption(MouseEvent mouseEvent) {
        eTypesOfHall hall = hallOptions.getValue();
    }
    @FXML
    public void checkValidation(MouseEvent mouseEvent) throws Exception {
        String firstNameSpouse1 = firstSpouseName.getText();
        String firstNameSpouse2 = secondSpouseName.getText();
        String lastNameSpouse1 = firstSpouseLastName.getText();
        String lastNameSpouse2 = secondSpouseLastName.getText();
        String idSpouse1 = firstSpouseID.getText();
        String idSpouse2 = secondSpouseID.getText();
        eTypesOfHall hall = hallOptions.getValue();
        int guests = numGuests.getValue();
        LocalDate date = weddingDate.getValue();
        if (firstNameSpouse1.isEmpty() || lastNameSpouse1.isEmpty() || firstNameSpouse2.isEmpty() || lastNameSpouse2.isEmpty() || idSpouse1.isEmpty() || idSpouse2.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Empty fields..");
            alert.showAndWait();
        } else {
            try {
                HelloApplication.manager .addCouple(firstNameSpouse1,lastNameSpouse1,idSpouse1,firstNameSpouse2,lastNameSpouse2,idSpouse2,guests,date,hall);
                Stage stage = HelloApplication.mainStage;
                FXMLLoader startPage = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
                Scene scene1 = new Scene(startPage.load());
                stage.setScene(scene1);
                stage.show();
            }catch (Exception e){
               String err =  e.getMessage();
                Alert error = new Alert(Alert.AlertType.ERROR, err);
                error.setTitle("Invalid information");
                error.showAndWait();
            }
        }
    }
}
