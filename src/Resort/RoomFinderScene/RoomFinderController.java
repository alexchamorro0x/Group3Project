package Resort.RoomFinderScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomFinderController implements Initializable {
  @FXML private Button btnHome;
  @FXML private Button GoToScene1From4;
  @FXML private ImageView RoomLayoutPicture;
  @FXML private Text RoomTypeText;
  @FXML private Text RoomDescription1;
  @FXML private Text RoomDescription2;
  @FXML private Text RoomDescription3;

  @FXML private RadioButton RadioBtnRoomTypeA;
  @FXML private RadioButton RadioBtnRoomTypeB;
  @FXML private RadioButton RadioBtnRoomTypeC;
  @FXML private RadioButton RadioBtnRoomTypeD;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  // Sets the Radio Button for 'Room Type A' to true, selected. Other Radio Buttons (B,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomA(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomLayoutSampleA.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room A");
    RoomDescription1.setText("3 Bed Room");
    RoomDescription2.setText("kitchen");
    RoomDescription3.setText("living room");
    RadioBtnRoomTypeA.setSelected(true);
    RadioBtnRoomTypeB.setSelected(false);
    RadioBtnRoomTypeC.setSelected(false);
    RadioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type B' to true, selected. Other Radio Buttons (A,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomB(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomLayoutSampleB.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room B");
    RoomDescription1.setText("kitchen");
    RoomDescription2.setText("4 Bed Room");
    RoomDescription3.setText("dining room");
    RadioBtnRoomTypeA.setSelected(false);
    RadioBtnRoomTypeB.setSelected(true);
    RadioBtnRoomTypeC.setSelected(false);
    RadioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type C' to true, selected. Other Radio Buttons (A,B,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomC(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomLayoutSampleC.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room C");
    RoomDescription1.setText("4 Bed Room");
    RoomDescription2.setText("dining room");
    RoomDescription3.setText("kitchen");
    RadioBtnRoomTypeA.setSelected(false);
    RadioBtnRoomTypeB.setSelected(false);
    RadioBtnRoomTypeC.setSelected(true);
    RadioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type D' to true, selected. Other Radio Buttons (A,B,C) are set
  // to false(deselected)
  public void RadioBtnClickedRoomD(MouseEvent mouseEvent) {
    // sets the image for the room layout from local file
    File RoomA = new File("src/Resort/RoomLayoutSampleD.png");
    Image RoomAImage = new Image(RoomA.toURI().toString());
    RoomLayoutPicture.setImage(RoomAImage);
    // sets the room title and 3 description text boxes
    RoomTypeText.setText("Room D");
    RoomDescription1.setText("5 Bed Room");
    RoomDescription2.setText("kitchen");
    RoomDescription3.setText("living room");
    RadioBtnRoomTypeA.setSelected(false);
    RadioBtnRoomTypeB.setSelected(false);
    RadioBtnRoomTypeC.setSelected(false);
    RadioBtnRoomTypeD.setSelected(true);
  }

  // This declares the 'Home' button on Scene 4 'Find Room'. This changes the scene back to 1,
  // 'Title'.
  public void btnClickedHome4(ActionEvent actionEvent) throws IOException {
    Stage thisStage = (Stage) GoToScene1From4.getScene().getWindow();
    Parent loginScene = FXMLLoader.load(getClass().getResource("../TitleScene/Title.fxml"));
    thisStage.setScene(new Scene(loginScene, 750, 500));
  }
}
