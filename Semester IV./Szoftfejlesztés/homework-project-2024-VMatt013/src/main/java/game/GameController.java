package game;

import gameresult.OnePlayerGameResult;
import gameresult.manager.json.JsonOnePlayerGameResultManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GameModel;
import model.Map;
import model.Selector;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.tinylog.Logger;
import gameresult.manager.GameResultManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;


/**
 * The controller of the game
 */
public class GameController {

    /** game board for the game */
    @FXML
    private GridPane board;

    /** the move counter javafx text field */
    @FXML
    private TextField moveCounterField;

    /** the player name javafx text field */
    @FXML
    public TextField playerNameGame;

    /** the model of the game */
    private GameModel model = new GameModel();

    /** the selector of the game with the given model */
    private Selector selector = new Selector(model);

    /** the string property of the name of the player */
    private final StringProperty name = new SimpleStringProperty();

    /** registers the key event handler */
    private void registerKeyEventHandler() {
        Platform.runLater(() -> board.getScene().setOnKeyPressed(this::handleKeyPress));
    }

    /** handles the key events */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        var restartKeyCombination = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        var quitKeyCombination = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
        if (restartKeyCombination.match(keyEvent)) {
            Logger.info("Reset Key Pressed, resetting.");
            resetBoard();
        } else if (quitKeyCombination.match(keyEvent)) {
            Logger.info("Quit Key pressed, quitting.");
            Platform.exit();
        }
    }

    /** handles the mouse click events */
    @FXML
    private void handleMouseClick(MouseEvent event) {
        var field = (StackPane) event.getSource();
        Logger.info("Click on field ({},{})", GridPane.getRowIndex(field), GridPane.getColumnIndex(field));
        selector.select(field);
        if (selector.isReadyToMove()) {
            selector.makeMove();
        }
    }

    /** resets the board */
    private void resetBoard() {
        model = new GameModel();
        selector = new Selector(model);
        registerKeyEventHandler();
        selector.phaseProperty().addListener(this::showSelectionPhaseChange);
        model.gameOverProperty().addListener(this::handleGameOver);
        moveCounterField.textProperty().bind(model.numberOfMovesProperty());
        playerNameGame.textProperty().bind(name);

        fillBoard();
    }
    private void resetBoard(String[][] map ) {
        model = new GameModel(map);
        selector = new Selector(model);
        registerKeyEventHandler();
        selector.phaseProperty().addListener(this::showSelectionPhaseChange);
        model.gameOverProperty().addListener(this::handleGameOver);
        moveCounterField.textProperty().bind(model.numberOfMovesProperty());
        playerNameGame.textProperty().bind(name);

        fillBoard();
    }

    /** fills the board with the default values */
    private void fillBoard() {
        board.getChildren().clear();

        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var field = createField(i, j);
                board.add(field, j, i);
            }
        }
    }

    /** initializes the game */
    @FXML
    private void initialize() {
        resetBoard();
        Logger.info("Game initialized.");
    }

    /** shows the change of the selection phase */
    private void showSelectionPhaseChange(ObservableValue<? extends Selector.Phase> value, Selector.Phase oldPhase, Selector.Phase newPhase) {
        switch (newPhase) {
            case SELECT_FROM -> {}
            case SELECT_TO -> showSelection(selector.getFrom());
            case READY_TO_MOVE -> hideSelection(selector.getFrom());
        }
    }

    /** shows the selection of the field to move from */
    private void showSelection(StackPane field) {
        field.getChildren().getFirst().getStyleClass().add("selected");
    }

    /** hides the selection of the field to move from */
    private void hideSelection(StackPane field) {
        field.getChildren().getFirst().getStyleClass().remove("selected");
    }

    /** creates a field for the board
     * @param row the row of the field
     * @param col the column of the field
     * @return the field
     */
    private StackPane createField(int row, int col) {
        var field = new StackPane();
        field.setBorder(Border.stroke(Color.BLACK));
        field.setOnMouseClicked(this::handleMouseClick);

        var piece = new StackPane();
        piece.getStyleClass().add("field");
        piece.setShape(new Circle(1));
        piece.setMaxHeight(36);
        piece.setMaxWidth(36);
        piece.setBorder(Border.stroke(Color.BLACK));

        var text = new Label();
        text.setTextFill(Color.BLACK);

        if (model.fieldProperty(row, col).get() == GameModel.State.NONE){
            field.setVisible(false);
        }

        piece.visibleProperty().bind(
                new BooleanBinding() {
                    {
                        super.bind(model.fieldProperty(row, col));
                    }
                    @Override
                    protected boolean computeValue() {
                        return switch (model.fieldProperty(row, col).get()) {
                            case GameModel.State.EMPTY, GameModel.State.NONE -> false;
                            default  -> true;
                        };
                    }
                }
        );

        text.textProperty().bind(
                new StringBinding() {
                    {
                        super.bind(model.fieldProperty(row, col));
                    }
                    @Override
                    protected String computeValue() {
                        return switch (model.fieldProperty(row, col).get()){
                            case GameModel.State.NONE, GameModel.State.EMPTY -> "";
                            case GameModel.State.FULL -> model.valueBoard[row][col].get();
                        };
                    }
                });

        piece.getChildren().add(text);
        field.getChildren().add(piece);
        return field;
    }

    /** handles the game over event */
    @FXML
    private void handleGameOver(ObservableValue observableValue, boolean oldVal, boolean newVal){
        if(newVal) {
            Logger.info("Game solved in {} seconds!", ChronoUnit.SECONDS.between(model.startTime, LocalDateTime.now()));
            try {
                Path path = Path.of("gameresult.json");
                if (!Files.exists(path)){
                    Files.createFile(path);
                    Files.writeString(path, "[]");

                }
                GameResultManager<OnePlayerGameResult> manager = new JsonOnePlayerGameResultManager(Path.of("gameresult.json"));
                manager.add(createGameResult());
                Logger.info("Added game result to JSON file.");
            } catch (IOException e) {
                Logger.error("Failed to save game result: {}", e.getMessage());
            }
            Platform.runLater(this::showGameOverAlert);
        }
    }

    /** returns the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name.get();
    }

    /** sets the name of the player
     * @param name the name of the player
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /** creates a game result
     * @return the game result
     */
    private OnePlayerGameResult createGameResult(){
        return OnePlayerGameResult.builder()
                .playerName(getName())
                .solved(true)
                .numberOfMoves(model.getNumberOfMoves())
                .duration(Duration.ofSeconds(ChronoUnit.SECONDS.between(model.startTime, LocalDateTime.now())))
                .created(ZonedDateTime.now())
                .build();
    }

    /** Resets the game */
    @FXML
    private void resetButton(ActionEvent event) throws IOException {
        resetBoard();
    }
    @FXML
    private void switchToEasyMode(ActionEvent event) throws IOException {
        resetBoard(Map.Easy);
    }

    private void showGameOverAlert(){
        var alert = new Alert(Alert.AlertType.NONE);
        alert.setHeaderText("You won!");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType resultsButton = new ButtonType("Scoreboard");
        alert.getButtonTypes().setAll(exitButton, resultsButton);
        alert.showAndWait().ifPresent(button -> {
            if (button == exitButton){
                Logger.info("Exit button clicked, exiting game.");
                Platform.exit();
            }else if(button == resultsButton){
                try {
                    Logger.info("Scoreboard button clicked, switching to scoreboard scene.");
                    switchToScoreboard();
                }catch (IOException e){
                    Logger.error("Failed to switch to game results: {}", e.getMessage());
                }
            }
        });
    }

    private void switchToScoreboard() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scoreboard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ( moveCounterField.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Scoreboard");
        stage.show();
    }

    
    }