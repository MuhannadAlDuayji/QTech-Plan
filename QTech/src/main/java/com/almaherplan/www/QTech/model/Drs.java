package com.almaherplan.www.QTech.model;

public class Drs {

    private Sorah from;
    private Sorah to;

    public Drs(Sorah from, Sorah to){
        this.from=from;
        this.to=to;
    }

    public Sorah getFrom() {
        return from;
    }

    public void setFrom(Sorah from) {
        this.from = from;
    }

    public Sorah getTo() {
        return to;
    }

    public void setTo(Sorah to) {
        this.to = to;
    }

    @Override
    public String toString() {

        if (this == null)
            return "null";
        return "Drs{" +
                "from=" + from +
                "\n, to=" + to +
                '}';
    }
}
