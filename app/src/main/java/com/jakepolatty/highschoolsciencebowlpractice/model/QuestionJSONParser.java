package com.jakepolatty.highschoolsciencebowlpractice.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionJSONParser {
    // Singleton definition
    private static QuestionJSONParser ourInstance;

    public static QuestionJSONParser getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new QuestionJSONParser(context);
        }

        return ourInstance;
    }

    public static QuestionJSONParser getInstance() {
        return ourInstance;
    }

    private Question[] parsedQuestions;

    // Initialization from json

    private QuestionJSONParser(Context context) {
        JSONArray jsonArray = new JSONArray();
        try {
            InputStream stream = context.getAssets().open("questions.json");
            Scanner s = new Scanner(stream).useDelimiter("\\A");
            String jsonString = s.hasNext() ? s.next() : "";
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException | IOException e) {
            // File will always exist, so this case should never be reached
            e.printStackTrace();
        }

        ArrayList<Question> tempQuestionList = new ArrayList<Question>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                tempQuestionList.add(new Question(jsonObject));
            } catch (JSONException e) {
                // If something is wrong with the initialization, the element won't be added
                continue;
            }
        }

        this.parsedQuestions = tempQuestionList.toArray(new Question[0]);
    }

    // Random question access methods

    private Question parseQuestionForIndex(int i) {
        return parsedQuestions[i];
    }

    public Question getRandomQuestion() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(parsedQuestions.length);
        return parseQuestionForIndex(randomIndex);
    }
}
