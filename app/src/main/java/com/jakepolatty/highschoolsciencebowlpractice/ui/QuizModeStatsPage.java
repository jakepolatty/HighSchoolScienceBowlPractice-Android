package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.jakepolatty.highschoolsciencebowlpractice.R;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuizModeStats;
import com.jakepolatty.highschoolsciencebowlpractice.model.QuizStatsFormatter;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

public class QuizModeStatsPage extends AppCompatActivity {
    private TextView categoryLabel;
    private TextView noResultsLabel;
    private PieChart pieChart;

    private Button menuButton;

    private QuizModeStats stats;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_mode_stats_page);

        Intent intent = getIntent();

        stats = intent.getParcelableExtra("STATS");
        category = intent.getStringExtra("CATEGORY");

        categoryLabel = (TextView) findViewById(R.id.categoryLabel);
        if (category.equals("Random")) {
            categoryLabel.setText("Category: All");
        } else {
            categoryLabel.setText("Category: " + category);
        }

        menuButton = (Button) findViewById(R.id.menuButton);

        noResultsLabel = (TextView) findViewById(R.id.noResultsLabel);

        pieChart = (PieChart) findViewById(R.id.pieChart);
        if (stats.getNumberCorrect() == 0 && stats.getNumberIncorrect() == 0 && stats.getNumberNotAnswered() == 0) {
            System.out.println("Test");
            noResultsLabel.setVisibility(View.VISIBLE);
        } else {
            Description desc = new Description();
            desc.setText("");
            pieChart.setDescription(desc);
            pieChart.setHoleColor(Color.TRANSPARENT);
            pieChart.setTransparentCircleRadius(0);
            pieChart.getLegend().setEnabled(false);

            List<PieEntry> entries = new ArrayList<PieEntry>();
            List<Integer> colors = new ArrayList<Integer>();

            if (stats.getNumberCorrect() > 0) {
                entries.add(new PieEntry(stats.getNumberCorrect(), "Correct"));
                colors.add(getResources().getColor(R.color.quizStatsCorrect));
            }
            if (stats.getNumberIncorrect() > 0) {
                entries.add(new PieEntry(stats.getNumberIncorrect(), "Incorrect"));
                colors.add(getResources().getColor(R.color.quizStatsIncorrect));
            }
            if (stats.getNumberNotAnswered() > 0) {
                entries.add(new PieEntry(stats.getNumberNotAnswered(), "Not Answered"));
                colors.add(getResources().getColor(R.color.quizStatsNotAnswered));
            }
            PieDataSet dataSet = new PieDataSet(entries, "");
            dataSet.setColors(colors);
            PieData pieData = new PieData(dataSet);
            pieData.setValueTextColor(Color.WHITE);
            pieData.setValueTextSize(16.0f);
            pieData.setValueFormatter(new QuizStatsFormatter());
            pieChart.setData(pieData);
            pieChart.setVisibility(View.VISIBLE);
            pieChart.invalidate();
        }
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(QuizModeStatsPage.this, HomePage.class);
        startActivity(intent);
    }
}
