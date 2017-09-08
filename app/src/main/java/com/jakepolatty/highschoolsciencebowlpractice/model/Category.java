package com.jakepolatty.highschoolsciencebowlpractice.model;

/**
 * Created by jakepolatty on 9/7/17.
 */

public enum Category {
    Biology("Biology"),
    Chemistry("Chemistry"),
    EarthAndSpace("Earth and Space"),
    Energy("Energy"),
    Mathematics("Mathematics"),
    Physics("Physics"),
    GeneralScience("General Science"),
    ComputerScience("Computer Science");

    private final String displayName;

    Category(String s) {
        displayName = s;
    }

    public String toString() {
        return this.displayName;
    }
}
