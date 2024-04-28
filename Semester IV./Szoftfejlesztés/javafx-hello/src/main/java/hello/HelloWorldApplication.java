package hello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorldApplication extends Application {

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        root.setBackground(Background.fill(Color.SALMON));
        Text text = new Text("Hehe \uE6AF");
        text.setFont(Font.font("Hack Nerd Font", FontWeight.BOLD, 50));
        text.setFill(Color.GOLD);
        root.getChildren().add(text);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Hello, World!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
