package com.event.role;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Role {

    private UUID id;
    private String name;

    public Role() {
    }

    public Role(UUID id, String privilege) {
        this.id = id;
        this.name = privilege;
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
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
