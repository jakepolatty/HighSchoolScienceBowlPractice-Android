package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.jakepolatty.highschoolsciencebowlpractice.R;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuestionJSONParser;

public class ReaderModeSettingsPage extends AppCompatActivity {
    private static final String[] setOptions = {"Set 1", "Set 2", "Set 3", "Set 4", "Set 5", "Set 6", "Set 7", "Set 8", "Set 9"};
    private static final String[] roundOptions = {"Round 1", "Round 2", "Round 3", "Round 4", "Round 5", "Round 6", "Round 7", "Round 8", "Round 9", "Round 10", "Round 11", "Round 12", "Round 13", "Round 14", "Round 15", "Round 16", "Round 17"};

    // Set and round pickers
    private NumberPicker setNumPicker;
    private NumberPicker roundNumPicker;

    // Time selectors
    private Spinner tossupTimeSpinner;
    private Spinner bonusTimeSpinner;
    private ToggleButton roundTimerButton;

    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_mode_settings_page);

        setNumPicker = (NumberPicker) findViewById(R.id.setNumPicker);
        setNumPicker.setMinValue(0);
        setNumPicker.setMaxValue(setOptions.length-1);
        setNumPicker.setWrapSelectorWheel(false);
        setNumPicker.setDisplayedValues(setOptions);

        roundNumPicker = (NumberPicker) findViewById(R.id.roundNumPicker);
        roundNumPicker.setMinValue(0);
        roundNumPicker.setMaxValue(roundOptions.length-1);
        roundNumPicker.setWrapSelectorWheel(false);
        roundNumPicker.setDisplayedValues(roundOptions);

        tossupTimeSpinner = (Spinner) findViewById(R.id.tossupTimeSelector);
        bonusTimeSpinner = (Spinner) findViewById(R.id.bonusTimeSelector);

        roundTimerButton = (ToggleButton) findViewById(R.id.roundTimerButton);

        menuButton = (Button) findViewById(R.id.menuButton);
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(ReaderModeSettingsPage.this, HomePage.class);
        startActivity(intent);
    }

    public void startReaderMode(View view) {
        int setNum = setNumPicker.getValue() + 1;
        int roundNum = roundNumPicker.getValue() + 1;
        boolean isTimedRound = roundTimerButton.isChecked();
        if ((roundNum == 16 || roundNum == 17) && (setNum == 5 || setNum == 6)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ReaderModeSettingsPage.this, R.style.alertStyle);
            builder.setTitle("Invalid Set");
            builder.setMessage("The chosen question set is not available.");
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        } else {
            Intent intent = new Intent(ReaderModeSettingsPage.this, ReaderModePage.class);

            QuestionJSONParser.getInstance().saveQuestionSetForSetAndRound(setNum, roundNum);

            int tossupTime = getTossupTimeSelected();
            intent.putExtra("TOSSUP_TIME", tossupTime);
            int bonusTime = getBonusTimeSelected();
            intent.putExtra("BONUS_TIME", bonusTime);

            intent.putExtra("TIMED_ROUND", isTimedRound);
            if (isTimedRound) {
                intent.putExtra("TIME_REMAINING", 480000);
                intent.putExtra("HALF", 1);
                intent.putExtra("TIMER_RUNNING", false);
            }

            intent.putExtra("INDEX", 0);

            startActivity(intent);
        }
    }

    private int getTossupTimeSelected() {
        String timeString = tossupTimeSpinner.getSelectedItem().toString();
        switch (timeString) {
            case "5 Seconds": return 5;
            case "10 Seconds": return 10;
            case "15 Seconds": return 15;
            default: return 5;
        }
    }

    private int getBonusTimeSelected() {
        String timeString = bonusTimeSpinner.getSelectedItem().toString();
        switch (timeString) {
            case "20 Seconds": return 20;
            case "25 Seconds": return 25;
            case "30 Seconds": return 30;
            case "35 Seconds": return 35;
            case "40 Seconds": return 40;
            default: return 20;
        }
    }
}
