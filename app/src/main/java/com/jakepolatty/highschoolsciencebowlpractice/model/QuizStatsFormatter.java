package com.jakepolatty.highschoolsciencebowlpractice.model;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by jakepolatty on 9/9/17.
 */

public class QuizStatsFormatter implements IValueFormatter {
    private DecimalFormat mFormat;

    public QuizStatsFormatter() {
        mFormat = new DecimalFormat("###,###,##0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value);
    }
}
