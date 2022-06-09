package com.event.language.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class LanguageModel {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @NotEmpty
    String propertyName;

    public LanguageModel(String id, String propertyName) {
        this.id = id;
        this.propertyName = propertyName;
    }

    public LanguageModel(String propertyName) {
        this.propertyName = propertyName;
    }

    public LanguageModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        LanguageModel that = (LanguageModel) o;
        return Objects.equals(id, that.id) && Objects.equals(propertyName, that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyName);
    }
}
