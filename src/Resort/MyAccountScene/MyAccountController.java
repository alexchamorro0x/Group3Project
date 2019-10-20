package Resort.MyAccountScene;

import Resort.Utility.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
  private Button btnBack;

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
  void btnClickEditAccount(MouseEvent event) {
    /*
      Todo: add code to initiate a scene change the edit account scene
     */
    System.out.println("Edit Account Entered");
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

