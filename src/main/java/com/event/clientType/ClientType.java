package com.event.clientType;

import java.util.Map;
import java.util.Objects;

public class ClientType {

    private String id;
    private String typeName;
    private Map<Integer, String> clientType;

    public ClientType() {
    }

    public ClientType(String id, String typeName) {
        this.id = id;
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

    public void update(){
        
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientType that = (ClientType) o;
        return id == that.id && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }
}
