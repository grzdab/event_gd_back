package com.event.equipment.models;

public class EquipmentCategory {
    int id;
    private String name;
    int sortingGroup;

    public EquipmentCategory() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortingGroup(int sortingGroup) {
        this.sortingGroup = sortingGroup;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSortingGroup() {
        return sortingGroup;
    }
}
