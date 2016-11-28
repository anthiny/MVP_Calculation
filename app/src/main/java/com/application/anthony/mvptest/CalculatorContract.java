package com.application.anthony.mvptest;

/**
 * Created by anthony on 2016. 11. 28..
 */

public interface CalculatorContract {

    //Our View handles these methods
    interface PublishToView{
        void showResult(String result);

        void showToastMessage(String message);
    }

    //passes click events from our View (DisplayFragment), to the presenter
    interface ForwardDisplayInteractionToPreseneter{
        void onDeleteShortClick();

        void onDeleteLongClick();
    }

    //passes click events from our View (InputFragment), to the presenter
    interface ForwardInputInteractionToPresenteter{
        void onNumberClick(int number);

        void onDecimalClick();

        void onEvaluateClick();

        void onOperatorClick(String operator);
    }
}
