package com.event.representative.settings.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class SettingsModel {

    @Id
    Integer id;

    String resourcesURI;

    public SettingsModel() {
    }

    public SettingsModel(Integer id, String resourcesURI) {
        this.id = id;
        this.resourcesURI = resourcesURI;
    }

    public SettingsModel(String resourcesURI) {
        this.resourcesURI = resourcesURI;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcesURI() {
        return resourcesURI;
    }

    public void setResourcesURI(String resourcesURI) {
        this.resourcesURI = resourcesURI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingsModel that = (SettingsModel) o;
        return Objects.equals(id, that.id) && Objects.equals(resourcesURI, that.resourcesURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resourcesURI);
    }
}
