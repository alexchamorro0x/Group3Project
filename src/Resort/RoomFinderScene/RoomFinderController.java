package Resort.RoomFinderScene;

import Resort.TitleScene.TitleController;
import Resort.Utility.AvailableRoom;
import Resort.Utility.DatabaseAgent;
import Resort.Utility.SessionInformation;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.util.Duration;

public class RoomFinderController implements Initializable {

  @FXML
  private Button btnHome;
  @FXML
  private Button GoToScene1From4;
  @FXML
  private ImageView RoomLayoutPicture;
  @FXML
  private Text RoomTypeText;
  @FXML
  private Text RoomDescription1;
  @FXML
  private Button btnBookRoom;
  @FXML
  private Label lblAvailableRooms;
  @FXML
  private Label lblInvalidDate;

  @FXML
  private RadioButton radioBtnRoomTypeAll;
  @FXML
  private RadioButton radioBtnRoomTypeAmbassador;
  @FXML
  private RadioButton radioBtnRoomTypeEagleView;
  @FXML
  private RadioButton radioBtnRoomTypePoolSide;
  @FXML
  private RadioButton radioBtnRoomTypeJunior;
  @FXML
  private DatePicker datePickerStart;
  @FXML
  private DatePicker datePickerEnd;

  @FXML
  private TableView<AvailableRoom> tvAvailableRooms;

  @FXML
  private TableColumn<?, ?> tvRoomType;

  @FXML
  private TableColumn<?, ?> tvRoomNumber;

  // variable to track what room type we are filtering by (default = all)
  String roomAvailabilityFilterType = "all";

  // String to hold our username if logged in and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();

  // setter for setting session information
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
  }

  /*
     runs this code at the start of the fxml file being launched. Hides the radio buttons and
     'Available Rooms' label until the date is selected. In the near future this will be implemented
     to show only the radio buttons for open rooms via communication with the Database. If no rooms
     are available, the 'Available Rooms' label will output this message.
    */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Not used now...my thinking is to use the radio buttons as a filter for the search results
