package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakepolatty.highschoolsciencebowlpractice.R;

public class QuizModePage extends AppCompatActivity {
    // Question text fields
    private TextView roundSetNumLabel;
    private TextView questionNumLabel;
    private TextView categoryTypeLabel;
    private TextView questionTextLabel;

    // Option buttons
    private Button optionWButton;
    private Button optionXButton;
    private Button optionYButton;
    private Button optionZButton;

    // Timer Label
    private TextView timerLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mode_page);

        roundSetNumLabel = (TextView) findViewById(R.id.roundSetNumLabel);
        questionNumLabel = (TextView) findViewById(R.id.questionNumLabel);
        categoryTypeLabel = (TextView) findViewById(R.id.categoryTypeLabel);
        questionTextLabel = (TextView) findViewById(R.id.questionTextLabel);
        timerLabel = (TextView) findViewById(R.id.timerLabel);

        optionWButton = (Button) findViewById(R.id.optionWButton);
        optionXButton = (Button) findViewById(R.id.optionXButton);
        optionYButton = (Button) findViewById(R.id.optionYButton);
        optionZButton = (Button) findViewById(R.id.optionZButton);
    }

    public void selectOptionW(View view) {

    }

    public void selectOptionX(View view) {

    }

    public void selectOptionY(View view) {

    }

    public void selectOptionZ(View view) {

    }
}
