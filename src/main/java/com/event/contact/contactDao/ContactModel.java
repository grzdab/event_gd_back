package com.event.contact.contactDao;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String email;
    String phone;

    public ContactModel() {
    }

    public ContactModel(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactModel contact = (ContactModel) o;
        return Objects.equals(email, contact.email) && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }
}
