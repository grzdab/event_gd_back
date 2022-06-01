package com.event.event.client.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity //mowi springowi, ze ta klasa bedzie modelem w bazie i na jej podstawie ma stworzyc tabele
@Table(name = "client") //nazwa tabeli
public class ClientModel {

    UUID id; //w klasie modelu musi znajowac sie pole o nazwie id
    String fullName;
    String shortName;
    int contactId;
    boolean isActive;
    int clientTypeId;
    String notes;
    int taxInfoId;

    public ClientModel() {
    }

    public ClientModel(String fullName, String shortName, int contactId, boolean isActive, int clientTypeId, String notes, int taxInfoId) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.contactId = contactId;
        this.isActive = isActive;
        this.clientTypeId = clientTypeId;
        this.notes = notes;
        this.taxInfoId = taxInfoId;
    }

    @Id //kazda klasa entity musi miec unikatowe id, wiec metoda getId musi miec adnotacje Id
    @GeneratedValue //id bedzie automatycznie generowane przez baze
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getTaxInfoId() {
        return taxInfoId;
    }

    public void setTaxInfoId(int taxInfoId) {
        this.taxInfoId = taxInfoId;
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
}
