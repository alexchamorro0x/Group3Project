package Resort.CreateAccountScene;

import Resort.Main;
import Resort.Utility.DatabaseAgent;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.corba.se.impl.encoding.CodeSetConversion;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CreateAccountController {

  @FXML private ComboBox expireMonth;
  @FXML private ComboBox expireYear;

  @FXML private ImageView homeLogo;

  @FXML private Label lblCreateIndicate;
  @FXML private Label lblInvalid;

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

    expireYear.getSelectionModel().selectFirst();
    expireMonth.getSelectionModel().selectFirst();

    lblInvalid.setVisible(false);
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
    // make the new scene we are going to
    Scene titleScene = new Scene(title);

    // initiate the scene change (no need to make changes to controller)
    window.setScene(titleScene);
  }

  @FXML
  public void btnClickCreateAccount(ActionEvent actionEvent) {
    /*
        The If statements below check if the user entered information into all text fields, if not then the text box
        will be colored red until clicked on to show that the information is incorrect
    */
    Boolean blankEntry = false;
    if (tfUsername.getText().length() == 0) {
      tfUsername.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }
    if (tfFirstName.getText().length() == 0) {
      tfFirstName.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }
    if (tfLastName.getText().length() == 0) {
      tfLastName.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }
    if (tfPassword.getText().length() == 0) {
      tfPassword.setStyle("-fx-background-color: orangered");
      tfPassword.setText("");
      blankEntry = true;
    }
    if (tfConfirmPassword.getText().length() == 0) {
      tfConfirmPassword.setStyle("-fx-background-color: orangered");
      tfConfirmPassword.setText("");
    }

    if (tfAddress.getText().length() == 0) {
      tfAddress.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }
    if (tfState.getText().length() == 0) {
      tfState.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }
    if (tfzipcode.getText().length() == 0) {
      tfzipcode.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }
    if (tfCreditCardNumber.getText().length() == 0) {
      tfCreditCardNumber.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }
    if (tfCvv.getText().length() == 0) {
      tfCvv.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }

    if (tfzipcode.getText().length() != 5 || tfzipcode.getText().matches("[0-9]+") == false) {
      tfzipcode.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }

    if (tfPassword.getText().equals(tfConfirmPassword.getText()) == true) {
    } else {
      tfPassword.setStyle("-fx-background-color: orangered");
      tfConfirmPassword.setStyle("-fx-background-color: orangered");
      tfPassword.setText("");
      tfConfirmPassword.setText("");
      blankEntry = true;
    }

    if (tfCvv.getText().length() == 3 && tfCvv.getText().matches("[0-9]+")) {
    } else {
      tfCvv.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }

    if (tfCreditCardNumber.getText().length() == 16
        && tfCreditCardNumber.getText().matches("[0-9]+")) {
    } else {
      tfCreditCardNumber.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }

    final String regex = "\\w+@\\w+\\.\\w+";
    String emailEntry = tfEmail.getText();
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(emailEntry);
    boolean EMAILVALID = matcher.find();

    if (EMAILVALID == false) {
      tfEmail.setStyle("-fx-background-color: orangered");
      blankEntry = true;
    }

    if (blankEntry == false) {
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
          tfCvv.getText(),
          expireMonth.getSelectionModel().getSelectedItem().toString(),
          expireYear.getSelectionModel().getSelectedItem().toString()
          );

      // displays "Account Successful" label to blink three times when button clicked
      lblCreateIndicate.setVisible(true);
      Timeline timeline =
          new Timeline(
              new KeyFrame(Duration.seconds(0.8), evt -> lblCreateIndicate.setVisible(false)),
              new KeyFrame(Duration.seconds(0.4), evt -> lblCreateIndicate.setVisible(true)));
      timeline.setCycleCount(3);
      timeline.play();
      lblCreateIndicate.setVisible(false);
    } else {
      // sout "please check RED Labels." -Label message
      lblInvalid.setVisible(true);
      fadeOut(lblInvalid);
    }
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

  public void tfFirstNameClicked(MouseEvent mouseEvent) {
    tfFirstName.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfFirstNameKey(KeyEvent keyEvent) {
    tfFirstName.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfLastNameClicked(MouseEvent mouseEvent) {
    tfLastName.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfLastNameKey(KeyEvent keyEvent) {
    tfLastName.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfUsernameClicked(MouseEvent mouseEvent) {
    tfUsername.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfUsernameKey(KeyEvent keyEvent) {
    tfUsername.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfEmailClicked(MouseEvent mouseEvent) {
    tfEmail.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfEmailKey(KeyEvent keyEvent) {
    tfEmail.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfPasswordClicked(MouseEvent mouseEvent) {
    tfPassword.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfPasswordKey(KeyEvent keyEvent) {
    tfPassword.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfConfirmPasswordClicked(MouseEvent mouseEvent) {
    tfConfirmPassword.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfConfirmPasswordKey(KeyEvent keyEvent) {
    tfConfirmPassword.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfAddressClicked(MouseEvent mouseEvent) {
    tfAddress.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfAddressKey(KeyEvent keyEvent) {
    tfAddress.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfStateClicked(MouseEvent mouseEvent) {
    tfState.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfStateKey(KeyEvent keyEvent) {
    tfState.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfzipcodeClicked(MouseEvent mouseEvent) {
    tfzipcode.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfzipcodeKey(KeyEvent keyEvent) {
    tfzipcode.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfCreditCardNumberClicked(MouseEvent mouseEvent) {
    tfCreditCardNumber.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfCreditCardNumberKey(KeyEvent keyEvent) {
    tfCreditCardNumber.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfCvvClicked(MouseEvent mouseEvent) {
    tfCvv.setStyle("-fx-background-color: whitesmoke");
  }

  public void tfCvvKey(KeyEvent keyEvent) {
    tfCvv.setStyle("-fx-background-color: whitesmoke");
  }

  private static void fadeOut(Object x) {
    // https://docs.oracle.com/javafx/2/api/javafx/animation/FadeTransition.html
    FadeTransition ft = new FadeTransition(Duration.millis(2200), (Node) x);
    ft.setToValue(0);
    ft.setFromValue(1);
    // ft.setCycleCount(4);
    // ft.setAutoReverse(true);
    ft.play();
  }

}
