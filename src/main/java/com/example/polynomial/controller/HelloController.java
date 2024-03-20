package com.example.polynomial.controller;

import com.example.polynomial.model.Polynomial;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class HelloController {
   @FXML
    TextField firstField;
    @FXML
    TextField secondField;
    @FXML
    TextField resultField;
    @FXML
    public void add() {
        Polynomial first = new Polynomial(firstField.getText());
        Polynomial second = new Polynomial(secondField.getText());
        Polynomial[] result = first.div(second);
        System.out.println(result[0].toString() + " " + result[1].toString());

        /*Monomial first = new Monomial(firstField.getText());
        Monomial second = new Monomial(secondField.getText());
        Monomial sum = first.add(second);
        Monomial dif = first.subtract(second);
        Monomial product = first.product(second);
        Monomial integral = first.integrate();
        System.out.println(integral.toString());
        System.out.println(sum.toString() + " " + dif.toString() + " " + product.toString());*/
    }
}