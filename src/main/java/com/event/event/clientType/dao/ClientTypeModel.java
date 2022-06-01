package com.event.event.clientType.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientTypeModel {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    public Integer getId() {
        return id;
    }
}
