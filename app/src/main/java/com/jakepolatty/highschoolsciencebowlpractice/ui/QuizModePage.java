package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakepolatty.highschoolsciencebowlpractice.R;
import com.jakepolatty.highschoolsciencebowlpractice.model.Category;
import com.jakepolatty.highschoolsciencebowlpractice.model.Question;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuestionJSONParser;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuestionType;

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

    // Toolbar Buttons
    private Button menuButton;
    private Button nextButton;

    // Timer Label
    private TextView timerLabel;

    // Intent fields
    private String category;
    private int tossupTime;
    private int bonusTime;

    private Question question;
    private int seconds = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mode_page);
        Intent intent = getIntent();
        category = intent.getStringExtra("CATEGORY");
        tossupTime = intent.getIntExtra("TOSSUP_TIME", 10);
        bonusTime = intent.getIntExtra("BONUS_TIME", 10);
        if (category.equals("Random")) {
            question = QuestionJSONParser.getInstance().getMCQuestion();
        } else {
            Category parsedCategory = getCategoryForString(category);
            question = QuestionJSONParser.getInstance().getMCQuestionForCategory(parsedCategory);
        }

        if (question.getQuestionType() == QuestionType.Tossup) {
            seconds = tossupTime;
        } else {
            seconds = bonusTime;
        }

        roundSetNumLabel = (TextView) findViewById(R.id.roundSetNumLabel);
        roundSetNumLabel.setText("Question Set " + question.getSetNumber() + " Round " + question.getRoundNumber());

        questionNumLabel = (TextView) findViewById(R.id.questionNumLabel);
        questionNumLabel.setText("Question " + question.getQuestionNumber() + " " + question.getQuestionType().toString());

        categoryTypeLabel = (TextView) findViewById(R.id.categoryTypeLabel);
        categoryTypeLabel.setText(question.getCategory().toString() + " " + question.getAnswerType().toString());

        questionTextLabel = (TextView) findViewById(R.id.questionTextLabel);
        questionTextLabel.setText(question.getQuestionText());

        timerLabel = (TextView) findViewById(R.id.timerLabel);
        timerLabel.setText(seconds + " Seconds Left");

        optionWButton = (Button) findViewById(R.id.optionWButton);
        optionWButton.setText(question.getAnswerChoices()[0]);

        optionXButton = (Button) findViewById(R.id.optionXButton);
        optionXButton.setText(question.getAnswerChoices()[1]);

        optionYButton = (Button) findViewById(R.id.optionYButton);
        optionYButton.setText(question.getAnswerChoices()[2]);

        optionZButton = (Button) findViewById(R.id.optionZButton);
        optionZButton.setText(question.getAnswerChoices()[3]);

        menuButton = (Button) findViewById(R.id.menuButton);
        nextButton = (Button) findViewById(R.id.nextButton);
    }

    public void selectOptionW(View view) {

    }

    public void selectOptionX(View view) {

    }

    public void selectOptionY(View view) {

    }

    public void selectOptionZ(View view) {

    }

    public void finishSet(View view) {
        // Temporary - replace with stats page
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(QuizModePage.this, HomePage.class);
        startActivity(intent);
    }

    public void loadNextQuestion(View view) {
        nextButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(QuizModePage.this, QuizModePage.class);

        intent.putExtra("CATEGORY", category);
        intent.putExtra("TOSSUP_TIME", tossupTime);
        intent.putExtra("BONUS_TIME", bonusTime);

        startActivity(intent);
    }

    private Category getCategoryForString(String s) {
        switch (s) {
            case "Biology": return Category.Biology;
            case "Chemistry": return Category.Chemistry;
            case "Earth and Space": return Category.EarthAndSpace;
            case "Energy": return Category.Energy;
            case "Math": return Category.Mathematics;
            case "Physics": return Category.Physics;
            default: return Category.GeneralScience;
        }
    }
}
