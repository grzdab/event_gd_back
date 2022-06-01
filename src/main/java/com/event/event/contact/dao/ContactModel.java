package com.event.event.contact.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContactModel {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
