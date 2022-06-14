package com.event.privilege.dao;

import com.event.privilege.PrivilegesEnum;
import com.event.role.Role;
import com.event.role.roleDao.RoleModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "PrivilegesModel")
public class PrivilegesModel {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotEmpty
    private String name;

    private PrivilegesEnum type;

    @ManyToOne
    @JoinColumn
    private RoleModel role;


    public PrivilegesModel() {
    }

    public PrivilegesModel(String id, String name, PrivilegesEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public PrivilegesModel(String name, PrivilegesEnum type) {
        this.name = name;
        this.type = type;
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

    public PrivilegesEnum getType() {
        return type;
    }

    public void setType(PrivilegesEnum type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivilegesModel that = (PrivilegesModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
