package Resort;

import Resort.Utility.DatabaseAgent;
import Resort.Utility.DatabaseInitializer;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.ScriptUtils;
import org.h2.tools.RunScript;
import org.h2.util.ScriptReader;

// Dont forget to add your H2 driver to the dependencies.
/**
 * Main class that starts the FXML application.
 */
public class Main extends Application {

    private void initalizeTables() throws SQLException {
        Connection conn = DatabaseAgent.getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement(DatabaseInitializer.sqlInit);
        preparedStatement.execute();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            initalizeTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("TitleScene/Title.fxml"));
        primaryStage.setTitle("Resort Reservations");
        root.getStylesheets().add
            (Main.class.getResource("resortTemplate.css").toExternalForm());
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

// yes


// comment
