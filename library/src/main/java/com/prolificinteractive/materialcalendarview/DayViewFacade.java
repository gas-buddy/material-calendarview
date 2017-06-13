package com.prolificinteractive.materialcalendarview;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstraction layer to help in decorating Day views
 */
public class DayViewFacade {

    @ColorInt private int unselectedCircleColor;

    private boolean isDecorated;

    private final LinkedList<Span> spans = new LinkedList<>();
    private boolean daysDisabled = false;

    DayViewFacade() {
        isDecorated = false;
    }

    public void setUnselectedCircleColor(int unselectedCircleColor) {
        this.unselectedCircleColor = unselectedCircleColor;
        isDecorated = true;
    }

    /**
     * Add a span to the entire text of a day
     *
     * @param span text span instance
     */
    public void addSpan(@NonNull Object span) {
        if (spans != null) {
            this.spans.add(new Span(span));
            isDecorated = true;
        }
    }

    /**
     * <p>Set days to be in a disabled state, or re-enabled.</p>
     * <p>Note, passing true here will <b>not</b> override minimum and maximum dates, if set.
     * This will only re-enable disabled dates.</p>
     *
     * @param daysDisabled true to disable days, false to re-enable days
     */
    public void setDaysDisabled(boolean daysDisabled) {
        this.daysDisabled = daysDisabled;
        this.isDecorated = true;
    }

    void reset() {
        unselectedCircleColor = 0;
        spans.clear();
        isDecorated = false;
        daysDisabled = false;
    }

    /**
     * Apply things set this to other
     *
     * @param dayViewFacade facade to apply our data to
     */
    void applyTo(DayViewFacade dayViewFacade) {
        if (unselectedCircleColor != 0) {
            dayViewFacade.setUnselectedCircleColor(unselectedCircleColor);
        }

        dayViewFacade.spans.addAll(spans);
        dayViewFacade.isDecorated |= this.isDecorated;
        dayViewFacade.daysDisabled = daysDisabled;
    }

    boolean isDecorated() {
        return isDecorated;
    }

    List<Span> getSpans() {
        return Collections.unmodifiableList(spans);
    }

    /**
     * Are days from this facade disabled
     *
     * @return true if disabled, false if not re-enabled
     */
    public boolean areDaysDisabled() {
        return daysDisabled;
    }

    @ColorInt
    public int getUnselectedCircleColor() {
        return unselectedCircleColor;
    }

    static class Span {

        final Object span;

        public Span(Object span) {
            this.span = span;
        }
    }
}
