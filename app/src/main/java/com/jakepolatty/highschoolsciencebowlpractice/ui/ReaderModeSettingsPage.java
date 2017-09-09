package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
