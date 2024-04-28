package hello;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloWorldApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/HelloWorld.fxml"));
        stage.setTitle("Hello, World!");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
