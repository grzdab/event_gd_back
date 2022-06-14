package com.event.privilege;

import java.util.Objects;

public class Privileges {

    private String id;
    private String name;
    private PrivilegesEnum privilegesEnum;

    public Privileges(String id, String name, PrivilegesEnum privilegesEnum) {
        this.id = id;
        this.name = name;
        this.privilegesEnum = privilegesEnum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrivilegesEnum getPrivilegesEnum() {
        return privilegesEnum;
    }

    public void setPrivilegesEnum(PrivilegesEnum privilegesEnum) {
        this.privilegesEnum = privilegesEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privileges that = (Privileges) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
