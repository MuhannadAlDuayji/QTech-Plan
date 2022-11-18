package com.almaherplan.www.QTech.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class  RequestQTech {

    private LocalDate localDate;
    private boolean wayForMemorized;
    private BigDecimal numberOfPageDrs;
    private BigDecimal numberOfLineDrs;

    private int numberOfRememorizedDrs;
    private int numberOfDaysSmallMemorized;
    private BigDecimal numberOfPageBiggestMemorized;
    private int numberOfSorah;
    private int numberOfAyah;
    private int numberOfSorahBiggestMemorize;
    private int numberOfAyahBiggestMemorize;
    private int numberOfDayPeerWeek;
    private int numberOfDayForPlan;
    
    private BigDecimal percentage;


    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public boolean isWayForMemorized() {
        return wayForMemorized;
    }

    public void setWayForMemorized(boolean wayForMemorized) {
        this.wayForMemorized = wayForMemorized;
    }

    public BigDecimal getNumberOfPageDrs() {
        return numberOfPageDrs;
    }

    public void setNumberOfPageDrs(BigDecimal numberOfPageDrs) {
        this.numberOfPageDrs = numberOfPageDrs;
    }

    public BigDecimal getNumberOfLineDrs() {
        return numberOfLineDrs;
    }

    public void setNumberOfLineDrs(BigDecimal numberOfLineDrs) {
        this.numberOfLineDrs = numberOfLineDrs;
    }

    public int getNumberOfRememorizedDrs() {
        return numberOfRememorizedDrs;
    }

    public void setNumberOfRememorizedDrs(int numberOfRememorizedDrs) {
        this.numberOfRememorizedDrs = numberOfRememorizedDrs;
    }
    public int getNumberOfDaysSmallMemorized() {
        return numberOfDaysSmallMemorized;
    }

    public void setNumberOfDaysSmallMemorized(int numberOfDaysSmallMemorized) {
        this.numberOfDaysSmallMemorized = numberOfDaysSmallMemorized;
    }

    public BigDecimal getNumberOfPageBiggestMemorized() {
        return numberOfPageBiggestMemorized;
    }

    public void setNumberOfPageBiggestMemorized(BigDecimal numberOfPageBiggestMemorized) {
        this.numberOfPageBiggestMemorized = numberOfPageBiggestMemorized;
    }

    public int getNumberOfSorah() {
        return numberOfSorah;
    }

    public void setNumberOfSorah(int numberOfSorah) {
        this.numberOfSorah = numberOfSorah;
    }

    public int getNumberOfAyah() {
        return numberOfAyah;
    }

    public void setNumberOfAyah(int numberOfAyah) {
        this.numberOfAyah = numberOfAyah;
    }

    public int getNumberOfSorahBiggestMemorize() {
        return numberOfSorahBiggestMemorize;
    }

    public void setNumberOfSorahBiggestMemorize(int numberOfSorahBiggestMemorize) {
        this.numberOfSorahBiggestMemorize = numberOfSorahBiggestMemorize;
    }

    public int getNumberOfAyahBiggestMemorize() {
        return numberOfAyahBiggestMemorize;
    }

    public void setNumberOfAyahBiggestMemorize(int numberOfAyahBiggestMemorize) {
        this.numberOfAyahBiggestMemorize = numberOfAyahBiggestMemorize;
    }

    public int getNumberOfDayPeerWeek() {
        return numberOfDayPeerWeek;
    }

    public void setNumberOfDayPeerWeek(int numberOfDayPeerWeek) {
        this.numberOfDayPeerWeek = numberOfDayPeerWeek;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public int getNumberOfDayForPlan() {
        return numberOfDayForPlan;
    }

    public void setNumberOfDayForPlan(int numberOfDayForPlan) {
        this.numberOfDayForPlan = numberOfDayForPlan;
    }

    @Override
    public String toString() {
        return "RequestQTech{" +
                "localDate=" + localDate +
                ", wayForMemorized=" + wayForMemorized +
                ", numberOfPageDrs=" + numberOfPageDrs +
                ", numberOfLineDrs=" + numberOfLineDrs +
                ", numberOfRememorizedDrs=" + numberOfRememorizedDrs +
                ", numberOfDaysSmallMemorized=" + numberOfDaysSmallMemorized +
                ", numberOfPageBiggestMemorized=" + numberOfPageBiggestMemorized +
                ", numberOfSorah=" + numberOfSorah +
                ", numberOfAyah=" + numberOfAyah +
                ", numberOfSorahBiggestMemorize=" + numberOfSorahBiggestMemorize +
                ", numberOfAyahBiggestMemorize=" + numberOfAyahBiggestMemorize +
                ", numberOfDayPeerWeek=" + numberOfDayPeerWeek +
                ", numberOfDayForPlan=" + numberOfDayForPlan +
                ", percentage=" + percentage +
                '}';
    }
}
