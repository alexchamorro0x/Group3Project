package Resort.CreateAccountScene;

import Resort.Main;
import Resort.Utility.DatabaseAgent;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateAccountController {

  ObservableList<String> expireMonthList =  FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
  ObservableList<String> expireYearList =  FXCollections.observableArrayList("2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");


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

  @FXML

  private ComboBox expireMonth;

  @FXML
  private ComboBox expireYear;

  @FXML
  private void initialize() {


   expireMonth.setItems(expireMonthList);


    expireYear.setItems(expireYearList);

  }



  @FXML
  void btnClickHome(MouseEvent event) throws IOException {
    //get a reference to the window we are in
    Stage window = (Stage) tfUsername.getScene().getWindow();

    // declare and initialize a loader for the FXML scene we are going to
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../TitleScene/Title.fxml"));

    // create a parent class with our loader pointing at the new scene
    Parent title = loader.load();
    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);
  }

  @FXML
  void btnClickCreateAccount(MouseEvent event) {


    //todo add code to validate values and give appropriate error messages for incorrect values

    DatabaseAgent.addUser(tfUsername.getText(), tfFirstName.getText(), tfLastName.getText(),
        tfPassword.getText(),tfEmail.getText(), tfAddress.getText(), tfState.getText(),
        tfzipcode.getText() , tfCreditCardNumber.getText(), tfCvv.getText());
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



