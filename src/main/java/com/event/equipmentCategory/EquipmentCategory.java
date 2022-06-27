package com.event.equipmentCategory;

public class EquipmentCategory {
    private int id;
    private String name;
    private int sortingGroup;
    private String description;

    public EquipmentCategory() {
    }

    public EquipmentCategory(int id, String name, int sortingGroup, String description) {
        this.id = id;
        this.name = name;
        this.sortingGroup = sortingGroup;
        this.description = description;
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

    public void setDescription(String description) {
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
