package com.event.privilege.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.UUID;

@Entity
public class PrivilegesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @NotEmpty
    String name;

    public PrivilegesModel() {
    }

    public PrivilegesModel(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public PrivilegesModel(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivilegesModel that = (PrivilegesModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
