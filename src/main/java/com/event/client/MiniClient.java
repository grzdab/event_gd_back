package com.event.client;

import com.event.address.Address;
import com.event.contact.Contact;
import com.event.representative.MiniRepresentative;

import java.util.List;
import java.util.UUID;

public class MiniClient {

    private UUID id;
    private String shortName;
    private List<MiniRepresentative> miniRepresentatives;
    private Contact contact;
    private List<Address> addresses;
    private String userId;

    public MiniClient() {
    }

    public MiniClient(UUID id, String shortName, List<MiniRepresentative> miniRepresentatives, Contact contact, List<Address> addresses, String userId) {
        this.id = id;
        this.shortName = shortName;
        this.miniRepresentatives = miniRepresentatives;
        this.contact = contact;
        this.addresses = addresses;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<MiniRepresentative> getMiniRepresentatives() {
        return miniRepresentatives;
    }

    public void setMiniRepresentatives(List<MiniRepresentative> miniRepresentatives) {
        this.miniRepresentatives = miniRepresentatives;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
