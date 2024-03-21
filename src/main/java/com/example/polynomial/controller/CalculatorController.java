package com.example.polynomial.controller;

import com.example.polynomial.model.Polynomial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
   @FXML
    TextField firstField;
    @FXML
    TextField secondField;
    @FXML
    TextField resultField;
    @FXML
    ChoiceBox<String> choice;
    private final String[] operations = {"add", "subtract", "multiply", "divide", "derivate", "integrate"};
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    choice.getItems().addAll(operations);
    choice.setOnAction(this::calculate);
    }
    @FXML
    public void calculate(ActionEvent event) {
        Polynomial first = new Polynomial(firstField.getText());
        Polynomial second = new Polynomial(secondField.getText());
        String result;
        String select = choice.getValue();
        switch (select) {
            case "add" -> result = first.add(second).toString();
            case "subtract" -> result = first.subtract(second).toString();
            case "multiply" -> result = first.product(second).toString();
            case "divide" -> {
                try {
                    Polynomial[] divide = first.div(second);
                    result = "Quotient: " + divide[0].toString() + " Remainder: " + divide[1].toString();
                } catch (ArithmeticException e) {
                    result = "Division is not possible";
                }
            }
            case "derivate" -> result = first.derivate().toString();
            case "integrate" -> result = first.integrate().toString();
            default -> result = "";
        }
        resultField.setText(result);
    }

}