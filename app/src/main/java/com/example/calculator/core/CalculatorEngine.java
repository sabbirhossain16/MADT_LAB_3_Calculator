package com.example.calculator.core;

public class CalculatorEngine {

    private double result = 0;
    private String lastOp = "=";
    private boolean clearNext = false;

    public String process(String input, String op){
        double value = Double.parseDouble(input);

        switch (lastOp){
            case "+": result += value; break;
            case "-": result -= value; break;
            case "×": result *= value; break;
            case "÷":
                if(value != 0) result /= value;
                break;
            case "=": result = value; break;
        }

        lastOp = op;
        clearNext = true;
        return String.valueOf(result);
    }

    public String sqrt(String input){
        double value = Double.parseDouble(input);
        result = Math.sqrt(value);
        clearNext = true;
        lastOp = "=";
        return String.valueOf(result);
    }

    public String plusMinus(String input){
        double value = Double.parseDouble(input);
        return String.valueOf(-value);
    }

    public void reset(){
        result = 0;
        lastOp = "=";
        clearNext = false;
    }

    public boolean shouldClearNext(){
        return clearNext;
    }

    public void clearNextDone(){
        clearNext = false;
    }
}
