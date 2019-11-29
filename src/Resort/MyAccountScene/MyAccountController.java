package Resort.MyAccountScene;

import Resort.EditAccountScene.EditAccountController;
import Resort.TitleScene.TitleController;
import Resort.Utility.AccountInformation;
import Resort.Utility.Booking;
import Resort.Utility.DatabaseAgent;
import Resort.Utility.SessionInformation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MyAccountController {
  //region GUI
  @FXML private Button btnHome;

  @FXML private ImageView homeLogo;

  @FXML private Label lblFirstName;
  @FXML private Label lblLastName;
  @FXML private Label lblUserName;
  @FXML private Label lblEmail;
  @FXML private Label lblAddress;
  @FXML private Label lblState;
  @FXML private Label lblZipCode;

  @FXML private TableView<Booking> tvBookings;
  @FXML private TableColumn<?, ?> tvCheckIn;
  @FXML private TableColumn<?, ?> tvCheckOut;
  @FXML private TableColumn<?, ?> tvRoomType;
  @FXML private TableColumn<?, ?> tvRoomNumber;
  @FXML private TableColumn<?, ?> tvBookingId;

  @FXML private Button btnEditAccount;
  @FXML private Button btnCancelBooking;
  @FXML private Button btnDeleteAccount;
//endregion

  // String to hold our username if logged in and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();

  /**
   * Code that rus at page startup, sets up the table.
   */
 public void initialize() {
    tvCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
    tvCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
    tvRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    tvRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
    tvBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

    File RoomA = new File("src/Resort/RoomFinderScene/pineapple.png");
    Image pineapple = new Image(RoomA.toURI().toString());
    homeLogo.setImage(pineapple);
    homeLogo.setFitHeight(65);
    homeLogo.setFitHeight(100);

    tvBookings.setPlaceholder(new Label("You have no reservations."));
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
    AccountInformation accountToDelete =
        DatabaseAgent.getAccountInformation(sessionInformation.getUserName());
    DatabaseAgent.deleteAccount(accountToDelete.getUserId());
    sessionInformation.setUserName(null);
    // get a reference to the window we are in
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

  /**
   * Move to the edit account page.
   * @param event
   * @throws IOException
   */
  @FXML
  void btnClickEditAccount(MouseEvent event) throws IOException {
    // get a reference to the window we are in
    Stage window = (Stage) btnHome.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../EditAccountScene/EditAccount.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent title = loader.load();
    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    EditAccountController editAccountController = loader.getController();
    editAccountController.setSessioninformation(sessionInformation);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);
  }

  /**
   * Move to the title page.
   * @param event
   * @throws IOException
   */
  @FXML void btnClickHome(MouseEvent event) throws IOException {
    // get a reference to the window we are in
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

  public void btnHomeEntered(MouseEvent mouseEvent) {
    homeLogo.setFitHeight(70);
    homeLogo.setFitHeight(105);

    Glow glow = new Glow();
    glow.setLevel(.15);
    homeLogo.setEffect(glow);

    Resort.RoomFinderScene.RoomFinderController.pictureBorder(homeLogo);
  }

  public void btnHomeExited(MouseEvent mouseEvent) {
    homeLogo.setFitHeight(65);
    homeLogo.setFitHeight(100);
    File RoomA = new File("src/Resort/RoomFinderScene/pineapple.png");
    Image pineapple = new Image(RoomA.toURI().toString());
    homeLogo.setImage(pineapple);
  }

  /**
   * setter for session information, updates information on the page
   * @param sessionInformation
   */
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
    AccountInformation userInformation =
            DatabaseAgent.getAccountInformation(sessionInformation.getUserName());
    lblUserName.setText(userInformation.getUserName());
    lblFirstName.setText(userInformation.getFirstName());
    lblLastName.setText(userInformation.getLastName());
    lblEmail.setText(userInformation.getEmail());
    lblAddress.setText(userInformation.getAddress());
    lblState.setText(userInformation.getState());
    lblZipCode.setText(userInformation.getZipCode());

    // fill table with users bookings
    // get bookings from database
    ArrayList<Booking> usersBookings =
            DatabaseAgent.getUserBookings(sessionInformation.getUserName());
    for (Booking booking : usersBookings) {
      tvBookings.getItems().add(booking);
    }
  }
}
