package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.BinaryCalculator;

public class BinaryCalculatorController {
    public TextField firstNum;
    public TextField secondNum;
    public Label result;

    private BinaryCalculator binaryCalculator;

    public void initalize(){
        binaryCalculator = new BinaryCalculator();
    }

    public void add(ActionEvent actionEvent) {
        result.setText(binaryCalculator.binaryAdd(firstNum.getText(),secondNum.getText()));
    }

    public void subtract(ActionEvent actionEvent) {
        result.setText(binaryCalculator.binarySub(firstNum.getText(),secondNum.getText()));
    }

    public void multiply(ActionEvent actionEvent) {
        result.setText(binaryCalculator.binaryMulti(firstNum.getText(),secondNum.getText()));
    }

    public void divide(ActionEvent actionEvent) {
        result.setText(binaryCalculator.binaryDivide(firstNum.getText(),secondNum.getText()));
    }
}
