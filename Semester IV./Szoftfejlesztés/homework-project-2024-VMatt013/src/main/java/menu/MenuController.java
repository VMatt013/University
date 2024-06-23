package menu;

import game.GameController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;

/**
 * The controller of the menu
 */
public class MenuController {

    /** initializes the menu controller */
    @FXML
    private void initialize() {
        registerKeyEventHandler();
    }

    /** the player name javafx text field */
    @FXML
    public TextField playerNameFieldMenu;

    /** exits the game */
    @FXML
    private void exitGame(ActionEvent event) throws IOException {
        Platform.exit();
    };

    /** switches to the game javafx scene */
    @FXML
    private void switchToGame(Event event) throws IOException{
        Logger.info("Play button clicked and player name added: {}", playerNameFieldMenu.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/game.fxml"));
        Parent root = loader.load();
        GameController controller = loader.getController();
        controller.setName(playerNameFieldMenu.getText());
        Stage stage = (Stage) playerNameFieldMenu.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Game");
        stage.show();
    }
    /** registers the key event handler */
    private void registerKeyEventHandler() {
        Platform.runLater(() -> playerNameFieldMenu.getScene().setOnKeyPressed(this::handleKeyPress));
    }
    /** handles the key events */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        var quitKeyCombination = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
        var startKeyCombination = new KeyCodeCombination(KeyCode.ENTER);
    if (startKeyCombination.match(keyEvent)) {
        try {
            switchToGame(keyEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } else if (quitKeyCombination.match(keyEvent)) {
            Logger.info("Quit Key pressed, quitting.");
            Platform.exit();
        }
    }

    /** switches to the scoreboard javafx scene */
    @FXML
    private void switchToScoreboard(ActionEvent event) throws IOException{
        Logger.info("Scoreboard button clicked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scoreboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Scoreboard");
        stage.show();
    }
}
