package com.jakepolatty.highschoolsciencebowlpractice.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jakepolatty.highschoolsciencebowlpractice.R;

public class AboutPage extends AppCompatActivity {
    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        menuButton = (Button) findViewById(R.id.menuButton);
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor("#94cffe"));
        Intent intent = new Intent(AboutPage.this, HomePage.class);
        startActivity(intent);
    }
}
