package Resort.TitleScene;

import Resort.ManagerViewScene.ManagerViewController;
import Resort.MyAccountScene.MyAccountController;
import Resort.RoomFinderScene.RoomFinderController;
import Resort.Utility.DatabaseAgent;
import Resort.Utility.SessionInformation;
import java.io.IOException;
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

  @FXML private CheckBox ckbManagerLogin;

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


  // Fields to hold the Username and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();


  // method for setting username if logged in
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
    updateLoginPane();
  }

  // method for setting visibility of LoggedInPane or login prompt pane
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

  @FXML
  void clickNewUser(MouseEvent event) throws IOException {

// changing scenes code
    Stage thisStage = (Stage) btnNewUser.getScene().getWindow();
    Parent loggedInScene = FXMLLoader.load(getClass().getResource(
        "../CreateAccountScene/CreateAccount.fxml"));
    thisStage.setScene(new Scene(loggedInScene, 1000, 600));

  }

  @FXML
  void clickRoomFinder(ActionEvent event) throws IOException {
// changing scenes code
    /*
    Stage thisStage = (Stage) btnRoomFinder.getScene().getWindow();
    Parent loggedInScene = FXMLLoader.load(getClass().getResource(
        "../RoomFinderScene/RoomFinder.fxml"));
    thisStage.setScene(new Scene(loggedInScene, 750, 500));
    */
    //get a reference to the window we are in
    Stage window = (Stage) btnRoomFinder.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../RoomFinderScene/roomFinder.fxml"));

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

  @FXML
  void clickLogin(MouseEvent event) {
    String username = tfFirstNameLogin.getText();
    String password = pfLoginPassword.getText();

    boolean managerCheck = ckbManagerLogin.isSelected();

    if (!managerCheck && DatabaseAgent.checkLoginInformation(username, password)) {
      System.out.println("Logged in successfully");

      // set session information to include username, userID and if is manager
      sessionInformation.setUserName(username);

      updateLoginPane();

    } else if (managerCheck && DatabaseAgent.checkLoginInformation(username, password)) {
      System.out.println("Manager Login");

      // set session information
      sessionInformation.setUserName(username);
      sessionInformation.setManager(true);

      // change visibility for login pane and manager button
      updateLoginPane();

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

  @FXML
  void btnClickManagerView(MouseEvent event) throws IOException {
    // changing scenes code
    //get a reference to the window we are in
    Stage window = (Stage) ckbManagerLogin.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../ManagerViewScene/ManagerView.fxml"));

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
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../MyAccountScene/MyAccount.fxml"));

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
