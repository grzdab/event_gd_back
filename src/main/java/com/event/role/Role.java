package com.event.role;

import com.event.privilege.Privilege;

import java.util.*;

public class Role {

    private UUID id;
    private String name;
    private List<Privilege> privilegesList;

    public Role() {
    }

    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.privilegesList = new ArrayList<>();
    }

    public Role(UUID id, String name, List<Privilege> privilegesList) {
        this.id = id;
        this.name = name;
        this.privilegesList = privilegesList;
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

    public List<Privilege> getPrivileges() {
        return privilegesList;
    }

    public void setPrivileges(List<Privilege> privilegesList) {
        this.privilegesList = privilegesList;
    }

    public void addToPrivilegesList(Privilege privileges){
        privilegesList.add(privileges);
    }

    public void deletePrivilegesFromList(Privilege privileges) {
        for (int i = 0; privilegesList.size() > i; i++) {
            if (privilegesList.get(i).equals(privileges)) {
                privilegesList.remove(privileges);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(privilegesList, role.privilegesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, privilegesList);
    }
}
