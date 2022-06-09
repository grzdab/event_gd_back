package com.event.companyData;

import com.event.address.Address;
import com.event.contact.Contact;
import com.event.legalEntityType.LegalEntityType;
import com.event.taxInfo.TaxInfo;

import java.util.Objects;
import java.util.UUID;

public class CompanyData {
    private UUID id;
    private String fullName;
    private String shortName;
    private TaxInfo taxInfo;
    private LegalEntityType legalEntityType;
    private Boolean inUse;
    private String notes;
    private Address address;
    private Contact contact;

    public CompanyData() {
    }

    public CompanyData(UUID id, String fullName, String shortName, TaxInfo taxInfo, LegalEntityType legalEntityType, Boolean inUse, String notes, Address address, Contact contact) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.taxInfo = taxInfo;
        this.legalEntityType = legalEntityType;
        this.inUse = inUse;
        this.notes = notes;
        this.address = address;
        this.contact = contact;
    }

    public CompanyData(String fullName, String shortName, TaxInfo taxInfo, LegalEntityType legalEntityType, Boolean inUse, String notes, Address address, Contact contact) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.taxInfo = taxInfo;
        this.legalEntityType = legalEntityType;
        this.inUse = inUse;
        this.notes = notes;
        this.address = address;
        this.contact = contact;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public TaxInfo getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
    }

    public LegalEntityType getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(LegalEntityType legalEntityType) {
        this.legalEntityType = legalEntityType;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyData that = (CompanyData) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName)
                && Objects.equals(shortName, that.shortName)
                && Objects.equals(taxInfo, that.taxInfo)
                && Objects.equals(legalEntityType, that.legalEntityType)
                && Objects.equals(inUse, that.inUse)
                && Objects.equals(notes, that.notes)
                && Objects.equals(address, that.address)
                && Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, shortName, taxInfo, legalEntityType, inUse, notes, address, contact);
    }
}
