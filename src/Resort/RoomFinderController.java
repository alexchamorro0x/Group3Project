package Resort;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomFinderController {



// This declares the 'Home' button on Scene 4 'Find Room'. This changes the scene back to 1, 'Title'.
    @FXML
    private Button GoToScene1From4;
    public void btnClickedHome4(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) GoToScene1From4.getScene().getWindow();
        Parent loginScene = FXMLLoader.load(getClass().getResource("Title.fxml"));
        thisStage.setScene(new Scene(loginScene, 750, 500));
    }
}


