package com.almaherplan.www.QTech.model;

public class RequestPrint {

    private Integer id;

    private String gregorianDate;
    private String hijrahDate;

    private String memorizedFromSorah;
    private String memorizedFromAyah;
    private String memorizedToSorah;
    private String memorizedToAyah;

    private String smallMemorizedFromSorah;
    private String smallMemorizedFromAyah;
    private String smallMemorizedToSorah;
    private String smallMemorizedToAyah;

    private String biggestMemorizedFromSorah;
    private String biggestMemorizedFromAyah;
    private String biggestMemorizedToSorah;
    private String biggestMemorizedToAyah;
    private String day;



    public RequestPrint() {
    }

    public RequestPrint(Integer id, String gregorianDate, String hijrahDate,
                        String memorizedFromSorah, String memorizedFromAyah,
                        String memorizedToSorah, String memorizedToAyah,
                        String smallMemorizedFromSorah, String smallMemorizedFromAyah,
                        String smallMemorizedToSorah, String smallMemorizedToAyah,
                        String biggestMemorizedFromSorah, String biggestMemorizedFromAyah,
                        String biggestMemorizedToSorah, String biggestMemorizedToAyah, String day) {
        this.id = id;
        this.gregorianDate = gregorianDate;
        this.hijrahDate = hijrahDate;
        this.memorizedFromSorah = memorizedFromSorah;
        this.memorizedFromAyah = memorizedFromAyah;
        this.memorizedToSorah = memorizedToSorah;
        this.memorizedToAyah = memorizedToAyah;
        this.smallMemorizedFromSorah = smallMemorizedFromSorah;
        this.smallMemorizedFromAyah = smallMemorizedFromAyah;
        this.smallMemorizedToSorah = smallMemorizedToSorah;
        this.smallMemorizedToAyah = smallMemorizedToAyah;
        this.biggestMemorizedFromSorah = biggestMemorizedFromSorah;
        this.biggestMemorizedFromAyah = biggestMemorizedFromAyah;
        this.biggestMemorizedToSorah = biggestMemorizedToSorah;
        this.biggestMemorizedToAyah = biggestMemorizedToAyah;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGregorianDate() {
        return gregorianDate;
    }

    public void setGregorianDate(String gregorianDate) {
        this.gregorianDate = gregorianDate;
    }

    public String getHijrahDate() {
        return hijrahDate;
    }

    public void setHijrahDate(String hijrahDate) {
        this.hijrahDate = hijrahDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMemorizedFromSorah() {
        return memorizedFromSorah;
    }

    public void setMemorizedFromSorah(String memorizedFromSorah) {
        this.memorizedFromSorah = memorizedFromSorah;
    }

    public String getMemorizedFromAyah() {
        return memorizedFromAyah;
    }

    public void setMemorizedFromAyah(String memorizedFromAyah) {
        this.memorizedFromAyah = memorizedFromAyah;
    }

    public String getMemorizedToSorah() {
        return memorizedToSorah;
    }

    public void setMemorizedToSorah(String memorizedToSorah) {
        this.memorizedToSorah = memorizedToSorah;
    }

    public String getMemorizedToAyah() {
        return memorizedToAyah;
    }

    public void setMemorizedToAyah(String memorizedToAyah) {
        this.memorizedToAyah = memorizedToAyah;
    }

    public String getSmallMemorizedFromSorah() {
        return smallMemorizedFromSorah;
    }

    public void setSmallMemorizedFromSorah(String smallMemorizedFromSorah) {
        this.smallMemorizedFromSorah = smallMemorizedFromSorah;
    }

    public String getSmallMemorizedFromAyah() {
        return smallMemorizedFromAyah;
    }

    public void setSmallMemorizedFromAyah(String smallMemorizedFromAyah) {
        this.smallMemorizedFromAyah = smallMemorizedFromAyah;
    }

    public String getSmallMemorizedToSorah() {
        return smallMemorizedToSorah;
    }

    public void setSmallMemorizedToSorah(String smallMemorizedToSorah) {
        this.smallMemorizedToSorah = smallMemorizedToSorah;
    }

    public String getSmallMemorizedToAyah() {
        return smallMemorizedToAyah;
    }

    public void setSmallMemorizedToAyah(String smallMemorizedToAyah) {
        this.smallMemorizedToAyah = smallMemorizedToAyah;
    }

    public String getBiggestMemorizedFromSorah() {
        return biggestMemorizedFromSorah;
    }

    public void setBiggestMemorizedFromSorah(String biggestMemorizedFromSorah) {
        this.biggestMemorizedFromSorah = biggestMemorizedFromSorah;
    }

    public String getBiggestMemorizedFromAyah() {
        return biggestMemorizedFromAyah;
    }

    public void setBiggestMemorizedFromAyah(String biggestMemorizedFromAyah) {
        this.biggestMemorizedFromAyah = biggestMemorizedFromAyah;
    }

    public String getBiggestMemorizedToSorah() {
        return biggestMemorizedToSorah;
    }

    public void setBiggestMemorizedToSorah(String biggestMemorizedToSorah) {
        this.biggestMemorizedToSorah = biggestMemorizedToSorah;
    }

    public String getBiggestMemorizedToAyah() {
        return biggestMemorizedToAyah;
    }

    public void setBiggestMemorizedToAyah(String biggestMemorizedToAyah) {
        this.biggestMemorizedToAyah = biggestMemorizedToAyah;
    }


    @Override
    public String toString() {
        return "RequestPrint{" +
                "id=" + id +
                ", gregorianDate='" + gregorianDate + '\'' +
                ", hijrahDate='" + hijrahDate + '\'' +
                ", day='" + day + '\'' +
                ", memorizedFromSorah='" + memorizedFromSorah + '\'' +
                ", memorizedFromAyah=" + memorizedFromAyah +
                ", memorizedToSorah='" + memorizedToSorah + '\'' +
                ", memorizedToAyah=" + memorizedToAyah +
                ", smallMemorizedFromSorah='" + smallMemorizedFromSorah + '\'' +
                ", smallMemorizedFromAyah=" + smallMemorizedFromAyah +
                ", smallMemorizedToSorah='" + smallMemorizedToSorah + '\'' +
                ", smallMemorizedToAyah=" + smallMemorizedToAyah +
                ", biggestMemorizedFromSorah='" + biggestMemorizedFromSorah + '\'' +
                ", biggestMemorizedFromAyah=" + biggestMemorizedFromAyah +
                ", biggestMemorizedToSorah='" + biggestMemorizedToSorah + '\'' +
                ", biggestMemorizedToAyah=" + biggestMemorizedToAyah +
                '}';
    }
}
