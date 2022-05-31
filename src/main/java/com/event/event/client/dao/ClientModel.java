package com.event.event.client.dao;

import java.util.UUID;

public class ClientModel {

    UUID id;
    String fullName;
    String shortName;
    int contactId;
    boolean isActive;
    int clientTypeId;
    int taxInfoId;
    String notes;
    int legalEntityType;

    public ClientModel() {
    }

    public ClientModel(UUID id, String fullName, String shortName, int contactId, boolean isActive, int clientTypeId, int taxInfoId, String notes, int legalEntityType) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.contactId = contactId;
        this.isActive = isActive;
        this.clientTypeId = clientTypeId;
        this.taxInfoId = taxInfoId;
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

    public int getTaxInfo() {
        return taxInfoId;
    }

    public void setTaxInfo(int taxInfoId) {
        this.taxInfoId = taxInfoId;
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
