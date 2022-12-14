package Resort.TitleScene;

import Resort.CreateAccountScene.CreateAccountController;
import Resort.Main;
import Resort.ManagerViewScene.ManagerViewController;
import Resort.MyAccountScene.MyAccountController;
import Resort.RoomFinderScene.RoomFinderController;
import Resort.Utility.DatabaseAgent;
import Resort.Utility.SessionInformation;
import java.io.IOException;
import java.nio.file.Path;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller class for the login scene. Handles logging in or making a new account. todo: make
 * separate controllers for each scene of the application.
 */
public class TitleController {


  @FXML private AnchorPane apLoginPrompt;
  @FXML private AnchorPane apLoggedIn;

  @FXML private Button btnRoomFinder;
  @FXML private Button btnNewUser;
  @FXML private Button btnManagerView;

  @FXML private ImageView ivRoomFinder;
  @FXML private ImageView ivTitlePage;

  @FXML private Label lblLoginValidation;
  @FXML private Label lblLoggedInUsername;

  @FXML private PasswordField pfLoginPassword;

  @FXML private TextField tfFirstNameLogin;
  @FXML private Label lblDescription;
  @FXML private Label lblTempDescription;
  @FXML private Label lblTitleLabel;
  @FXML private Pane titleBackGround;


  //Fields to hold the Username and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();


  /**
   *  method for setting username if logged in
   * @param sessionInformation the information from the tab that stores login information.
   */
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
    updateLoginPane();
  }

  /**
   *  method for setting visibility of LoggedInPane or login prompt pane
   */
  private void updateLoginPane() {
    // if logged in, set prompt to not visible and set loggedInpane to visible and set username
    if (sessionInformation.getUserName() != null) {
      apLoggedIn.setVisible(true);
      apLoginPrompt.setVisible(false);
      lblLoggedInUsername.setText(sessionInformation.getUserName());
    } else {
      apLoginPrompt.setVisible(true);
      apLoggedIn.setVisible(false);
    }
    // if the user is a manager show the Manager View button
    if (sessionInformation.isManager()) {
      btnManagerView.setVisible(true);
    } else {
      btnManagerView.setVisible(false);
    }

  }

  /**
   * Detects button press and sends user to create account screen.
   * @param event accepts mouse click event when user clicks the button
   * @throws IOException exception if input is invalid.
   */
  @FXML
  void clickNewUser(MouseEvent event) throws IOException {

    Stage window = (Stage) btnNewUser.getScene().getWindow();

// changing scenes code
    Stage thisStage = (Stage) btnNewUser.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(CreateAccountController.class.getResource("CreateAccount.fxml"));
    Parent title = loader.load();

    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);

  }

  @FXML
  void clickRoomFinder(ActionEvent event) throws IOException {
// changing scenes code

    //get a reference to the window we are in
    Stage window = (Stage) btnRoomFinder.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader(RoomFinderController.class.getResource("RoomFinder.fxml"));


    //loader.setLocation(getClass().getResource("../RoomFinderScene/RoomFinder.fxml"));


    System.out.println(getClass().getResource("Resort"));


    // create a parent class with our loader pointing at the new scene
    Parent roomFinder = loader.load();

    // get controller for Title page
    RoomFinderController roomFinderController = loader.getController();

    // if username is set, set in controller before changing scene
    if (sessionInformation.getUserName() != null) {
      roomFinderController.setSessionInformation(sessionInformation);
    }
    // make the new scene we are going to
    Scene titleScene = new Scene(roomFinder);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);

  }

  /**
   * Tests for valid login information then changes the screen to the logged in screen.
   * @param event accepts mouse click from user
   */
  @FXML
  void clickLogin(MouseEvent event) {
    String username = tfFirstNameLogin.getText();
    String password = pfLoginPassword.getText();

    System.out.println(username);
    System.out.println(password);

    //boolean managerCheck = ckbManagerLogin.isSelected();

    if (!password.equals("")) {
      if (DatabaseAgent.checkLoginInformation(username, password)) {
        System.out.println("Logged in successfully");

        // set session information to include username, userID and if is manager
        sessionInformation.setUserName(username);

        // I will set the manager flag here //////////////////////////////////////////////////////////////
        sessionInformation.setManager(DatabaseAgent.checkIfManager(username));

        updateLoginPane();

      } else {
        System.out.println("Username or password incorrect");
        createLoginValidator(false);
      }
    } else {
      System.out.println("Must enter password");
      createLoginValidator(false);

    }
  }


  private FadeTransition loginFadeOut = new FadeTransition(Duration.millis(2000));

  // Transition effect for fading out Success/Failed indicators
  private FadeTransition fadeOut = new FadeTransition(
      Duration.millis(2000)
  );

  /**
   * Show some validation on the screen so the user knows if login worked.
   * @param success represents if the login function worked or not.
   */
  private void createLoginValidator(boolean success) {
    if (success) {
      lblLoginValidation.setText("Incorrect First Name or Password");
    }
    lblLoginValidation.setVisible(true);
    loginFadeOut.playFromStart();

  }

  @FXML
  void btnClickManagerView(MouseEvent event) throws IOException {
    // changing scenes code
    //get a reference to the window we are in
    Stage window = (Stage) btnManagerView.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader(ManagerViewController.class.getResource("ManagerView.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent myAccountParent = loader.load();
    // make the new scene we are going to
    Scene myAccountScene = new Scene(myAccountParent);

    // get a reference to the controller for the scene we are going to
    ManagerViewController managerViewController = loader.getController();

    // initialize necessary data in the controller before making the scene change
    managerViewController.setSessionInformation(sessionInformation);

    // initiate the scene change
    window.setScene(myAccountScene);
  }


  @FXML
  void btnClickMyAccount(MouseEvent event) throws IOException {
    // changing scenes code
    //get a reference to the window we are in
    Stage window = (Stage) lblLoginValidation.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader(MyAccountController.class.getResource("MyAccount.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent myAccountParent = loader.load();
    // make the new scene we are going to
    Scene myAccountScene = new Scene(myAccountParent);

    // get a reference to the controller for the scene we are going to
    MyAccountController myAccountController = loader.getController();

    // initialize necessary data in the controller before making the scene change
    myAccountController.setSessionInformation(sessionInformation);

    // initiate the scene change
    window.setScene(myAccountScene);
  }

  /**
   * Signs the user out if they're already logged into an account.
   * @param event handles mouse click event
   */
  @FXML
  void btnClickSignOut(MouseEvent event) {
    sessionInformation.setManager(false);
    sessionInformation.setUserName(null);
    updateLoginPane();
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

    Resort.RoomFinderScene.RoomFinderController.pictureBorder(ivTitlePage);
    Resort.RoomFinderScene.RoomFinderController.pictureBorder(ivRoomFinder);

    // testing set visibility for loggedinPane or loginPrompt depending on if username is set
    updateLoginPane();


  }

}

 /* dock.jpg    Free to reuse from Pixabay.com, free-to-use website.
    https://pixabay.com/photos/pier-jetty-ocean-sea-water-way-569314/*/

 /* roomFinderButton.jpg    Free to reuse from Pixabay.com, free-to-use website.
    https://pixabay.com/photos/hotel-room-home-bed-inner-luxury-4416515/*/
