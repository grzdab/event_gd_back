package com.event.event.client;
import com.event.event.address.Address;
import com.event.event.businessBranch.BusinessBranch;
import com.event.event.businessCategory.BusinessCategory;
import com.event.event.clientType.ClientType;
import com.event.event.representative.Representative;
import com.event.event.taxInfo.TaxInfo;
import io.swagger.models.Contact;

import java.util.List;
import java.util.UUID;

public class Client {

    UUID id;
    String fullName;
    String shortName;
    List<Address> addresses;
    Contact contact;
    boolean isActive;
    ClientType clientType;
    TaxInfo taxInfo;
    List<BusinessBranch> businessBranches;
    List<BusinessCategory> businessCategories;
    String notes;
    List<Representative> representatives;

    public Client() {
    }

    public Client(UUID id, String fullName, String shortName, List<Address> addresses, Contact contact, boolean isActive, ClientType clientType, TaxInfo taxInfo, List<BusinessBranch> businessBranches, List<BusinessCategory> businessCategories, String notes, List<Representative> representatives) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.addresses = addresses;
        this.contact = contact;
        this.isActive = isActive;
        this.clientType = clientType;
        this.taxInfo = taxInfo;
        this.businessBranches = businessBranches;
        this.businessCategories = businessCategories;
        this.notes = notes;
        this.representatives = representatives;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public TaxInfo getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(TaxInfo taxInfo) {
        this.taxInfo = taxInfo;
    }

    public List<BusinessBranch> getBusinessBranches() {
        return businessBranches;
    }

    public void setBusinessBranches(List<BusinessBranch> businessBranches) {
        this.businessBranches = businessBranches;
    }

    public List<BusinessCategory> getBusinessCategories() {
        return businessCategories;
    }

    public void setBusinessCategories(List<BusinessCategory> businessCategories) {
        this.businessCategories = businessCategories;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Representative> getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(List<Representative> representatives) {
        this.representatives = representatives;
    }
}
