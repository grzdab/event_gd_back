package com.event.representative.settings;

import java.util.Objects;

public class Settings {

    private int id;
    private String resourcesURI;

    public Settings() {
    }

    public Settings(int id, String resourcesURI) {
        this.id = id;
        this.resourcesURI = resourcesURI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        Settings settings = (Settings) o;
        return Objects.equals(id, settings.id) && Objects.equals(resourcesURI, settings.resourcesURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resourcesURI);
    }
}
