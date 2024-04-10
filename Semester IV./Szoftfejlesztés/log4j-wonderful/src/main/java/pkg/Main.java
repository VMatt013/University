package pkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import javafx.scene.layout.BorderPane;

import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * The location of the configuration file.
     */
    private static final String configLocation = org.apache.logging.log4j.core.LoggerContext.getContext().getConfiguration().getConfigurationSource().getLocation();

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();

        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(20);
        textArea.setEditable(true);
        textArea.setText(FileUtils.readFileToString(new File(configLocation)));
        root.setCenter(textArea);

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(event -> {
            logger.info("Writing configuration to {}", configLocation);
            try (FileWriter writer = new FileWriter(configLocation)) {
                writer.write(textArea.getText());
            } catch(IOException e) {
                logger.error("Failed to save configuration", e);
            }
        });
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(saveMenuItem);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(
            new TimerTask() {
                public void run() {
                    logger.fatal("This is a FATAL message");
                    logger.error("This is an ERROR message");
                    logger.warn("This is a WARN message");
                    logger.info("This is an INFO message");
                    logger.debug("This is a DEBUG message");
                    logger.trace("This is a TRACE message");
                }
            }, 0, 3000);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(configLocation);
        primaryStage.setOnCloseRequest(event -> { timer.cancel(); Platform.exit(); System.exit(0); });
        primaryStage.show();
        Platform.setImplicitExit(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
