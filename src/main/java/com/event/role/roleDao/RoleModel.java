package com.event.role.roleDao;

import com.event.privilege.dao.PrivilegeModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity(name = "RoleModel")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @NotEmpty
    String name;

    @OneToMany
    private List<PrivilegeModel> privilegesList = new ArrayList<>();

    public RoleModel() {
    }

    public RoleModel(String privilege) {
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

    public void setName(String privilege) {
        this.name = privilege;
    }

    public List<PrivilegeModel> getPrivilegesList() {
        return privilegesList;
    }

    public void setPrivilegesList(List<PrivilegeModel> privilegesList) {
        this.privilegesList = privilegesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleModel roleModel = (RoleModel) o;
        return Objects.equals(id, roleModel.id) && Objects.equals(name, roleModel.name) && Objects.equals(privilegesList, roleModel.privilegesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, privilegesList);
    }
}
