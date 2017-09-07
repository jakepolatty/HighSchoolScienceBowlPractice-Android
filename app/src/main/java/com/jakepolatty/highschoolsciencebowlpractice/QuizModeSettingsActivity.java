package com.jakepolatty.highschoolsciencebowlpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class QuizModeSettingsActivity extends AppCompatActivity {
    private String selectedOption = "Random";

    // Topic option toggle buttons
    private ToggleButton biologyButton;
    private ToggleButton chemistryButton;
    private ToggleButton earthAndSpaceButton;
    private ToggleButton energyButton;
    private ToggleButton mathButton;
    private ToggleButton physicsButton;
    private ToggleButton randomButton;

    private Spinner tossupTimeSpinner;
    private Spinner bonusTimeSpinner;

    private Button startSetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mode_settings);

        biologyButton = (ToggleButton) findViewById(R.id.biologyButton);
        chemistryButton = (ToggleButton) findViewById(R.id.chemistryButton);
        earthAndSpaceButton = (ToggleButton) findViewById(R.id.earthAndSpaceButton);
        energyButton = (ToggleButton) findViewById(R.id.energyButton);
        mathButton = (ToggleButton) findViewById(R.id.mathButton);
        physicsButton = (ToggleButton) findViewById(R.id.physicsButton);
        randomButton = (ToggleButton) findViewById(R.id.randomButton);

        tossupTimeSpinner = (Spinner) findViewById(R.id.tossupTimeSelector);
        bonusTimeSpinner = (Spinner) findViewById(R.id.bonusTimeSelector);
    }

    public void startQuizMode(View view) {
        System.out.println(selectedOption);
    }

    private void toggleOff() {
        biologyButton.setChecked(false);
        biologyButton.setSelected(false);
        chemistryButton.setChecked(false);
        chemistryButton.setSelected(false);
        earthAndSpaceButton.setChecked(false);
        earthAndSpaceButton.setSelected(false);
        energyButton.setChecked(false);
        energyButton.setSelected(false);
        mathButton.setChecked(false);
        mathButton.setSelected(false);
        physicsButton.setChecked(false);
        physicsButton.setSelected(false);
        randomButton.setChecked(false);
        randomButton.setSelected(false);
    }

    public void selectBiology(View view) {
        toggleOff();
        selectedOption = "Biology";
        biologyButton.setChecked(true);
        biologyButton.setSelected(true);
    }

    public void selectChemistry(View view) {
        toggleOff();
        selectedOption = "Chemistry";
        chemistryButton.setChecked(true);
        chemistryButton.setSelected(true);
    }

    public void selectEarthAndSpace(View view) {
        toggleOff();
        selectedOption = "Earth and Space";
        earthAndSpaceButton.setChecked(true);
        earthAndSpaceButton.setSelected(true);
    }

    public void selectEnergy(View view) {
        toggleOff();
        selectedOption = "Energy";
        energyButton.setChecked(true);
        energyButton.setSelected(true);
    }

    public void selectMath(View view) {
        toggleOff();
        selectedOption = "Math";
        mathButton.setChecked(true);
        mathButton.setSelected(true);
    }

    public void selectPhysics(View view) {
        toggleOff();
        selectedOption = "Physics";
        physicsButton.setChecked(true);
        physicsButton.setSelected(true);
    }

    public void selectRandom(View view) {
        toggleOff();
        selectedOption = "Random";
        randomButton.setChecked(true);
        randomButton.setSelected(true);
    }
}
