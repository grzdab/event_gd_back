package com.event.taxInfo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaxInfoModel {

    @Column
    private int id;
    @Column(length = 20)
    private String regon;
    @Column(length = 11)
    private String pesel;
    @Column(length = 20)
    private String nip;
    @Column(length = 20)
    private String krs;
    @Column(length = 20)
    private String insurance;
    private Integer legalEntityTypeId;

    public TaxInfoModel() {
    }

    public TaxInfoModel(int id, String regon, String pesel, String nip, String krs, String insurance, Integer legalEntityTypeId) {
        this.id = id;
        this.regon = regon;
        this.pesel = pesel;
        this.nip = nip;
        this.krs = krs;
        this.insurance = insurance;
        this.legalEntityTypeId = legalEntityTypeId;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getKrs() {
        return krs;
    }

    public void setKrs(String krs) {
        this.krs = krs;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public Integer getLegalEntityTypeId() {
        return legalEntityTypeId;
    }

    public void setLegalEntityTypeId(Integer legalEntityTypeId) {
        this.legalEntityTypeId = legalEntityTypeId;
    }
}
