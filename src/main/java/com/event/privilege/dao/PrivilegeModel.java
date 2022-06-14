package com.event.privilege.dao;

import com.event.privilege.PrivilegeEnum;
import com.event.role.roleDao.RoleModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity(name = "PrivilegesModel")
public class PrivilegeModel {

    private Integer id;

    @NotEmpty
    private String name;
    private PrivilegeEnum type;

    public PrivilegeModel() {
    }

    public PrivilegeModel(int id, String name, PrivilegeEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public PrivilegeModel(String name, PrivilegeEnum type) {
        this.name = name;
        this.type = type;
    }

    @Id
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

    public PrivilegeEnum getType() {
        return type;
    }

    public void setType(PrivilegeEnum type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivilegeModel that = (PrivilegeModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
