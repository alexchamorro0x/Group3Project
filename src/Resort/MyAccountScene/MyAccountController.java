package Resort.MyAccountScene;

import Resort.EditAccountScene.EditAccountController;
import Resort.TitleScene.TitleController;
import Resort.Utility.AccountInformation;
import Resort.Utility.Booking;
import Resort.Utility.DatabaseAgent;
import Resort.Utility.SessionInformation;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MyAccountController {

  @FXML
  private Label lblFirstName;

  @FXML
  private Label lblLastName;

  @FXML
  private Label lblUserName;

  @FXML
  private Label lblEmail;

  @FXML
  private Label lblAddress;

  @FXML
  private Label lblState;

  @FXML
  private Label lblZipCode;

  @FXML
  private Button btnEditAccount;

  @FXML
  private TableView<Booking> tvBookings;

  @FXML
  private TableColumn<?, ?> tvCheckIn;

  @FXML
  private TableColumn<?, ?> tvCheckOut;

  @FXML
  private TableColumn<?, ?> tvRoomType;

  @FXML
  private TableColumn<?, ?> tvRoomNumber;

  @FXML
  private TableColumn<?, ?> tvBookingId;

  @FXML
  private Button btnCancelBooking;

  @FXML
  private Button btnDeleteAccount;

  @FXML
  private Button btnHome;

  // String to hold our username if logged in and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();

  // setter for session information, updates information on the page
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
    AccountInformation userInformation = DatabaseAgent.getAccountInformation(sessionInformation.getUserName());
    lblUserName.setText(userInformation.getUserName());
    lblFirstName.setText(userInformation.getFirstName());
    lblLastName.setText(userInformation.getLastName());
    lblEmail.setText(userInformation.getEmail());
    lblAddress.setText(userInformation.getAddress());
    lblState.setText(userInformation.getState());
    lblZipCode.setText(userInformation.getZipCode());

    // fill table with users bookings
    // get bookings from database
    ArrayList<Booking> usersBookings = DatabaseAgent.getUserBookings(sessionInformation.getUserName());
    for (Booking booking : usersBookings) {
      tvBookings.getItems().add(booking);
    }

  }

  @FXML
  void btnClickCancelBooking(MouseEvent event) {
    Booking bookingToDelete = tvBookings.getSelectionModel().getSelectedItem();
    DatabaseAgent.deleteReservation(Integer.parseInt(bookingToDelete.getBookingId()));
    int indexToRemove = tvBookings.getSelectionModel().getSelectedIndex();
    tvBookings.getItems().remove(indexToRemove);
  }

  @FXML
  void btnClickDeleteAccount(MouseEvent event) throws IOException {
    AccountInformation accountToDelete = DatabaseAgent.getAccountInformation(sessionInformation.getUserName());
    DatabaseAgent.deleteAccount(accountToDelete.getUserId());
    sessionInformation.setUserName(null);
    //get a reference to the window we are in
    Stage window = (Stage) btnHome.getScene().getWindow();

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
    System.out.println("Delete Account Entered");
  }

  @FXML
  void btnClickEditAccount(MouseEvent event) throws IOException {
    //get a reference to the window we are in
    Stage window = (Stage) btnHome.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../EditAccountScene/EditAccount.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent title = loader.load();
    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    EditAccountController editAccountController = loader.getController();
    editAccountController.setSessionformation(sessionInformation);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);
  }

  @FXML
  void btnClickHome(MouseEvent event) throws IOException {
    //get a reference to the window we are in
    Stage window = (Stage) btnHome.getScene().getWindow();

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

  public void initialize() {
    tvCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
    tvCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
    tvRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    tvRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
    tvBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

  }

}

