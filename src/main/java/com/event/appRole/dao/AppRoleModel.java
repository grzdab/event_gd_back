package com.event.appRole.dao;


import com.event.role.roleDao.RoleModel;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class AppRoleModel {

    Integer id;

    @OneToOne
    RoleModel role;

    public AppRoleModel() {
    }
    public AppRoleModel(RoleModel role) {
        this.role = role;
    }
    public AppRoleModel(Integer id, RoleModel role) {
        this.id = id;
        this.role = role;
    }
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRoleModel that = (AppRoleModel) o;
        return Objects.equals(id, that.id) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
