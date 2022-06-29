package com.event.legalEntityType;

import java.util.Map;
import java.util.Objects;

public class LegalEntityType {
    private int id;
    private String typeName;
    private Map<String,String> legalEntityType;

    public LegalEntityType() {
    }

    public LegalEntityType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public LegalEntityType(String typeName) {
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegalEntityType that = (LegalEntityType) o;
        return Objects.equals(id, that.id) && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }
}
