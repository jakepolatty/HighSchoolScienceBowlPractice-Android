package com.jakepolatty.highschoolsciencebowlpractice.model;

/**
 * Created by jakepolatty on 9/8/17.
 */

public enum AnswerType {
    MultipleChoice("Multiple Choice"),
    ShortAnswer("Short Answer");

    private final String displayName;

    AnswerType(String s) {
        displayName = s;
    }

    public String toString() {
        return this.displayName;
    }
}
