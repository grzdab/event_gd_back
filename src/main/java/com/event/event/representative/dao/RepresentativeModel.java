package com.event.event.representative.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class RepresentativeModel {
    private UUID id;

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    public UUID getId() {
        return id;
    }
}
