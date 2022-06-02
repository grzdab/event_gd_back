package com.event.representative.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "representative") //nazwa tabeli
public class RepresentativeModel {

    private Integer id;
    private String firstName;
    private String lastName;
    private int contactId;
    private String clientId;

    public RepresentativeModel() {
    }

    public RepresentativeModel(String firstName, String lastName, int contactId, String clientId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactId = contactId;
        this.clientId = clientId;
    }

    public RepresentativeModel(Integer id, String firstName, String lastName, int contactId, String clientId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactId = contactId;
        this.clientId = clientId;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
