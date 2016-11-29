package com.application.anthony.mvptest;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

/**
 * Created by anthony on 2016. 11. 28..
 */

public class Calculation {
    private final Symbols symbols;
    private CalculationResult calculationResult;
    private static String currentEXpression;

    interface CalculationResult {
        void onExpressionChanged(String result, boolean successful);
    }

    public void setCalculationResultListener(CalculationResult calculationResult){
        this.calculationResult = calculationResult;
        currentEXpression = "";
    }

    public Calculation(){
        symbols = new Symbols();
    }


    /*
    Delete a single character from currentExpression, unless empty
    "" - invalid
    543 - valid
    45*65 -valid
     */

    public void deleteCharacter(){
        if (currentEXpression.length() > 0){
            currentEXpression = currentEXpression.substring(0, currentEXpression.length() - 1);
            calculationResult.onExpressionChanged(currentEXpression, true);
        }else{
            calculationResult.onExpressionChanged("invalid Input", false);
        }
    }

    /*
    Delete entire expression unless empty
     */

    public void deleteExpression(){
        if (currentEXpression.equals("")){
            calculationResult.onExpressionChanged("Invalid Input", false);
        }

        currentEXpression = "";
        calculationResult.onExpressionChanged(currentEXpression, true);

    }

    /*
    Append number to currentExpression if valid:
    "0"
    @param number
     */
    public void appendNumber(String number){
        if(currentEXpression.startsWith("0") && number.equals("0")){
            calculationResult.onExpressionChanged("Invalid Input", false);
        }else{
            if (currentEXpression.length() <= 16){
                currentEXpression += number;
                calculationResult.onExpressionChanged(currentEXpression, true);
            }else {
                calculationResult.onExpressionChanged("Expression Too Long", false);
            }
        }
    }

    /*
    Append an Operator to currentExpression, if valid:
    65 - valid
    56* - invalid
    12+34 - valid
    "" - invalid
     */
    public void appendOperator(String operator){
        if (validateExpression(currentEXpression)){
            currentEXpression += operator;
            calculationResult.onExpressionChanged(currentEXpression, true);
        }
    }

    public void appendDecimal(){
        if (validateExpression(currentEXpression)){
            currentEXpression += ".";
            calculationResult.onExpressionChanged(currentEXpression, true);
        }
    }

    public void performEvaluation(){
        if (validateExpression(currentEXpression)){
            try {
                Double result = symbols.eval(currentEXpression);
                currentEXpression = Double.toString(result);
                calculationResult.onExpressionChanged(currentEXpression, true);
            } catch (SyntaxException e){
                calculationResult.onExpressionChanged("Invalid Input", false);
                e.printStackTrace();
            }
        }
    }

    public Boolean validateExpression(String expression){
        if (expression.endsWith("*") || expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("/")){
            calculationResult.onExpressionChanged("Invalid Input", false);
            return false;
        }
        else if (expression.equals("")){
            calculationResult.onExpressionChanged("Empty Expression", false);
            return false;
        }
        else if (expression.length() > 16){
            calculationResult.onExpressionChanged("Expression Too Long", false);
            return false;
        }else{
            return true;
        }
    }
}
