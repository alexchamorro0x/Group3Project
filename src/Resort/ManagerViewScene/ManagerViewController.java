package Resort.ManagerViewScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerViewController {

    @FXML
    private AnchorPane Name;

    @FXML
    private Button GoToScene1From5;

    @FXML
    private CheckBox enableEdit;

    @FXML
    private Button updateInfo;

    @FXML
    private CheckBox Occupied;

    @FXML
    public void btnClickedHome5(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) GoToScene1From5.getScene().getWindow();
        Parent loginScene = FXMLLoader.load(getClass().getResource("../TitleScene/Title.fxml"));
        thisStage.setScene(new Scene(loginScene, 750, 500));
    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @FXML
    void cbEdit(ActionEvent event) {

    }

    @FXML
    void cbOccupied(ActionEvent event) {

    }

    @FXML
    void tfName(MouseEvent event) {

    }

}
