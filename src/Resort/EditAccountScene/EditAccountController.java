package Resort.EditAccountScene;

import Resort.Utility.DatabaseAgent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Resort.ManagerViewScene.ManagerViewController;
import Resort.TitleScene.TitleController;
import Resort.Utility.AccountInformation;
import Resort.Utility.SessionInformation;
import javafx.scene.control.Button;

public class EditAccountController {

  @FXML private ComboBox expireMonth;
  @FXML private ComboBox expireYear;
  @FXML private ImageView homeLogo;
  @FXML private Label lblCreateIndicate;
  @FXML private TextField tfFirstName;
  @FXML private TextField tfLastName;
  @FXML private TextField tfUsername;
  @FXML private TextField tfEmail;
  @FXML private TextField tfPassword;
  @FXML private TextField tfAddress;
  @FXML private TextField tfState;
  @FXML private TextField tfzipcode;
  @FXML private TextField tfCreditCardNumber;
  @FXML private TextField tfCvv;
  @FXML private TextField tfConfirmPassword;
  @FXML private Button btnReturnToManager;
  ObservableList<String> expireMonthList =
      FXCollections.observableArrayList(
          "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
  ObservableList<String> expireYearList =
      FXCollections.observableArrayList(
          "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
          "2030");

  // String to hold our username if logged in and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();

  private String userNameForManagerEdit;

  public void initialize() {
    expireMonth.setItems(expireMonthList);
    expireYear.setItems(expireYearList);

    lblCreateIndicate.setVisible(false);
    btnReturnToManager.setVisible(false);

    File RoomA = new File("src/Resort/RoomFinderScene/pineapple.png");
    Image pineapple = new Image(RoomA.toURI().toString());
    homeLogo.setImage(pineapple);
    homeLogo.setFitWidth(65);
    homeLogo.setFitHeight(100);
  }

  // Setter so that when called from manager view to edit another users account this can be set
  public void setUserNameForManagerEdit(String userNameForManagerEdit) {
    this.userNameForManagerEdit = userNameForManagerEdit;

    btnReturnToManager.setVisible(true);

    AccountInformation userAccount = DatabaseAgent.getAccountInformation(userNameForManagerEdit);

    tfFirstName.setText(userAccount.getFirstName());
    tfLastName.setText(userAccount.getLastName());
    tfUsername.setText(userAccount.getUserName());
    tfEmail.setText(userAccount.getEmail());
    tfAddress.setText(userAccount.getAddress());
    tfState.setText(userAccount.getState());
    tfzipcode.setText(userAccount.getZipCode());
    tfPassword.setText(userAccount.getPassWord());
    tfConfirmPassword.setText(userAccount.getPassWord());
    tfCvv.setText(userAccount.getCvv());
  }

  // setter for session information
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
  }

  // setter for session information
  public void setSessionInformation(SessionInformation sessionInformation, String targetUser) {
    this.sessionInformation = sessionInformation;
    userNameForManagerEdit = targetUser;
  }

  @FXML
  void btnClickUpdateAccount(MouseEvent event) {
    // todo add logic to update the account in the database
  }

  @FXML
  void btnClickHome(MouseEvent event) throws IOException {
    // get a reference to the window we are in
    Stage window = (Stage) tfUsername.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../TitleScene/Title.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent title = loader.load();

    // get controller for Title page
    TitleController titleController = loader.getController();
    titleController.setSessionInformation(sessionInformation);
    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);
  }

  public void btnHomeEntered(MouseEvent mouseEvent) {
    homeLogo.setFitHeight(70);
    homeLogo.setFitHeight(105);

    Glow glow = new Glow();
    glow.setLevel(.15);
    homeLogo.setEffect(glow);

    Resort.RoomFinderScene.RoomFinderController.pictureBorder(homeLogo);
  }

  public void btnHomeExited(MouseEvent mouseEvent) {
    homeLogo.setFitHeight(65);
    homeLogo.setFitHeight(100);
    File RoomA = new File("src/Resort/RoomFinderScene/pineapple.png");
    Image pineapple = new Image(RoomA.toURI().toString());
    homeLogo.setImage(pineapple);
  }

  public void setSessioninformation(SessionInformation sessionformation) {
    this.sessionInformation = sessionformation;

    // If this is called from the manager view to edit another users information set info for that
    // user
    AccountInformation userAccount =
        DatabaseAgent.getAccountInformation(sessionformation.getUserName());

    System.out.println("Test");

    tfFirstName.setText(userAccount.getFirstName());
    tfLastName.setText(userAccount.getLastName());
    tfUsername.setText(userAccount.getUserName());
    tfEmail.setText(userAccount.getEmail());
    tfAddress.setText(userAccount.getAddress());
    tfState.setText(userAccount.getState());
    tfzipcode.setText(userAccount.getZipCode());
    tfPassword.setText(userAccount.getPassWord());
    tfConfirmPassword.setText(userAccount.getPassWord());
    tfCvv.setText(userAccount.getCvv());
  }

  @FXML
  void clickReturnToManager(ActionEvent event) throws IOException {
    Stage window = (Stage) tfUsername.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../ManagerViewScene/ManagerView.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent managerView = loader.load();

    // get controller for Title page
    ManagerViewController managerViewController = loader.getController();

    managerViewController.setSessionInformation(sessionInformation);

    // make the new scene we are going to
    Scene managerScene = new Scene(managerView);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(managerScene);
  }

  @FXML
  void clickUpdateAccount(ActionEvent event) throws SQLException {
    int userId = DatabaseAgent.getAccountInformation(sessionInformation.getUserName()).getUserId();
    // if a manager is editing someones account username needs to be reassigned
    if (userNameForManagerEdit != null) {
      userId = DatabaseAgent.getAccountInformation(userNameForManagerEdit).getUserId();
    }
    AccountInformation accountInformation = new AccountInformation();
    accountInformation.setFirstName(tfFirstName.getText());
    accountInformation.setLastName(tfLastName.getText());
    accountInformation.setPassWord(tfPassword.getText());
    accountInformation.setUserName(tfUsername.getText());
    accountInformation.setEmail(tfEmail.getText());
    accountInformation.setAddress(tfAddress.getText());
    accountInformation.setState(tfState.getText());
    accountInformation.setZipCode(tfzipcode.getText());
    accountInformation.setCreditCardNumber(tfCreditCardNumber.getText());
    accountInformation.setCvv(tfCvv.getText());
    accountInformation.setUserId(userId);

    DatabaseAgent.updateAccount(accountInformation);
  }

  /*
   public CreateAccountController(Stage CreateAccount) throws IOException {

     Parent root = FXMLLoader.load(getClass().getResource("CreateAccountScene/CreateAccount.fxml"));
     CreateAccount.setTitle("Resort Reservations");
     root.getStylesheets().add
             (Main.class.getResource("resortTemplate.css").toExternalForm());
     CreateAccount.setScene(new Scene(root, 850, 530));
     CreateAccount.show();
   }

  */

}
