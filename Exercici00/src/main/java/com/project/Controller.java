package com.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;

import javafx.event.ActionEvent;

public class Controller {

    private boolean first = true;
    private double num1;
    private double num2;
    private String operation;

    @FXML
    private TextField display;
    @FXML
    private TextField littleDisplay;

    @FXML
    private void addNum(ActionEvent event) {
        String screenText = display.getText();
        
        if((isDigit(screenText) && !screenText.equals("0")) || (screenText.equals("-") && !(littleDisplay.getText().charAt(littleDisplay.getText().length()-1)=='-')) || (screenText.equals("."))){
            display.setText(screenText+((Button) event.getSource()).getText());
        }else{
            display.setText(((Button) event.getSource()).getText());
        }
    }

    @FXML
    private void doOperation(ActionEvent event){
        if(first){
            num1 = Double.parseDouble(display.getText());
            operation = ((Button)event.getSource()).getText();
            littleDisplay.setText(littleDisplay.getText()+" "+display.getText()+" "+operation);
            
            display.setText(operation);
            first = false;
        }else{
            boolean done = calculateLocal();
            if(done){
                operation = ((Button)event.getSource()).getText();
                display.setText(operation);
            }else{
                display.setText(((Button)event.getSource()).getText());
            }
            if(num1%1==0){
                littleDisplay.setText((int)num1+" "+operation);
            }else{
                littleDisplay.setText(num1+" "+operation);
            }
        }
    }

    @FXML
    private void calculate(ActionEvent evt){
        littleDisplay.setText(littleDisplay.getText()+" "+display.getText()+" =");
        calculateLocal();
    }

    @FXML
    private void deleteAll(ActionEvent evt){
        num1 = 0;
        num2 = 0;
        operation = "";
        first = true;
        display.setText("0");
        littleDisplay.setText("");
    }

    @FXML
    private void deleteEntry(ActionEvent evt){
        display.setText("0");
    }

    private boolean calculateLocal(){
        try{
            num2 = Double.parseDouble(display.getText());
        }catch(NumberFormatException e){
            return false;
        }
        
        if(operation.equals("+")){
            num1 = num1 + num2;
        }else if(operation.equals("-")){
            num1 = num1 - num2;
        }else if(operation.equals("x")){
            num1 = num1 * num2;
        }else if(operation.equals("/")){
            num1 = num1 / num2;
        }
        if(num1%1==0){
            display.setText(Integer.toString((int)num1));
        }
        else{
            display.setText(Double.toString(num1));
        }
        first = true;
        return true;
    }

    private boolean isDigit(String txt){
        try{
            Double.parseDouble(txt);
        }catch(NumberFormatException e){
            return false;
        };
        return true;
    }
}
