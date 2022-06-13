package com.event.appRole.roleDao;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.UUID;

@Entity
public class AppRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @NotEmpty
    String name;

//    @Column(columnDefinition = "boolean default false")
//    Boolean isActive;

    public AppRoleModel() {
    }

    public AppRoleModel(String privilege) {
        this.name = privilege;
//        this.isActive = active;
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

//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRoleModel that = (AppRoleModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
