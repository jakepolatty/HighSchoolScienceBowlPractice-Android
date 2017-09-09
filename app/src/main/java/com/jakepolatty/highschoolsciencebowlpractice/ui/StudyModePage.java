package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakepolatty.highschoolsciencebowlpractice.R;
import com.jakepolatty.highschoolsciencebowlpractice.model.AnswerType;
import com.jakepolatty.highschoolsciencebowlpractice.model.Category;
import com.jakepolatty.highschoolsciencebowlpractice.model.Question;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuestionJSONParser;

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

    private Button showAnswerButton;

    // Intent fields
    private String category;
    private int round;

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_mode_page);

        Intent intent = getIntent();
        category = intent.getStringExtra("CATEGORY");
        round = intent.getIntExtra("ROUND", 0);

        if (category == "Random") {
            if (round == 0) {
                question = QuestionJSONParser.getInstance().getRandomQuestion();
            } else {
                question = QuestionJSONParser.getInstance().getQuestionForRound(round);
            }
        } else {
            Category parsedCategory = getCategoryForString(category);
            if (round == 0) {
                question = QuestionJSONParser.getInstance().getQuestionForCategory(parsedCategory);
            } else {
                question = QuestionJSONParser.getInstance().getQuestionForCategoryAndRound(parsedCategory, round);
            }
        }

        roundSetNumLabel = (TextView) findViewById(R.id.roundSetNumLabel);
        roundSetNumLabel.setText("Question Set " + question.getSetNumber() + " Round " + question.getRoundNumber());

        questionNumLabel = (TextView) findViewById(R.id.questionNumLabel);
        questionNumLabel.setText("Question " + question.getQuestionNumber() + " " + question.getQuestionType().toString());

        categoryTypeLabel = (TextView) findViewById(R.id.categoryTypeLabel);
        categoryTypeLabel.setText(question.getCategory().toString() + " " + question.getAnswerType().toString());

        questionTextLabel = (TextView) findViewById(R.id.questionTextLabel);
        questionTextLabel.setText(question.getQuestionText());

        answerOptionsLabel = (TextView) findViewById(R.id.answerOptionsLabel);
        if (question.getAnswerType() == AnswerType.MultipleChoice && question.getAnswerChoices().length == 4) {
            System.out.println("Test");
            answerOptionsLabel.setText(
                    question.getAnswerChoices()[0]+"\n"+
                    question.getAnswerChoices()[1]+"\n"+
                    question.getAnswerChoices()[2]+"\n"+
                    question.getAnswerChoices()[3]+"\n"
            );
        }

        answerLabel = (TextView) findViewById(R.id.answerLabel);
        answerLabel.setText("Answer: " + question.getAnswer());

        menuButton = (Button) findViewById(R.id.menuButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(StudyModePage.this, HomePage.class);
        startActivity(intent);
    }

    public void loadNextQuestion(View view) {
        nextButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(StudyModePage.this, StudyModePage.class);

        intent.putExtra("CATEGORY", category);
        intent.putExtra("ROUND", round);

        startActivity(intent);
    }

    public void showAnswer(View view) {
        showAnswerButton.setVisibility(View.INVISIBLE);
        answerLabel.setVisibility(View.VISIBLE);
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
