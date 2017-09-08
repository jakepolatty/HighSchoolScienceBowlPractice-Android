package com.jakepolatty.highschoolsciencebowlpractice.model;

/**
 * Created by jakepolatty on 9/8/17.
 */

public class QuestionJSONParser {
    // Singleton definition
    private static final QuestionJSONParser ourInstance = new QuestionJSONParser();

    public static QuestionJSONParser getInstance() {
        return ourInstance;
    }

    private Question[] parsedQuestions;

    private QuestionJSONParser() {
    }
}
