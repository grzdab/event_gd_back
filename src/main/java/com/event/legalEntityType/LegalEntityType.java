package com.event.legalEntityType;

import java.util.Map;
import java.util.Objects;

public class LegalEntityType {
    private String id;
    private String typeName;
    private Map<String,String> legalEntityType;

    public LegalEntityType() {
    }

    public LegalEntityType(String id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public LegalEntityType(String typeName) {
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
