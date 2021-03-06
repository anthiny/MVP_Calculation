package com.application.anthony.mvptest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by anthony on 2016. 11. 28..
 */

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        DisplayFragment displayFragment = (DisplayFragment) getSupportFragmentManager().findFragmentById(R.id.frag_display);
        InputFragment inputFragment = (InputFragment) getSupportFragmentManager().findFragmentById(R.id.frag_input);

        CalculatorPresenter presenter = new CalculatorPresenter(displayFragment);
        displayFragment.setPresenter(presenter);
        inputFragment.setPresenter(presenter);
    }
}
