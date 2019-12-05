package Resort;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that starts the FXML application.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("TitleScene/Title.fxml"));
        primaryStage.setTitle("Resort Reservations");
        root.getStylesheets().add
            (Main.class.getResource("resortTemplate.css").toExternalForm());
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    /**
     * This is the main entry point for the program.
     * @param args arguments passed in when called from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
