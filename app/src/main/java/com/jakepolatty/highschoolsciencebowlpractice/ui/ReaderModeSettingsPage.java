package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.jakepolatty.highschoolsciencebowlpractice.R;

public class ReaderModeSettingsPage extends AppCompatActivity {
    private static final String[] setOptions = {"Set 1", "Set 2", "Set 3", "Set 4", "Set 5", "Set 6", "Set 7", "Set 8"};
    private static final String[] roundOptions = {"Round 1", "Round 2", "Round 3", "Round 4", "Round 5", "Round 6", "Round 7", "Round 8", "Round 9", "Round 10", "Round 11", "Round 12", "Round 13", "Round 14", "Round 15", "Round 16", "Round 17"};

    // Set and round pickers
    private NumberPicker setNumPicker;
    private NumberPicker roundNumPicker;

    // Time selectors
    private Spinner tossupTimeSpinner;
    private Spinner bonusTimeSpinner;

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

        menuButton = (Button) findViewById(R.id.menuButton);
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(ReaderModeSettingsPage.this, HomePage.class);
        startActivity(intent);
    }

    public void startReaderMode(View view) {
        Intent intent = new Intent(ReaderModeSettingsPage.this, ReaderModePage.class);

        int setNum = setNumPicker.getValue() + 1;
        intent.putExtra("SET", setNum);
        int roundNum = roundNumPicker.getValue() + 1;
        intent.putExtra("ROUND", roundNum);

        int tossupTime = getTossupTimeSelected();
        intent.putExtra("TOSSUP_TIME", tossupTime);
        int bonusTime = getBonusTimeSelected();
        intent.putExtra("BONUS_TIME", bonusTime);

        startActivity(intent);
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
