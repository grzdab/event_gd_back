package com.event.privilege;

import java.util.Objects;

public class Privilege {

    private int id;
    private String name;
    private PrivilegeEnum privilegesEnum;

    public Privilege(int id, String name, PrivilegeEnum privilegesEnum) {
        this.id = id;
        this.name = name;
        this.privilegesEnum = privilegesEnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrivilegeEnum getPrivilegesEnum() {
        return privilegesEnum;
    }

    public void setPrivilegesEnum(PrivilegeEnum privilegesEnum) {
        this.privilegesEnum = privilegesEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege that = (Privilege) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
