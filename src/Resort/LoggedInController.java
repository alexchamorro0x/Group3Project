package Resort;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoggedInController {

  @FXML
  private Button btnLogOut;

  @FXML
  void btnClicklogOut(MouseEvent event) throws IOException {
    Stage thisStage = (Stage) btnLogOut.getScene().getWindow();

    Parent loginScene = FXMLLoader.load(getClass().getResource("login.fxml"));
    thisStage.setScene(new Scene(loginScene, 500, 500));

  }

}
