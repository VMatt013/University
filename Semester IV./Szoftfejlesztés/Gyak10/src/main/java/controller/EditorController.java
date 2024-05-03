package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import model.EditorModel;

import java.io.IOException;

public class EditorController {
 private final EditorModel editorModel = new EditorModel();
    //@F2
    public void initalize(){

    }
    public void onSave(){
        if (editorModel.getFilePath() != null){

        }
    }

    public void onNew(){

    }

    public void onOpen(){
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        var file = fileChooser.showOpenDialog(null);
        if (file != null){
            try{
                editorModel.open(file.getPath());
            }catch (IOException e){
                System.out.println("Failed to open file!");
            }
        }

    }

    private void performSaveAs(){
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        var file = fileChooser.showOpenDialog(null);
        if (file != null){
            try{
                editorModel.saveAs(file.getPath());
            }catch (IOException e){
                System.out.println("Failed to save file!");
            }
        }
    }

    public void onQuit(){
        Platform.exit();
    }

    public void onAbout(){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("JavaFX Text Editor");
        alert.setContentText("""
                Java version: %s, %s
                JavaFX version: %s
                """.formatted(System.getProperty("java.version"),System.getProperty("java.vendor"),System.getProperty("javafx.version")));
        alert.show();
    }
}
