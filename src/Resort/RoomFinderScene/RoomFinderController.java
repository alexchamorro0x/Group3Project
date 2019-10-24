package Resort.RoomFinderScene;

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
  @FXML private Button btnHome;
  @FXML private Button GoToScene1From4;
  @FXML private ImageView RoomLayoutPicture;
  @FXML private Text RoomTypeText;
  @FXML private Text RoomDescription1;
  @FXML private Button btnBookRoom;
  @FXML private Label lblAvailableRooms;
  @FXML private Label lblInvalidDate;

  @FXML private RadioButton radioBtnRoomTypeA;
  @FXML private RadioButton radioBtnRoomTypeB;
  @FXML private RadioButton radioBtnRoomTypeC;
  @FXML private RadioButton radioBtnRoomTypeD;
  @FXML private DatePicker datePickerStart;
  @FXML private DatePicker datePickerEnd;

  /*
   runs this code at the start of the fxml file being launched. Hides the radio buttons and
   'Available Rooms' label until the date is selected. In the near future this will be implemented
   to show only the radio buttons for open rooms via communication with the Database. If no rooms
   are available, the 'Available Rooms' label will output this message.
  */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lblAvailableRooms.setVisible(false);
    radioBtnRoomTypeA.setVisible(false);
    radioBtnRoomTypeB.setVisible(false);
    radioBtnRoomTypeC.setVisible(false);
    radioBtnRoomTypeD.setVisible(false);
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
      lblAvailableRooms.setVisible(true);
      radioBtnRoomTypeA.setVisible(true);
      radioBtnRoomTypeB.setVisible(true);
      radioBtnRoomTypeC.setVisible(true);
      radioBtnRoomTypeD.setVisible(true);
    }
    btnBookRoom.setDisable(false);
  }

  // Sets the Radio Button for 'Room Type A' to true, selected. Other Radio Buttons (B,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomA(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleA.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room A");
    RoomDescription1.setText("- 3 Bed Room \n\n- Kitchen \n\n- Living room");
    radioBtnRoomTypeA.setSelected(true);
    radioBtnRoomTypeB.setSelected(false);
    radioBtnRoomTypeC.setSelected(false);
    radioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type B' to true, selected. Other Radio Buttons (A,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomB(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleB.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room B");
    RoomDescription1.setText("- Kitchen \n\n- 4 Bed Room \n\n- Dining room");
    radioBtnRoomTypeA.setSelected(false);
    radioBtnRoomTypeB.setSelected(true);
    radioBtnRoomTypeC.setSelected(false);
    radioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type C' to true, selected. Other Radio Buttons (A,B,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomC(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleC.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room C");
    RoomDescription1.setText("- 4 Bed Room \n\n- Dining room \n\n- Kitchen");
    radioBtnRoomTypeA.setSelected(false);
    radioBtnRoomTypeB.setSelected(false);
    radioBtnRoomTypeC.setSelected(true);
    radioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type D' to true, selected. Other Radio Buttons (A,B,C) are set
  // to false(deselected)
  public void RadioBtnClickedRoomD(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomFinderScene/RoomLayoutSampleD.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room D");
    RoomDescription1.setText("- 5 Bed Room \n\n- Kitchen \n\n- Living room");
    radioBtnRoomTypeA.setSelected(false);
    radioBtnRoomTypeB.setSelected(false);
    radioBtnRoomTypeC.setSelected(false);
    radioBtnRoomTypeD.setSelected(true);
  }

  // This declares the 'Home' button on Scene 4 'Find Room'. This changes the scene back to 1,
  // 'Title'.
  public void btnClickedHome4(ActionEvent actionEvent) throws IOException {
    Stage thisStage = (Stage) GoToScene1From4.getScene().getWindow();
    Parent loginScene = FXMLLoader.load(getClass().getResource("../TitleScene/Title.fxml"));
    thisStage.setScene(new Scene(loginScene, 750, 500));
  }
}
