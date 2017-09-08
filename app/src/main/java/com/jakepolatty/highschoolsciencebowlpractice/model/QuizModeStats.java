package com.jakepolatty.highschoolsciencebowlpractice.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jakepolatty on 9/8/17.
 */

public class QuizModeStats implements Parcelable {
    private int numberCorrect;
    private int numberIncorrect;
    private int numberNotAnswered;

    public QuizModeStats() {
        numberCorrect = 0;
        numberIncorrect = 0;
        numberNotAnswered = 0;
    }

    protected QuizModeStats(Parcel in) {
        numberCorrect = in.readInt();
        numberIncorrect = in.readInt();
        numberNotAnswered = in.readInt();
    }

    public static final Creator<QuizModeStats> CREATOR = new Creator<QuizModeStats>() {
        @Override
        public QuizModeStats createFromParcel(Parcel in) {
            return new QuizModeStats(in);
        }

        @Override
        public QuizModeStats[] newArray(int size) {
            return new QuizModeStats[size];
        }
    };

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public int getNumberNotAnswered() {
        return numberNotAnswered;
    }

    public void addCorrect() {
        numberCorrect++;
    }

    public void addIncorrect() {
        numberIncorrect++;
    }

    public void addNotAnswered() {
        numberNotAnswered++;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberCorrect);
        dest.writeInt(numberIncorrect);
        dest.writeInt(numberNotAnswered);
    }
}
