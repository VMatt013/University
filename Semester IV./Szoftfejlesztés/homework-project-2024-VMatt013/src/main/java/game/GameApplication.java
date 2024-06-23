package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main application class
 */
public class GameApplication extends Application {

    /**
     * Starts the application menu
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent game = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setTitle("JavaFX Board Game");
        Scene scene = new Scene(game);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}