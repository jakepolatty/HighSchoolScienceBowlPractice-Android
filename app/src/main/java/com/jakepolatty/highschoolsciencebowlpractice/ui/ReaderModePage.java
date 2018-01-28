package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jakepolatty.highschoolsciencebowlpractice.R;
import com.jakepolatty.highschoolsciencebowlpractice.model.AnswerType;
import com.jakepolatty.highschoolsciencebowlpractice.model.Question;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuestionJSONParser;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuestionType;

public class ReaderModePage extends AppCompatActivity {
    // Question text fields
    private TextView roundSetNumLabel;
    private TextView questionNumLabel;
    private TextView categoryTypeLabel;
    private TextView questionTextLabel;
    private TextView answerOptionsLabel;
    private TextView answerLabel;
    private TextView timerLabel;

    // Toolbar Buttons
    private Button menuButton;
    private Button nextButton;

    private Button startTimerButton;

    // Round questionTimer components
    private LinearLayout timerBar;
    private TextView roundTimeLabel;
    private ToggleButton roundTimerStartToggle;

    // Intent fields
    private int questionIndex;
    private int tossupTime;
    private int bonusTime;
    private boolean isTimedRound;
    private long roundTimeRemaining;
    private int halfNum;
    private boolean isTimerRunning;

    private CountDownTimer questionTimer;
    private CountDownTimer roundTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_mode_page);

        Intent intent = getIntent();

        tossupTime = intent.getIntExtra("TOSSUP_TIME", 5);
        bonusTime = intent.getIntExtra("BONUS_TIME", 20);
        questionIndex = intent.getIntExtra("INDEX", 0);

        isTimedRound = intent.getBooleanExtra("TIMED_ROUND", false);
        if (isTimedRound) {
            roundTimeRemaining = intent.getLongExtra("TIME_REMAINING", 480000);
            halfNum = intent.getIntExtra("HALF", 1);
            isTimerRunning = intent.getBooleanExtra("TIMER_RUNNING", false);
        }

        Question question = QuestionJSONParser.getInstance().getCurrentReaderQuestion(questionIndex);

        int seconds;
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

        timerLabel = (TextView) findViewById(R.id.timerLabel);
        timerLabel.setText(seconds + " Seconds Left");

        menuButton = (Button) findViewById(R.id.menuButton);

        nextButton = (Button) findViewById(R.id.nextButton);
        if (questionIndex == QuestionJSONParser.getInstance().getCurrentReaderSetLength() - 1) {
            nextButton.setText("Finish Set");
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextButton.setTextColor(Color.parseColor("#94cffe"));
                    Intent intent = new Intent(ReaderModePage.this, ReaderModeSettingsPage.class);
                    startActivity(intent);
                }
            });
        }

        startTimerButton = (Button) findViewById(R.id.startTimerButton);

        timerBar = (LinearLayout) findViewById(R.id.timerBar);
        roundTimeLabel = (TextView) findViewById(R.id.roundTimeLabel);
        roundTimerStartToggle = (ToggleButton) findViewById(R.id.roundTimerStartToggle);

        if (!isTimedRound) {
            timerBar.setVisibility(View.INVISIBLE);
            timerBar.removeAllViews();
            timerBar.setMinimumHeight(0);
        }

        questionTimer = new CountDownTimer(seconds * 1000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerLabel.setText(Math.round(Math.ceil(millisUntilFinished/1000.0)) + " Seconds Left");
            }

            @Override
            public void onFinish() {
                timerLabel.setText("Time's Up");
            }
        };

        if (isTimedRound) {
            roundTimer = createRoundTimer();

            if (isTimerRunning) {
                roundTimerStartToggle.setChecked(true);
                roundTimer.start();
            }
        }
    }

    private CountDownTimer createRoundTimer() {
        return new CountDownTimer(roundTimeRemaining, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                roundTimeRemaining = millisUntilFinished;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int seconds = (int) ((millisUntilFinished / 1000) % 60);
                if (halfNum == 1) {
                    roundTimeLabel.setText(minutes + ":" + String.format("%2d", seconds) + " (Half 1)");
                } else {
                    roundTimeLabel.setText(minutes + ":" + String.format("%2d", seconds) + " (Half 2)");
                }
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                if (halfNum == 1) {
                    roundTimeLabel.setText("Halftime");
                } else {
                    roundTimeLabel.setText("Round Over");
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextButton.setTextColor(Color.parseColor("#ffffff"));
    }

    public void startTimer(View view) {
        startTimerButton.setVisibility(View.INVISIBLE);
        timerLabel.setVisibility(View.VISIBLE);
        questionTimer.start();
    }

    public void returnMainMenu(View view) {
        questionTimer.cancel();
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(ReaderModePage.this, HomePage.class);
        startActivity(intent);
    }

    public void loadNextQuestion(View view) {
        questionTimer.cancel();
        timerLabel.setVisibility(View.INVISIBLE);
        nextButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(ReaderModePage.this, ReaderModePage.class);

        intent.putExtra("TOSSUP_TIME", tossupTime);
        intent.putExtra("BONUS_TIME", bonusTime);
        intent.putExtra("INDEX", questionIndex+1);

        startActivity(intent);
    }

    public void toggleRoundTimer(View view) {
        if (roundTimerStartToggle.isChecked()) { // Running timer
            roundTimer.start();
            isTimerRunning = true;
        } else { // Paused
            roundTimer.cancel();
            roundTimer = createRoundTimer();
            roundTimerStartToggle.setTextOff("Resume");
            isTimerRunning = false;
        }
    }
}
