package Resort.EditAccountScene;

import Resort.TitleScene.TitleController;
import Resort.Utility.AccountInformation;
import Resort.Utility.DatabaseAgent;
import Resort.Utility.SessionInformation;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditAccountController {

  @FXML
  private AnchorPane btnCreateAccount;

  @FXML
  private TextField tfFirstName;

  @FXML
  private TextField tfLastName;

  @FXML
  private TextField tfUsername;

  @FXML
  private TextField tfEmail;

  @FXML
  private TextField tfPassword;

  @FXML
  private TextField tfConfirmPassword;

  @FXML
  private TextField tfAddress;

  @FXML
  private TextField tfState;

  @FXML
  private TextField tfzipcode;

  @FXML
  private TextField tfCreditCardNumber;

  @FXML
  private TextField tfCvv;

  @FXML
  private Label lblCreateIndicate;

  // String to hold our username if logged in and if user is a manager
  private SessionInformation sessionInformation = new SessionInformation();

  // setter for session information
  public void setSessionInformation(SessionInformation sessionInformation) {
    this.sessionInformation = sessionInformation;
  }

  @FXML
  void btnClickUpdateAccount(MouseEvent event) {
    //todo add logic to update the account in the database
  }

  @FXML
  void btnClickHome(MouseEvent event) throws IOException {
    //get a reference to the window we are in
    Stage window = (Stage) btnCreateAccount.getScene().getWindow();

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

  public void setSessionformation(SessionInformation sessionformation) {
    this.sessionInformation = sessionformation;
    AccountInformation userAccount = DatabaseAgent.getAccountInformation(sessionformation.getUserName());
    tfFirstName.setText(userAccount.getFirstName());
    tfLastName.setText(userAccount.getLastName());
    tfUsername.setText(userAccount.getUserName());
    tfEmail.setText(userAccount.getEmail());
    tfAddress.setText(userAccount.getAddress());
    tfState.setText(userAccount.getState());
    tfzipcode.setText(userAccount.getZipCode());
  }

}
