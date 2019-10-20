package Resort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Dont forget to add your H2 driver to the dependencies.
/**
 * Main class that starts the FXML application.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Title.fxml"));
        primaryStage.setTitle("Resort Reservations");
        root.getStylesheets().add
            (Main.class.getResource("login.css").toExternalForm());
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

// yes


// comment
