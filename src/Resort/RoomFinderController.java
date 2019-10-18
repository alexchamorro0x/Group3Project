package Resort;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomFinderController implements Initializable {

  @FXML private Button GoToScene1From4;

  @FXML private RadioButton RadioBtnRoomTypeA;
  @FXML private RadioButton RadioBtnRoomTypeB;
  @FXML private RadioButton RadioBtnRoomTypeC;
  @FXML private RadioButton RadioBtnRoomTypeD;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // RoomLayoutSample.setVisible(false);
  }

  // Sets the Radio Button for 'Room Type A' to true, selected. Other Radio Buttons (B,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomA(MouseEvent mouseEvent) {
    RadioBtnRoomTypeA.setSelected(true);
    RadioBtnRoomTypeB.setSelected(false);
    RadioBtnRoomTypeC.setSelected(false);
    RadioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type B' to true, selected. Other Radio Buttons (A,C,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomB(MouseEvent mouseEvent) {
    RadioBtnRoomTypeA.setSelected(false);
    RadioBtnRoomTypeB.setSelected(true);
    RadioBtnRoomTypeC.setSelected(false);
    RadioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type C' to true, selected. Other Radio Buttons (A,B,D) are set
  // to false(deselected)
  public void RadioBtnClickedRoomC(MouseEvent mouseEvent) {
    RadioBtnRoomTypeA.setSelected(false);
    RadioBtnRoomTypeB.setSelected(false);
    RadioBtnRoomTypeC.setSelected(true);
    RadioBtnRoomTypeD.setSelected(false);
  }
  // Sets the Radio Button for 'Room Type D' to true, selected. Other Radio Buttons (A,B,C) are set
  // to false(deselected)
  public void RadioBtnClickedRoomD(MouseEvent mouseEvent) {
    RadioBtnRoomTypeA.setSelected(false);
    RadioBtnRoomTypeB.setSelected(false);
    RadioBtnRoomTypeC.setSelected(false);
    RadioBtnRoomTypeD.setSelected(true);
  }

  // This declares the 'Home' button on Scene 4 'Find Room'. This changes the scene back to 1,
  // 'Title'.
  public void btnClickedHome4(ActionEvent actionEvent) throws IOException {
    Stage thisStage = (Stage) GoToScene1From4.getScene().getWindow();
    Parent loginScene = FXMLLoader.load(getClass().getResource("Title.fxml"));
    thisStage.setScene(new Scene(loginScene, 750, 500));
  }
}
