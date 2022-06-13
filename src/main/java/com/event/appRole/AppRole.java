package com.event.appRole;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AppRole {

    private UUID id;
    private String name;
    private List<Map<String, Boolean>> privileges;

    public AppRole() {
    }

    public AppRole(UUID id, String privilege) {
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

    public List<Map<String, Boolean>> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Map<String, Boolean>> privileges) {
        this.privileges = privileges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole role = (AppRole) o;
        return Objects.equals(id, role.id) && Objects.equals(privileges, role.privileges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, privileges);
    }
}
