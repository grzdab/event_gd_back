package com.event.taxInfo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaxInfoModel {

    private Integer id;
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
    private String legalEntityTypeId;

    public TaxInfoModel() {
    }

    public TaxInfoModel(Integer id, String regon, String pesel, String nip, String krs, String insurance, String legalEntityTypeId) {
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getLegalEntityTypeId() {
        return legalEntityTypeId;
    }

    public void setLegalEntityTypeId(String legalEntityTypeId) {
        this.legalEntityTypeId = legalEntityTypeId;
    }
}
