package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.jakepolatty.highschoolsciencebowlpractice.R;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuizModeStats;

public class QuizModeSettingsPage extends AppCompatActivity {
    private String selectedCategory = "Random";

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

    private Button menuButton;

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

        menuButton = (Button) findViewById(R.id.menuButton);
    }

    public void startQuizMode(View view) {
        Intent intent = new Intent(QuizModeSettingsPage.this, QuizModePage.class);
        intent.putExtra("CATEGORY", selectedCategory);

        int tossupTime = getTossupTimeSelected();
        intent.putExtra("TOSSUP_TIME", tossupTime);
        int bonusTime = getBonusTimeSelected();
        intent.putExtra("BONUS_TIME", bonusTime);

        QuizModeStats stats = new QuizModeStats();
        intent.putExtra("STATS", stats);

        startActivity(intent);
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(QuizModeSettingsPage.this, HomePage.class);
        startActivity(intent);
    }

    private int getTossupTimeSelected() {
        String timeString = tossupTimeSpinner.getSelectedItem().toString();
        switch (timeString) {
            case "5 Seconds": return 5;
            case "10 Seconds": return 10;
            case "15 Seconds": return 15;
            case "20 Seconds": return 20;
            case "25 Seconds": return 25;
            case "30 Seconds": return 30;
            default: return 10;
        }
    }

    private int getBonusTimeSelected() {
        String timeString = bonusTimeSpinner.getSelectedItem().toString();
        switch (timeString) {
            case "5 Seconds": return 5;
            case "10 Seconds": return 10;
            case "15 Seconds": return 15;
            case "20 Seconds": return 20;
            case "25 Seconds": return 25;
            case "30 Seconds": return 30;
            case "35 Seconds": return 35;
            case "40 Seconds": return 40;
            default: return 10;
        }
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
        selectedCategory = "Biology";
        biologyButton.setChecked(true);
        biologyButton.setSelected(true);
    }

    public void selectChemistry(View view) {
        toggleOff();
        selectedCategory = "Chemistry";
        chemistryButton.setChecked(true);
        chemistryButton.setSelected(true);
    }

    public void selectEarthAndSpace(View view) {
        toggleOff();
        selectedCategory = "Earth and Space";
        earthAndSpaceButton.setChecked(true);
        earthAndSpaceButton.setSelected(true);
    }

    public void selectEnergy(View view) {
        toggleOff();
        selectedCategory = "Energy";
        energyButton.setChecked(true);
        energyButton.setSelected(true);
    }

    public void selectMath(View view) {
        toggleOff();
        selectedCategory = "Math";
        mathButton.setChecked(true);
        mathButton.setSelected(true);
    }

    public void selectPhysics(View view) {
        toggleOff();
        selectedCategory = "Physics";
        physicsButton.setChecked(true);
        physicsButton.setSelected(true);
    }

    public void selectRandom(View view) {
        toggleOff();
        selectedCategory = "Random";
        randomButton.setChecked(true);
        randomButton.setSelected(true);
    }
}
