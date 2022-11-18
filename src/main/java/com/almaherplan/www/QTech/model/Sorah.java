package com.almaherplan.www.QTech.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(
        name = "Sorah"
)
public class Sorah {

    @Id
    @JsonIgnore
    private Integer rid;
    @JsonIgnore
    private Integer lid;
    private Integer sorah;

    private Integer ayah;
    @JsonIgnore
    private BigDecimal length;
    @JsonIgnore
    private Integer page;
    @JsonIgnore
    private Integer hizb;
    @JsonIgnore
    private Integer juz;
    @JsonIgnore
    private BigDecimal rsum;
    @JsonIgnore
    private BigDecimal lsum;


    public Sorah() {
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getSorah() {
        return sorah;
    }

    public void setSorah(Integer sorah) {
        this.sorah = sorah;
    }

    public Integer getAyah() {
        return ayah;
    }

    public void setAyah(Integer ayah) {
        this.ayah = ayah;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getHizb() {
        return hizb;
    }

    public void setHizb(Integer hizb) {
        this.hizb = hizb;
    }

    public Integer getJuz() {
        return juz;
    }

    public void setJuz(Integer juz) {
        this.juz = juz;
    }

    public BigDecimal getRsum() {
        return rsum;
    }

    public void setRsum(BigDecimal rsum) {
        this.rsum = rsum;
    }

    public BigDecimal getLsum() {
        return lsum;
    }

    public void setLsum(BigDecimal lsum) {
        this.lsum = lsum;
    }

    @Override
    public String toString() {
        return "Sorah{" +
                "rid=" + rid +
                ", lid=" + lid +
                ", sorah=" + sorah +
                ", ayah=" + ayah +
                ", length=" + length +
                ", page=" + page +
                ", hizb=" + hizb +
                ", juz=" + juz +
                ", rsum=" + rsum +
                ", lsum=" + lsum +
                '}';
    }
}
