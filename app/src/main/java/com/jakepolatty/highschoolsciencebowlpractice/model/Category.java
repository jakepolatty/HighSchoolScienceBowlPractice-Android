package com.jakepolatty.highschoolsciencebowlpractice.model;

/**
 * Created by jakepolatty on 9/7/17.
 */

public enum Category {
    BIOLOGY("Biology"),
    CHEMISTRY("Chemistry"),
    EARTHANDSPACE("Earth and Space"),
    ENERGY("Energy"),
    MATHEMATICS("Mathematics"),
    PHYSICS("Physics"),
    GENERALSCIENCE("General Science"),
    COMPUTERSCIENCE("Computer Science");

    private final String displayName;

    Category(String s) {
        displayName = s;
    }

    public String toString() {
        return this.displayName;
    }
}
