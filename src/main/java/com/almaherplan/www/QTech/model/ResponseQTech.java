package com.almaherplan.www.QTech.model;

import java.time.LocalDate;
import java.time.chrono.HijrahDate;
import java.util.List;

public class ResponseQTech {

    private List<LocalDate> gregorianDateList;
    private List<String> hijrahDateList;
    private List<Drs> memorized;
    private List<Drs> smallMemorized;
    private List<Drs> biggestMemorized;

    public List<LocalDate> getGregorianDateList() {
        return gregorianDateList;
    }

    public void setGregorianDateList(List<LocalDate> gregorianDateList) {
        this.gregorianDateList = gregorianDateList;
    }

    public List<String> getHijrahDateList() {
        return hijrahDateList;
    }

    public void setHijrahDateList(List<String> hijrahDateList) {
        this.hijrahDateList = hijrahDateList;
    }

    public List<Drs> getMemorized() {
        return memorized;
    }

    public void setMemorized(List<Drs> memorized) {
        this.memorized = memorized;
    }

    public List<Drs> getSmallMemorized() {
        return smallMemorized;
    }

    public void setSmallMemorized(List<Drs> smallMemorized) {
        this.smallMemorized = smallMemorized;
    }

    public List<Drs> getBiggestMemorized() {
        return biggestMemorized;
    }

    public void setBiggestMemorized(List<Drs> biggestMemorized) {
        this.biggestMemorized = biggestMemorized;
    }
}
