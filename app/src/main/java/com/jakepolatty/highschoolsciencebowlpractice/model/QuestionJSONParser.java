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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    private Question[] currentReaderSet;

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

    public Question getQuestionForCategory(Category category) {
        while (true) {
            Question question = getRandomQuestion();
            if (question.getCategory() == category) {
                return question;
            }
        }
        // Will never time out because of limited enum values
    }

    public Question getQuestionForCategoryAndRound(Category category, int round) {
        while (true) {
            Question question = getRandomQuestion();
            if (question.getCategory() == category && question.getRoundNumber() == round) {
                return question;
            }
        }
        // Will never time out because of limited category and round selections
    }

    public Question getQuestionForRound(int round) {
        while (true) {
            Question question = getRandomQuestion();
            if (question.getRoundNumber() == round) {
                return question;
            }
        }
        // Will never time out because of limited round selections
    }

    public Question getQuestionForSetAndRound(int set, int round) {
        while (true) {
            Question question = getRandomQuestion();
            if (question.getSetNumber() == set && question.getRoundNumber() == round) {
                return question;
            }
        }
        // Will never time out because of limited set and round selections
    }

    public Question getMCQuestion() {
        while (true) {
            Question question = getRandomQuestion();
            if (question.getAnswerType() == AnswerType.MultipleChoice) {
                return question;
            }
        }
    }

    public Question getMCQuestionForCategory(Category category) {
        while (true) {
            Question question = getMCQuestion();
            if (question.getCategory() == category) {
                return question;
            }
        }
        // Will never time out because of limited category selections
    }

    // Full question set methods

    public void saveQuestionSetForSetAndRound(int set, int round) {
        ArrayList<Question> tempList = new ArrayList<Question>();
        for (Question question : parsedQuestions) {
            if (question.getSetNumber() == set && question.getRoundNumber() == round) {
                tempList.add(question);
            }
        }
        Question[] qArray = tempList.toArray(new Question[0]);
        Arrays.sort(qArray, new Comparator<Question>() {
            @Override
            public int compare(Question q1, Question q2) {
                if (q1.getQuestionNumber() < q2.getQuestionNumber()) {
                    return -1;
                } else if (q1.getQuestionNumber() == q2.getQuestionNumber()) {
                    if (q1.getQuestionType() == QuestionType.Tossup) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        });
        currentReaderSet = qArray;
    }

    public Question getCurrentReaderQuestion(int index) {
        return currentReaderSet[index];
    }

    public int getCurrentReaderSetLength() {
        return currentReaderSet.length;
    }
}
