package com.event.language;

import java.util.Objects;

public class Language {

    private int id;
    private String propertyName;

    public Language() {
    }

    public Language(int id, String propertyName) {
        this.id = id;
        this.propertyName = propertyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(id, language.id) && Objects.equals(propertyName, language.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyName);
    }
}
