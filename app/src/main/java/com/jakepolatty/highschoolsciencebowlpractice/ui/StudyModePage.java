package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jakepolatty.highschoolsciencebowlpractice.R;
import com.jakepolatty.highschoolsciencebowlpractice.model.Question;

public class StudyModePage extends AppCompatActivity {
    // Question text fields
    private TextView roundSetNumLabel;
    private TextView questionNumLabel;
    private TextView categoryTypeLabel;
    private TextView questionTextLabel;
    private TextView answerOptionsLabel;
    private TextView answerLabel;

    // Toolbar Buttons
    private Button menuButton;
    private Button nextButton;

    // Intent fields
    private String category;
    private int round;

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_mode_page);
    }
}
