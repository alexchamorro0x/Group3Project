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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

  @FXML private Button btnHome;
  @FXML private Button btnBookRoom;
  @FXML private ImageView RoomLayoutPicture;
  @FXML private ImageView homeLogo;
  @FXML private Text RoomTypeText;
  @FXML private Text RoomDescription1;
  @FXML private Label lblInvalidDate;
  @FXML private Label RoomFinderTitle;

  @FXML private AnchorPane APdates;
  @FXML private AnchorPane AProoms;

  @FXML private RadioButton radioBtnRoomTypeAll;
  @FXML private RadioButton radioBtnRoomTypeAmbassador;
  @FXML private RadioButton radioBtnRoomTypeEagleView;
  @FXML private RadioButton radioBtnRoomTypePoolSide;
  @FXML private RadioButton radioBtnRoomTypeJunior;
  @FXML private DatePicker datePickerStart;
  @FXML private DatePicker datePickerEnd;

  @FXML private TableView<AvailableRoom> tvAvailableRooms;
  @FXML private TableColumn<?, ?> tvRoomType;
  @FXML private TableColumn<?, ?> tvRoomNumber;
  @FXML private AnchorPane roomDescriptionBorder;

  // variable to track what room type we are filtering by (default = all)
  String roomAvailabilityFilterType = "all";

  // String to hold our username if logged in and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();

  // setter for setting session information
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
  }

  public SessionInformation getSessionInformation(SessionInformation sessionInformation) {
    return sessionInformation;
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

    // https://stackoverflow.com/questions/12717487/how-to-implement-a-transparent-pane-with-non-transparent-children

    // start by setting All filter to true
    radioBtnRoomTypeAll.setSelected(true);

    // Initialize the availability table view by associating the data fields from AvailableRooms
    // class with the columns in the table
    tvRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    tvRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

    // clears the 'No Content" message on the blank tableView.
    tvAvailableRooms.setPlaceholder(new Label(""));
    tvAvailableRooms.setVisible(false);

    File RoomA = new File("src/Resort/RoomFinderScene/pineapple.png");
    Image pineapple = new Image(RoomA.toURI().toString());
    homeLogo.setImage(pineapple);
    homeLogo.setFitHeight(65);
    homeLogo.setFitHeight(100);

    File Room = new File("src/Resort/RoomFinderScene/allRooms.png");
    Image RoomAImage = new Image(Room.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    pictureBorder(RoomLayoutPicture);

    fadeIn(RoomLayoutPicture);
    fadeIn(RoomFinderTitle);
    fadeIn(APdates);
    fadeIn(AProoms);
    fadeIn(btnBookRoom);
    fadeIn2(homeLogo);

    pictureBorder(homeLogo);

    roomDescriptionBorder.setVisible(false);
  }

  /*
   * Clicking the 'Book Room' button will print to the console the start and end date. This must be
   * implemented into communicating with the Database in the near future. If either of the
   * DatePicker fields are empty, the button will blink "Invalid Date" 3 times in red text directly
   * to the left of the button
   */
  public void btnClickedBookRoom() throws SQLException {
    // https://stackoverflow.com/questions/20446026/get-value-from-date-picker
    // date picker
    // https://stackoverflow.com/questions/43084698/flashing-label-in-javafx-gui
    // label animation

    // if not logged in, display message, else, code below
    if (tvAvailableRooms.getSelectionModel().getSelectedItem() == null) {
      lblInvalidDate.setStyle("-fx-font-size: 16");
      lblInvalidDate.setStyle("-fx-fill: red");
        fadeOut(lblInvalidDate,"Please select a room from the list");
        lblInvalidDate.setStyle("-fx-font-size: 25");
    } else {
      LocalDate dateStart = datePickerStart.getValue();
      LocalDate dateEnd = datePickerEnd.getValue();
      if (dateStart == null || dateEnd == null) {
        btnBookRoom.setDisable(true);
        displayInavlid(dateStart, dateEnd, lblInvalidDate);

        // Outputs 'invalid date' if the start date is after the end date
      } else if (dateStart.compareTo(dateEnd) > 0) {
        btnBookRoom.setDisable(true);
        displayInavlid(dateStart, dateEnd, lblInvalidDate);

      } else if (sessionInformation.getUserName() == null) {
        System.out.println("Please LogIn.");
        String message = "Please Log In.";
        displayInavlid(lblInvalidDate, message);

      } else {
        System.out.println("\nStart date: " + dateStart + "\nEnd date: " + dateEnd);
        AvailableRoom roomToBook = tvAvailableRooms.getSelectionModel().getSelectedItem();
        DatabaseAgent.insertIntoReservations(
            sessionInformation.getUserName(),
            roomToBook.getRoomNumber(),
            Date.valueOf(dateStart),
            Date.valueOf(dateEnd));
      }
      btnBookRoom.setDisable(false);
      updateResults();
    }
  }

  @FXML
  void updateAvailability(ActionEvent event) throws SQLException {
    // call method to update search results..method is separate so other actions on page can
    // call the same update

    // shows the TableView of availble rooms if the date pickers are set up correctly
    LocalDate dateStart = datePickerStart.getValue();
    LocalDate dateEnd = datePickerEnd.getValue();
    if (dateStart != null && dateEnd != null && dateStart.compareTo(dateEnd) < 0) {
      tvAvailableRooms.setVisible(true);
    }

    updateResults();
  }

  // Sets the Radio Button for 'Room Type All' to true, selected. Other Radio Buttons (A,B,C,D) are
  // set
  // to false(deselected)
  public void RadioBtnClickedRoomAll(ActionEvent actionEvent) throws SQLException {
    // all selected so image and text is set to null
    File RoomA = new File("src/Resort/RoomFinderScene/allRooms.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    pictureBorder(RoomLayoutPicture); // sets the room title and 3 description text boxes
    RoomTypeText.setText("");
    RoomDescription1.setText("");
    radioBtnRoomTypeAll.setSelected(true);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(false);
    roomDescriptionBorder.setVisible(false);
    // set filter type
    roomAvailabilityFilterType = "all";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type A' to true, selected. Other Radio Buttons (All,B,C,D) are
  // set
  // to false(deselected)
  public void RadioBtnClickedRoomAmbassador(ActionEvent actionEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/ambassadorSuite.jpg");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    pictureBorder(RoomLayoutPicture);

    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Ambassador Suite");
    RoomDescription1.setText("Luxurious 5 room\nocean view cabin");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(true);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(false);
    roomDescriptionBorder.setVisible(true);
    // set filter type
    roomAvailabilityFilterType = "Ambassador Suite";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type B' to true, selected. Other Radio Buttons (All,A,C,D) are
  // set
  // to false(deselected)
  public void RadioBtnClickedRoomEagleView(ActionEvent actionEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/eagleViewCondo.jpg");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    pictureBorder(RoomLayoutPicture);

    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Eagle View Condo");
    RoomDescription1.setText("Extravagant 4 room condo\nshowcasing a bird's eye\nview of the city");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(true);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(false);
    roomDescriptionBorder.setVisible(true);
    // set filter type
    roomAvailabilityFilterType = "Eagle View Condo";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type C' to true, selected. Other Radio Buttons (All,A,B,D) are
  // set
  // to false(deselected)
  public void RadioBtnClickedRoomPoolSide(ActionEvent actionEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/poolSideCondo.jpg");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    pictureBorder(RoomLayoutPicture);

    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Pool Side Condo");
    RoomDescription1.setText(
        "Relaxing 4 room condo\nnear the pool with a\nkitchen and dining room");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(true);
    radioBtnRoomTypeJunior.setSelected(false);
    roomDescriptionBorder.setVisible(true);
    // set filter type
    roomAvailabilityFilterType = "Pool Side Condo";
    // call method to update search results
    updateResults();
  }

  // Sets the Radio Button for 'Room Type D' to true, selected. Other Radio Buttons (All,A,B,C) are
  // set
  // to false(deselected)
  public void RadioBtnClickedRoomJunior(ActionEvent actionEvent) throws SQLException {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/juniorSuite.jpg");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    pictureBorder(RoomLayoutPicture);

    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Junior Condo");
    RoomDescription1.setText("Economic 3 room condo\nfeaturing a kitchen and\nliving area");
    radioBtnRoomTypeAll.setSelected(false);
    radioBtnRoomTypeAmbassador.setSelected(false);
    radioBtnRoomTypeEagleView.setSelected(false);
    radioBtnRoomTypePoolSide.setSelected(false);
    radioBtnRoomTypeJunior.setSelected(true);
    roomDescriptionBorder.setVisible(true);
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
    // get a reference to the window we are in
    Stage window = (Stage) btnHome.getScene().getWindow();

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

  public void updateResults() throws SQLException {
    // only update if a start and end date have been selected
    if (datePickerStart.getValue() != null && datePickerEnd.getValue() != null) {
      String checkIn = Date.valueOf(datePickerStart.getValue()).toString();
      String checkOut = Date.valueOf(datePickerEnd.getValue()).toString();

      // clear list before each update
      tvAvailableRooms.getItems().clear();

      ArrayList<AvailableRoom> results =
          DatabaseAgent.getAvailableRooms(checkIn, checkOut, roomAvailabilityFilterType);

      for (AvailableRoom availableRoom : results) {
        tvAvailableRooms.getItems().add(availableRoom);
      }
    }
  }

  // adds a shadow border to pictures from ImageView objects.
  public static void pictureBorder(ImageView RoomLayoutPicture) {
    // https://stackoverflow.com/questions/20489908/border-radius-and-shadow-on-imageview
    Rectangle clip =
        new Rectangle(RoomLayoutPicture.getFitWidth(), RoomLayoutPicture.getFitHeight());
    clip.setArcWidth(35);
    clip.setArcHeight(35);
    RoomLayoutPicture.setClip(clip);

    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    WritableImage image = RoomLayoutPicture.snapshot(parameters, null);

    RoomLayoutPicture.setClip(null);
    RoomLayoutPicture.setEffect(new DropShadow(20, Color.BLACK));
    RoomLayoutPicture.setImage(image);
  }

  private static void displayInavlid(LocalDate dateStart, LocalDate dateEnd, Label lblInvalidDate) {
    lblInvalidDate.setText("Invalid Date");
    lblInvalidDate.setTextFill(Color.RED);
    Timeline timeline =
        new Timeline(
            new KeyFrame(Duration.seconds(0.75), evt -> lblInvalidDate.setVisible(false)),
            new KeyFrame(Duration.seconds(0.35), evt -> lblInvalidDate.setVisible(true)));
    timeline.setCycleCount(3);
    timeline.play();
  }

  private static void displayInavlid(Label lblInvalidDate, String message) {
    lblInvalidDate.setText(message);
    lblInvalidDate.setTextFill(Color.RED);
    Timeline timeline =
        new Timeline(
            new KeyFrame(Duration.seconds(0.75), evt -> lblInvalidDate.setVisible(false)),
            new KeyFrame(Duration.seconds(0.35), evt -> lblInvalidDate.setVisible(true)));
    timeline.setCycleCount(3);
    timeline.play();
  }

  private static void fadeOut(Label x, String message) {
    // https://docs.oracle.com/javafx/2/api/javafx/animation/FadeTransition.html
    x.setText(message);
    x.setVisible(true);
    FadeTransition ft = new FadeTransition(Duration.millis(2400), (Node) x);
    ft.setToValue(0);
    ft.setFromValue(1);
    // ft.setCycleCount(4);
    // ft.setAutoReverse(true);
    ft.play();
  }

  private static void fadeIn(Object x) {
    // https://docs.oracle.com/javafx/2/api/javafx/animation/FadeTransition.html
    FadeTransition ft = new FadeTransition(Duration.millis(650), (Node) x);
    ft.setToValue(1);
    ft.setFromValue(0);
    // ft.setCycleCount(4);
    // ft.setAutoReverse(true);
    ft.play();
  }

  private static void fadeIn2(Object x) {
    FadeTransition ft = new FadeTransition(Duration.millis(550), (Node) x);
    ft.setToValue(1);
    ft.setFromValue(0);
    // ft.setCycleCount(4);
    // ft.setAutoReverse(true);
    ft.play();
  }

  public void btnHomeEntered(MouseEvent mouseEvent) {
    homeLogo.setFitHeight(70);
    homeLogo.setFitHeight(105);

    Glow glow = new Glow();
    glow.setLevel(.15);
    homeLogo.setEffect(glow);

    pictureBorder(homeLogo);
  }

  public void btnHomeExited(MouseEvent mouseEvent) {
    homeLogo.setFitHeight(65);
    homeLogo.setFitHeight(100);
    File RoomA = new File("src/Resort/RoomFinderScene/pineapple.png");
    Image pineapple = new Image(RoomA.toURI().toString());
    homeLogo.setImage(pineapple);
  }
}

/*
beach.png    Free to reuse from Pixabay.com, free-to-use website.
https://cdn.pixabay.com/photo/2017/01/20/00/30/maldives-1993704_960_720.jpg

poolsidecondo.jpg
https://pixabay.com/photos/pool-tropical-people-person-1395069/

eagleviewcondo.jpg
https://pixabay.com/photos/hotel-living-room-indoors-595121/

ambassadorSuite.jpg
https://pixabay.com/photos/the-lodge-mar-beach-relax-water-4586948/

juniorSuite.jpg
https://pixabay.com/photos/hotel-guest-room-new-1330846/




 */
