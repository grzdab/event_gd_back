package com.event.taxInfo;

import com.event.legalEntityType.LegalEntityType;

public class TaxInfo {

    private int id;
    private LegalEntityType legalEntityType;
    private String regon;
    private String pesel;
    private String nip;
    private String krs;
    private String insurance;

    public TaxInfo() {
    }

    public TaxInfo(int id, LegalEntityType legalEntityType, String regon, String pesel, String nip, String krs, String insurance) {
        this.id = id;
        this.legalEntityType = legalEntityType;
        this.regon = regon;
        this.pesel = pesel;
        this.nip = nip;
        this.krs = krs;
        this.insurance = insurance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LegalEntityType getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(LegalEntityType legalEntityType) {
        this.legalEntityType = legalEntityType;
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
}
