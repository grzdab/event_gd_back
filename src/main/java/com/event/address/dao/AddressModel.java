package com.event.address.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AddressModel {
    private Integer id;
    @Column(length = 100) //wartosc nie moze byc nullem, max 100 znakow
    private String street;
    @Column(length = 10)
    private String streetNumber;
    @Column(length = 10)
    private String postalCode;
    @Column(length = 100)
    private String city;
    private int countryId;
    @Column(columnDefinition = "boolean default true")
    private Boolean isPrimaryAddress;
    private String clientId;

    public AddressModel() {
    }

    public AddressModel(Integer id, String street, String streetNumber, String postalCode, String city,
                        int countryId, boolean isPrimaryAddress, String clientId) {
        this.id = id;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.countryId = countryId;
        this.isPrimaryAddress = isPrimaryAddress;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public Boolean isPrimaryAddress() {
        return isPrimaryAddress;
    }

    public void setPrimaryAddress(Boolean primary) {
        isPrimaryAddress = primary;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
