package Resort.MyAccountScene;

import Resort.EditAccountScene.EditAccountController;
import Resort.Utility.AccountInformation;
import Resort.Utility.Booking;
import Resort.Utility.DatabaseCrud;
import java.io.IOException;
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

  private AccountInformation userAccount = new AccountInformation();

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

  @FXML
  void btnClickCancelBooking(MouseEvent event) {
    /*
      Todo: add code here to remove the selected booking from the database
     */
    int indexToRemove = tvBookings.getSelectionModel().getSelectedIndex();
    tvBookings.getItems().remove(indexToRemove);
  }

  @FXML
  void btnClickDeleteAccount(MouseEvent event) {
    /*
      Todo: add code to remove this account from the database
     */
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
    editAccountController.setAccountInformation(userAccount);

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
    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);
  }

  public void setUserInformation(String userName) {
    DatabaseCrud databaseAgent = new DatabaseCrud();
    AccountInformation userInformation = databaseAgent.getAccountInformation(userName);
    this.userAccount = userInformation;
    lblUserName.setText(userInformation.getUserName());
    lblFirstName.setText(userInformation.getFirstName());
    lblLastName.setText(userInformation.getLastName());
    lblEmail.setText(userInformation.getEmail());
    lblAddress.setText(userInformation.getAddress());
    lblState.setText(userInformation.getState());
    lblZipCode.setText(userInformation.getZipCode());
  }

  public void initialize() {
    tvCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
    tvCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
    tvRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    tvRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
    tvBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

    // Test booking - will need to be replaced with code to get current accounts bookings
    tvBookings.getItems().add(new Booking("1/12/19", "1/13/19", "Suite", "123", "A1"));
  }

}

