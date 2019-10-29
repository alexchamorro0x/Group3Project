package Resort.ManagerViewScene;

import Resort.TitleScene.TitleController;
import Resort.Utility.SessionInformation;
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

    // String to hold our username if logged in and if user is a manager
    private SessionInformation sessionInformation = new SessionInformation();

    // setter for session information
    public void setSessionInformation(SessionInformation sessionInformation) {
        this.sessionInformation = sessionInformation;
    }

    @FXML
    public void btnClickedHome5(ActionEvent actionEvent) throws IOException {
        //get a reference to the window we are in
        Stage window = (Stage) Name.getScene().getWindow();

        // declare and initialize a loader for the FXML scene we are going to
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../TitleScene/Title.fxml"));

        // create a parent class with our loader pointing at the new scene
        Parent title = loader.load();

        // get controller for Title page
        TitleController titleController = loader.getController();
        titleController.setSessionInformation(sessionInformation);
        // make the new scene we are going to
        Scene titleScene = new Scene(title);

        // initiate the scene change (no need to make changes to controller)
        window.setScene(titleScene);
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
