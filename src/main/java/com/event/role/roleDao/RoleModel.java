package com.event.role.roleDao;

import com.event.privilege.dao.PrivilegeModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Entity
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleModelId;
    @NotEmpty
    private String name;
//    @ManyToMany
//    private List<PrivilegeModel> privilegesList;

    public RoleModel() {
    }

    public RoleModel(String name) {
        this.name = name;
    }

    public Integer getRoleModelId() {
        return roleModelId;
    }

    public void setRoleModelId(Integer id) {
        this.roleModelId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<PrivilegeModel> getPrivilegesList() {
//        return privilegesList;
//    }
//
//    public void setPrivilegesList(List<PrivilegeModel> privilegesList) {
//        this.privilegesList = privilegesList;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RoleModel roleModel = (RoleModel) o;
//        return Objects.equals(roleModelId, roleModel.roleModelId) && Objects.equals(name, roleModel.name) && Objects.equals(privilegesList, roleModel.privilegesList);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(roleModelId, name, privilegesList);
//    }
}
