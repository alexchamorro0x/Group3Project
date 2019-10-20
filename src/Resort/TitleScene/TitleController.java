package Resort.TitleScene;

import Resort.Utility.DatabaseCrud;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller class for the login scene. Handles logging in or making a new account. todo: make
 * separate controllers for each scene of the application.
 */
public class TitleController {

  @FXML
  private Button btnRoomFinder;

  @FXML
  private Label lblNewUser;

  @FXML
  private Label lblTitleLabel;

  @FXML
  private ImageView ivTitlePage;

  @FXML
  private TextField tfFirstNameLogin;

  @FXML
  private PasswordField pfLoginPassword;

  @FXML
  private Label lblLoginValidation;

  @FXML
  private Label lblDescription;

  @FXML
  void clickNewUser(MouseEvent event) throws IOException {

// changing scenes code
    Stage thisStage = (Stage) lblNewUser.getScene().getWindow();
    Parent loggedInScene = FXMLLoader.load(getClass().getResource(
        "../CreateAccountScene/CreateAccount.fxml"));
    thisStage.setScene(new Scene(loggedInScene, 750, 500));

  }

  @FXML
  void clickRoomFinder(ActionEvent event) throws IOException {
// changing scenes code
    Stage thisStage = (Stage) btnRoomFinder.getScene().getWindow();
    Parent loggedInScene = FXMLLoader.load(getClass().getResource(
        "../RoomFinderScene/RoomFinder.fxml"));
    thisStage.setScene(new Scene(loggedInScene, 750, 500));
  }

  @FXML
  void clickLogin(MouseEvent event) throws IOException {
    DatabaseCrud updater = new DatabaseCrud();
    String name = tfFirstNameLogin.getText();
    String password = pfLoginPassword.getText();

    if (updater.checkLoginInformation(name, password)) {
      System.out.println("Logged in successfully");
      // changing scenes code
      Stage thisStage = (Stage) lblLoginValidation.getScene().getWindow();
      Parent loggedInScene = FXMLLoader.load(getClass().getResource(
          "../MyAccountScene/MyAccount.fxml"));
      thisStage.setScene(new Scene(loggedInScene, 750, 500));
    } else {
      System.out.println("Username or password incorrect");
      createLoginValidator(false);
    }
  }

  private FadeTransition loginFadeOut = new FadeTransition(Duration.millis(2000));

  // Transition effect for fading out Success/Failed indicators
  private FadeTransition fadeOut = new FadeTransition(
      Duration.millis(2000)
  );


  private void createLoginValidator(boolean success) {
    if (success) {
      lblLoginValidation.setText("Incorrect First Name or Password");
    }
    lblLoginValidation.setVisible(true);
    loginFadeOut.playFromStart();

  }

  /**
   * Initialize method that gets called once the scene is open and the controller is initialized.
   */
  public void initialize() {
    // set create account success/fail label none visible
    // and prepare fade graphic for it
    lblLoginValidation.setVisible(false);
    loginFadeOut.setNode(lblLoginValidation);
    loginFadeOut.setFromValue(1.0);
    loginFadeOut.setToValue(0.0);
    loginFadeOut.setCycleCount(1);
    loginFadeOut.setAutoReverse(false);


  }

}
