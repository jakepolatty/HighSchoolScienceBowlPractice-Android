package com.jakepolatty.highschoolsciencebowlpractice.model;

/**
 * Created by jakepolatty on 9/8/17.
 */

public enum QuestionType {
    Tossup("Tossup"),
    Bonus("Bonus");

    private final String displayName;

    QuestionType(String s) {
        displayName = s;
    }

    public String toString() {
        return this.displayName;
    }
}
