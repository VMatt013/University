package scoreboard;

import gameresult.OnePlayerGameResult;
import gameresult.manager.json.JsonOnePlayerGameResultManager;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.tinylog.Logger;
import game.GameController;
import util.DurationUtil;
import util.DurationUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The controller of the scoreboard
 */
public class ScoreboardController {
   /** the maximum number of rows in the table view */
    private static final int MAX_NUMBER_OF_ROWES = 10;

    /** the table view of the scoreboard */
    @FXML
    private TableView<OnePlayerGameResult> tableView;

    /** the table column of the player name */
    @FXML
    private TableColumn<OnePlayerGameResult, String> playerName;

    /** the table column of the step count */
    @FXML
    private TableColumn<OnePlayerGameResult, Integer> stepCount;

    /** the table column of the duration */
    @FXML
    private TableColumn<OnePlayerGameResult, String> durationSec;

    /** the table column of the created time */
    @FXML
    private TableColumn<OnePlayerGameResult, String> createdTime;

    /** initializes the scoreboard controller */
    @FXML
    private void initialize() throws IOException {
        playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        stepCount.setCellValueFactory(new PropertyValueFactory<>("numberOfMoves"));

        durationSec.setCellValueFactory(
                data -> {
                    var duration = data.getValue().getDuration();
                    return new ReadOnlyStringWrapper(DurationUtil.formatDuration(duration));
                }
        );
        createdTime.setCellValueFactory(
                data -> {
                    var date = data.getValue().getCreated();
                    var formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
                    return new ReadOnlyStringWrapper(formatter.format(date));
                }
        );
        ObservableList<OnePlayerGameResult> observableList = FXCollections.observableArrayList();
        observableList.addAll(new JsonOnePlayerGameResultManager(Path.of("gameresult.json"))
                .getBestByNumberOfMoves(MAX_NUMBER_OF_ROWES));
        tableView.setItems(observableList);
        registerKeyEventHandler();
    }

    /** registers the key event handler */
    private void registerKeyEventHandler() {
        Platform.runLater(() -> tableView.getScene().setOnKeyPressed(this::handleKeyPress));
    }
    /** handles the key events */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        var restartKeyCombination = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        var quitKeyCombination = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
        if (restartKeyCombination.match(keyEvent)) {
            Logger.info("Reset Key Pressed, resetting.");
        } else if (quitKeyCombination.match(keyEvent)) {
            Logger.info("Quit Key pressed, quitting.");
            Platform.exit();
        }
    }

    /** switches to the menu javafx scene */
    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        Logger.info("Back to menu screen button pressed.");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Menu");
        stage.show();
    }
}