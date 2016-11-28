package com.application.anthony.mvptest;

/**
 * Created by anthony on 2016. 11. 28..
 */

public class CalculatorPresenter implements CalculatorContract.ForwardDisplayInteractionToPreseneter,
        CalculatorContract.ForwardInputInteractionToPresenteter, Calculation.CalculationResult{

    private CalculatorContract.PublishToView publishResult;
    private Calculation calc;
    //An object of DisplayFragment
    public CalculatorPresenter (CalculatorContract.PublishToView publishResult){
        this.publishResult = publishResult;
        calc = new Calculation();
        calc.setCaculationReusltListener(this);
    }

    @Override
    public void onDeleteShortClick() {
        calc.deleteCharacter();
    }

    @Override
    public void onDeleteLongClick() {
        calc.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calc.appendNumber(String.valueOf(number));
    }

    @Override
    public void onDecimalClick() {
        calc.appendDecimal();
    }

    @Override
    public void onEvaluateClick() {
        calc.performEvaluation();
    }

    @Override
    public void onOperatorClick(String operator) {
        calc.appendOperator(operator);
    }

    @Override
    public void onExpressionChanged(String result, boolean successful) {
        if (successful){
            publishResult.showResult(result);
        }else{
            publishResult.showToastMessage(result);
        }
    }
}

