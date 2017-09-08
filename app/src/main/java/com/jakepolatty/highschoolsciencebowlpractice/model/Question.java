package com.jakepolatty.highschoolsciencebowlpractice.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

enum QuestionJSONKeys {
    QuestionText("qTxt"),
    QuestionAnswer("qAns"),
    AnswerChoices("ansCh"),
    Category("cat"),
    SetNumber("sNum"),
    RoundNumber("rNum"),
    QuestionNumber("qNum"),
    AnswerType("mc"),
    QuestionType("tb");

    private final String keyString;

    QuestionJSONKeys(String s) {
        keyString = s;
    }

    public String key() {
        return this.keyString;
    }
}

public class Question {
    private String questionText;
    private Category category;
    private QuestionType questionType;
    private AnswerType answerType;
    private int setNumber;
    private int roundNumber;
    private int questionNumber;
    private String[] answerChoices;
    private String answer;

    Question(JSONObject json) throws JSONException {
        String qText = json.getString(QuestionJSONKeys.QuestionText.key());
        String qAnswer = json.getString(QuestionJSONKeys.QuestionAnswer.key());
        String catString = json.getString(QuestionJSONKeys.Category.key());
        int setNumber = json.getInt(QuestionJSONKeys.SetNumber.key());
        int roundNumber = json.getInt(QuestionJSONKeys.RoundNumber.key());
        int questionNumber = json.getInt(QuestionJSONKeys.QuestionNumber.key());
        String answerType = json.getString(QuestionJSONKeys.AnswerType.key());
        String questionType = json.getString(QuestionJSONKeys.QuestionType.key());

        this.questionText = qText;
        this.answer = qAnswer;
        this.setNumber = setNumber;
        this.roundNumber = roundNumber;
        this.questionNumber = questionNumber;
        this.category = getCategoryForString(catString);
        this.answerType = getATypeForString(answerType);
        this.questionType = getQTypeForString(questionType);

        if (this.answerType == AnswerType.MultipleChoice) {
            JSONArray ansChoicesArray = json.getJSONArray(QuestionJSONKeys.AnswerChoices.key());
            String[] ansChoices = new String[4];
            ansChoices[0] = ansChoicesArray.getString(0);
            ansChoices[1] = ansChoicesArray.getString(1);
            ansChoices[2] = ansChoicesArray.getString(2);
            ansChoices[3] = ansChoicesArray.getString(3);
            this.answerChoices = ansChoices;
        } else {
            this.answerChoices = null;
        }
    }

    private Category getCategoryForString(String s) {
        switch (s) {
            case "BIO": return Category.Biology;
            case "CHEM": return Category.Chemistry;
            case "EAS": return Category.EarthAndSpace;
            case "ENG": return Category.Energy;
            case "MATH": return Category.Mathematics;
            case "PHY": return Category.Physics;
            case "GS": return Category.GeneralScience;
            case "CS": return Category.ComputerScience;
            default: return Category.GeneralScience;
        }
    }

    private QuestionType getQTypeForString(String s) {
        switch (s) {
            case "T": return QuestionType.Tossup;
            case "B": return QuestionType.Bonus;
            default: return QuestionType.Tossup;
        }
    }

    private AnswerType getATypeForString(String s) {
        switch (s) {
            case "MC": return AnswerType.MultipleChoice;
            case "SA": return AnswerType.ShortAnswer;
            default: return AnswerType.ShortAnswer;
        }
    }

    public String getQuestionText() {
        return questionText;
    }

    public Category getCategory() {
        return category;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public String getAnswer() {
        return answer;
    }
}