//    lblAvailableRooms.setVisible(false);
//    radioBtnRoomTypeA.setVisible(false);
//    radioBtnRoomTypeB.setVisible(false);
//    radioBtnRoomTypeC.setVisible(false);
//    radioBtnRoomTypeD.setVisible(false);

    // start by setting All filter to true
    radioBtnRoomTypeAll.setSelected(true);

    // Initialize the availability table view by associating the data fields from AvailableRooms
    // class with the columns in the table
    tvRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    tvRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

  }

  /*
   * Clicking the 'Book Room' button will print to the console the start and end date. This must be
   * implemented into communicating with the Database in the near future. If either of the
   * DatePicker fields are empty, the button will blink "Invalid Date" 3 times in red text directly
   * to the left of the button
   */
  public void btnClickedBookRoom() {
    // https://stackoverflow.com/questions/20446026/get-value-from-date-picker
    // date picker
    // https://stackoverflow.com/questions/43084698/flashing-label-in-javafx-gui
    // label animation

    LocalDate dateStart = datePickerStart.getValue();
    LocalDate dateEnd = datePickerEnd.getValue();
    if (dateStart == null || dateEnd == null) {

      lblInvalidDate.setText("Invalid Date");
      lblInvalidDate.setTextFill(Color.RED);
      btnBookRoom.setDisable(true);
      Timeline timeline =
          new Timeline(
              new KeyFrame(Duration.seconds(0.8), evt -> lblInvalidDate.setVisible(false)),
              new KeyFrame(Duration.seconds(0.4), evt -> lblInvalidDate.setVisible(true)));
      timeline.setCycleCount(3);
      timeline.play();

    } else {
      System.out.println("\nStart date: " + dateStart + "\nEnd date: " + dateEnd);
      AvailableRoom roomToBook = tvAvailableRooms.getSelectionModel().getSelectedItem();
      DatabaseAgent.insertIntoReservations(sessionInformation.getUserName(),
          roomToBook.getRoomNumber(), Date.valueOf(dateStart), Date.valueOf(dateEnd));
    }
    btnBookRoom.setDisable(false);
  }

  // Sets the Radio Button for 'Room Type All' to true, selected. Other Radio Buttons (A,B,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomAll(MouseEvent mouseEvent) throws SQLException {
    // all selected so image and text is set to null
    RoomLayoutPicture.setImage(null);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("");
    RoomDescription1.setText("");
    radioBtnRoomTypeAll.setSelected(true);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(false);
    // set filter type
    roomAvailabilityFilterType = "all";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type A' to true, selected. Other Radio Buttons (All,B,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomAmbassador(MouseEvent mouseEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleD.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Ambassador Suite");
    RoomDescription1.setText("- 5 Bed Room \n\n- Kitchen \n\n- Living room");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(true);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(false);
    // set filter type
    roomAvailabilityFilterType = "Ambassador Suite";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type B' to true, selected. Other Radio Buttons (All,A,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomEagleView(MouseEvent mouseEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleB.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Eagle View Condo");
    RoomDescription1.setText("- Kitchen \n\n- 4 Bed Room \n\n- Dining room");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(true);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(false);
    // set filter type
    roomAvailabilityFilterType = "Eagle View Condo";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type C' to true, selected. Other Radio Buttons (All,A,B,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomPoolSide(MouseEvent mouseEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleC.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Pool Side Condo");
    RoomDescription1.setText("- 4 Bed Room \n\n- Dining room \n\n- Kitchen");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(true);
    radioBtnRoomTypeJunior.setSelected(false);
    // set filter type
    roomAvailabilityFilterType = "Pool Side Condo";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type D' to true, selected. Other Radio Buttons (All,A,B,C) are set
  // to false(deselected)
  public void RadioBtnClickedRoomJunior(MouseEvent mouseEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleA.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Junior Condo");
    RoomDescription1.setText("- 3 Bed Room \n\n- Kitchen \n\n- Living room");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(true);
    // set filter type
    roomAvailabilityFilterType = "Junior Condo";
    // call method to update search results
    updateResults();
  }

  // This declares the 'Home' button on Scene 4 'Find Room'. This changes the scene back to 1,
  // 'Title'.
  public void btnClickedHome4(ActionEvent actionEvent) throws IOException {
    /*
    Stage thisStage = (Stage) GoToScene1From4.getScene().getWindow();
    Parent loginScene = FXMLLoader.load(getClass().getResource("../TitleScene/Title.fxml"));
    thisStage.setScene(new Scene(loginScene, 750, 500));
    */
    //get a reference to the window we are in
    Stage window = (Stage) GoToScene1From4.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../TitleScene/Title.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent title = loader.load();

    // get controller for Title page
    TitleController titleController = loader.getController();
    // set username if it is has been set
    if (sessionInformation.getUserName() != null) {
      titleController.setSessionInformation(sessionInformation);
    }
    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);
  }

  // update method called every time a date change for checkin or checkout is made
  @FXML
  void updateAvailability(ActionEvent event) throws SQLException {
    // call method to update search results..method is separate so other actions on page can
    // call the same update
    updateResults();
  }

  public void updateResults() throws SQLException {
    // only update if a start and end date have been selected
    if (datePickerStart.getValue() != null && datePickerEnd.getValue() != null) {
      String checkIn = Date.valueOf(datePickerStart.getValue()).toString();
      String checkOut = Date.valueOf(datePickerEnd.getValue()).toString();

      // clear list before each update
      tvAvailableRooms.getItems().clear();

      ArrayList<AvailableRoom> results = DatabaseAgent
          .getAvailableRooms(checkIn, checkOut, roomAvailabilityFilterType);

      for (AvailableRoom availableRoom : results) {
        tvAvailableRooms.getItems().add(availableRoom);
      }

    }
  }
}