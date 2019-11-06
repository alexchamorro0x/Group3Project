package Resort.ManagerViewScene;

import Resort.TitleScene.TitleController;
import Resort.Utility.SessionInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerViewController {

    @FXML
    private AnchorPane Name;

    @FXML
    private TableView<?> tvBooked;

    @FXML
    private TableColumn<?, ?> tcFirstName;

    @FXML
    private TableColumn<?, ?> tcLastName;

    @FXML
    private TableColumn<?, ?> tcCheckIn;

    @FXML
    private TableColumn<?, ?> tcCheckOut;

    @FXML
    private TableColumn<?, ?> tcRoomNumberBooked;

    @FXML
    private TableColumn<?, ?> tcRoomTypeBooked;

    @FXML
    private Button GoToScene1From5;

    @FXML
    private TableView<?> tvVacant;

    @FXML
    private TableColumn<?, ?> tcRoomNumberVacant;

    @FXML
    private TableColumn<?, ?> tcRoomTypeVacant;

    @FXML
    private TextField tfFirstName;

    @FXML
    private Button btnBookRoom;

    @FXML
    private TextField tfLastName;

    @FXML
    private DatePicker dpCheckInBooked;

    @FXML
    private DatePicker dpCheckOutBooked;

    @FXML
    private DatePicker dpCheckInVacant;

    @FXML
    private DatePicker dpCheckOutVacant;

    @FXML
    private Button btnCancelBooking;

    @FXML
    private ChoiceBox<?> cbRoomNumber;

    @FXML
    private ChoiceBox<?> cbRoomType;

    @FXML
    private TextField tfUserName;

    @FXML
    private TextField tfState;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfZipCode;

    @FXML
    private TextField tfCreditCard;

    @FXML
    private TextField tfCVV;


    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @FXML
    void tfName(MouseEvent event) {

    }


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


}
