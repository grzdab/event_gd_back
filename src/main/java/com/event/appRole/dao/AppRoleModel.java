package com.event.appRole.dao;


import com.event.role.roleDao.RoleModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class AppRoleModel {
    @Id
    String id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    RoleModel role;

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }
}
