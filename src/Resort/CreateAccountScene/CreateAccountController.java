package Resort.CreateAccountScene;

import Resort.Main;
import Resort.Utility.DatabaseAgent;

import java.io.File;
import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreateAccountController {

  @FXML private ComboBox expireMonth;
  @FXML private ComboBox expireYear;

  @FXML private ImageView homeLogo;

  @FXML private Label lblCreateIndicate;

  @FXML private TextField tfFirstName;
  @FXML private TextField tfLastName;
  @FXML private TextField tfUsername;
  @FXML private TextField tfEmail;
  @FXML private PasswordField tfPassword;
  @FXML private TextField tfAddress;
  @FXML private TextField tfState;
  @FXML private TextField tfzipcode;
  @FXML private TextField tfCreditCardNumber;
  @FXML private TextField tfCvv;

  @FXML private PasswordField tfConfirmPassword;

  ObservableList<String> expireMonthList =
      FXCollections.observableArrayList(
          "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
  ObservableList<String> expireYearList =
      FXCollections.observableArrayList(
          "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
          "2030");


  public void initialize() {
    expireMonth.setItems(expireMonthList);
    expireYear.setItems(expireYearList);

    lblCreateIndicate.setVisible(false);

    File RoomA = new File("src/Resort/RoomFinderScene/pineapple.png");
    Image pineapple = new Image(RoomA.toURI().toString());
    homeLogo.setImage(pineapple);
    homeLogo.setFitWidth(65);
    homeLogo.setFitHeight(100);
  }

  @FXML void btnClickHome(MouseEvent event) throws IOException {
    // get a reference to the window we are in
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

  @FXML void btnClickCreateAccount(MouseEvent event) {
    // todo add code to validate values and give appropriate error messages for incorrect values

    DatabaseAgent.addUser(
        tfUsername.getText(),
        tfFirstName.getText(),
        tfLastName.getText(),
        tfPassword.getText(),
        tfEmail.getText(),
        tfAddress.getText(),
        tfState.getText(),
        tfzipcode.getText(),
        tfCreditCardNumber.getText(),
        tfCvv.getText());

    // displays "Account Successful" label to blink three times when button clicked
    lblCreateIndicate.setVisible(true);
    Timeline timeline =
        new Timeline(
            new KeyFrame(Duration.seconds(0.8), evt -> lblCreateIndicate.setVisible(false)),
            new KeyFrame(Duration.seconds(0.4), evt -> lblCreateIndicate.setVisible(true)));
    timeline.setCycleCount(3);
    timeline.play();
    lblCreateIndicate.setVisible(false);
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
