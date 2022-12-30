package com.almaherplan.www.QTech.model;

import java.math.BigDecimal;

public class SuggestionStop {

    private Sorah currentStop = new Sorah();
    private int indexStop;

    private boolean ayahValueZero = false;

    private BigDecimal closetNumberToTarget = new BigDecimal(100);


    public SuggestionStop() {}

    public SuggestionStop(Sorah currentStop, int indexStop) {
        this.currentStop = currentStop;
        this.indexStop = indexStop;
    }
    public SuggestionStop(Sorah currentStop, int indexStop, boolean ayahValueZero) {
        this.currentStop = currentStop;
        this.indexStop = indexStop;
        this.ayahValueZero = ayahValueZero;
    }

    public SuggestionStop(Sorah currentStop, int indexStop, boolean ayahValueZero, BigDecimal closetNumberToTarget) {
        this.currentStop = currentStop;
        this.indexStop = indexStop;
        this.ayahValueZero = ayahValueZero;
        this.closetNumberToTarget = closetNumberToTarget;
    }

    public Sorah getCurrentStop() {
        return currentStop;
    }

    public void setCurrentStop(Sorah currentStop) {
        this.currentStop = currentStop;
    }

    public int getIndexStop() {
        return indexStop;
    }

    public void setIndexStop(int indexStop) {
        this.indexStop = indexStop;
    }

    public boolean isAyahValueZero() {
        return ayahValueZero;
    }

    public void setAyahValueZero(boolean ayahValueZero) {
        this.ayahValueZero = ayahValueZero;
    }

    public BigDecimal getClosetNumberToTarget() {
        return closetNumberToTarget;
    }

    public void setClosetNumberToTarget(BigDecimal closetNumberToTarget) {
        this.closetNumberToTarget = closetNumberToTarget;
    }

    @Override
    public String toString() {
        return "SuggestionStop{" +
                "currentStop=" + currentStop +
                ", indexStop=" + indexStop +
                ", ayahValueZero=" + ayahValueZero +
                ", closetNumberToTarget=" + closetNumberToTarget +
                '}';
    }
}
