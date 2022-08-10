package com.event.client.dao;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity //mowi springowi, ze ta klasa bedzie modelem w bazie i na jej podstawie ma stworzyc tabele
@Table(name = "client") //nazwa tabeli
public class ClientModel {

    @Column
    private UUID id; //w klasie modelu musi znajowac sie pole o nazwie id
    @Column(nullable = false, length = 200) //wartosc nie moze byc nullem, max 200 znakow
    private String fullName;
    @Column(nullable = false, length = 100) //wartosc nie moze byc nullem, max 100 znakow
    private String shortName;
    @Column
    private int contactId;
    @Column(nullable = false)
    @ColumnDefault("true") // wartosc domyslna
    private boolean isActive;
    @Column
    private int clientTypeId;
    @Column
    private String notes;
    @Column
    private int taxInfoId;
    @Column(nullable = false)
    private String appUserId;
    @Column
    private List<Integer> businessBranchesIds;
    @Column
    private List<Integer> businessCategoriesIds;

    public ClientModel() {
    }

    public ClientModel(String fullName, String shortName, int contactId, boolean isActive, int clientTypeId, String notes, int taxInfoId, String appUserId) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.contactId = contactId;
        this.isActive = isActive;
        this.clientTypeId = clientTypeId;
        this.notes = notes;
        this.taxInfoId = taxInfoId;
        this.appUserId = appUserId;
        this.businessBranchesIds = new ArrayList<>();
        this.businessCategoriesIds = new ArrayList<>();
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

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    @ElementCollection
    public List<Integer> getBusinessBranchesId() {
        return businessBranchesIds;
    }

    public void setBusinessBranchesId(List<Integer> businessBranchesIds) {
        this.businessBranchesIds = businessBranchesIds;
    }

    @ElementCollection
    public List<Integer> getBusinessCategoriesId() {
        return businessCategoriesIds;
    }

    public void setBusinessCategoriesId(List<Integer> businessCategoriesIds) {
        this.businessCategoriesIds = businessCategoriesIds;
    }
}
