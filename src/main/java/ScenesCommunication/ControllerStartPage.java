package ScenesCommunication;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerStartPage {
    @FXML
    private Button startButton;

    @FXML
    private void startButtonClick() throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = Main.mainStage;
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window...");
        }
    }


}