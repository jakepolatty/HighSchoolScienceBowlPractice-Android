package com.jakepolatty.highschoolsciencebowlpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button quizModeOptionButton;
    private Button readerModeOptionButton;
    private Button studyModeOptionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        quizModeOptionButton = (Button) findViewById(R.id.quizModeOptionButton);
        readerModeOptionButton = (Button) findViewById(R.id.readerModeOptionButton);
        studyModeOptionButton = (Button) findViewById(R.id.studyModeOptionButton);

//        View.OnClickListener quizModeListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Quiz Mode");
//            }
//        };
//        quizModeOptionButton.setOnClickListener(quizModeListener);
//
//        View.OnClickListener readerModeListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Reader Mode");
//            }
//        };
//        readerModeOptionButton.setOnClickListener(readerModeListener);
//
//        View.OnClickListener studyModeListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Study Mode");
//            }
//        };
//        studyModeOptionButton.setOnClickListener(studyModeListener);
    }

    public void startQuizMode(View view) {
        Intent intent = new Intent(HomePage.this, QuizModeSettingsActivity.class);
        startActivity(intent);
    }
}
