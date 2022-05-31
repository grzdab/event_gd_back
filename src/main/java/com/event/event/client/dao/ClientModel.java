package com.event.event.client.dao;

import com.event.event.address.dao.AddressModel;
import com.event.event.businessBranch.dao.BusinessBranchModel;
import com.event.event.businessCategory.dao.BusinessCategoryModel;
import com.event.event.clientType.dao.ClientTypeModel;
import com.event.event.representative.dao.RepresentativeModel;
import com.event.event.taxInfo.dao.TaxInfoModel;
import io.swagger.models.Contact;

import java.util.List;
import java.util.UUID;

public class ClientModel {

    UUID id;
    String fullName;
    String shortName;
    int contactId;
    boolean isActive;
    int clientTypeId;
    TaxInfoModel taxInfo;
    String notes;
    int legalEntityType;

    public ClientModel() {
    }

    public ClientModel(UUID id, String fullName, String shortName, int contactId, boolean isActive, int clientTypeId, TaxInfoModel taxInfo, String notes, int legalEntityType) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.contactId = contactId;
        this.isActive = isActive;
        this.clientTypeId = clientTypeId;
        this.taxInfo = taxInfo;
        this.notes = notes;
        this.legalEntityType = legalEntityType;
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

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(int clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public TaxInfoModel getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(TaxInfoModel taxInfo) {
        this.taxInfo = taxInfo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getLegalEntityType() {
        return legalEntityType;
    }

    public void setLegalEntityType(int legalEntityType) {
        this.legalEntityType = legalEntityType;
    }
}
