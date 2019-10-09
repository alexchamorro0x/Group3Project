package Resort;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Controller class for the login scene. Handles logging in or making a new account. todo: make
 * separate controllers for each scene of the application.
 */
public class Controller {

  @FXML
  private Label lblLoginValidation;

  @FXML
  private AnchorPane btnCreateAccount;

  @FXML
  private TextField tfCreateFirstName;

  @FXML
  private TextField tfCreateLastName;

  @FXML
  private TextField tfCreatePassword;

  @FXML
  private Label lblCreateIndicate;

  @FXML
  private TextField tfFirstNameLogin;

  @FXML
  private PasswordField pfLoginPassword;

  /**
   * Handler for clicking the create account button. Extracts account information from text fields
   * and makes a call to DatabaseCrud addAccount method if fields are populated.
   *
   * @param event records when the mouse clicks the create account button
   */
  @FXML
  void clickCreateAccount(MouseEvent event) {
    DatabaseCrud executor = new DatabaseCrud();
    String firstName = tfCreateFirstName.getText();
    String lastName = tfCreateLastName.getText();
    String password = tfCreatePassword.getText();
    if (tfCreateLastName.getLength() != 0 && tfCreateFirstName.getLength() != 0
        && tfCreatePassword.getLength() != 0) {
      executor.addUser(firstName, lastName, password);
      createIndicator(true);
    } else {
      createIndicator(false);
    }

  }

  @FXML
  void clickLogin(MouseEvent event) {
    DatabaseCrud updater = new DatabaseCrud();
    String name = tfFirstNameLogin.getText();
    String password = pfLoginPassword.getText();

    if (updater.checkLoginInformation(name, password)) {
      System.out.println("Logged in successfully");
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

  /**
   * Indicator that shows success or failed while attempting to make an account. Fades out over 2
   * seconds.
   *
   * @param success determines the success of creating a new account
   */
  private void createIndicator(boolean success) {
    if (success) {
      lblCreateIndicate.setText("Success");
    } else {
      lblCreateIndicate.setText("Failed");
    }
    lblCreateIndicate.setVisible(true);
    fadeOut.playFromStart();
  }

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
    lblCreateIndicate.setVisible(false);
    fadeOut.setNode(lblCreateIndicate);
    fadeOut.setFromValue(1.0);
    fadeOut.setToValue(0.0);
    fadeOut.setCycleCount(1);
    fadeOut.setAutoReverse(false);

    lblLoginValidation.setVisible(false);
    loginFadeOut.setNode(lblLoginValidation);
    loginFadeOut.setFromValue(1.0);
    loginFadeOut.setToValue(0.0);
    loginFadeOut.setCycleCount(1);
    loginFadeOut.setAutoReverse(false);


  }


}
