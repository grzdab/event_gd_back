package com.event.equipmentCategory.dao;

import javax.persistence.*;

@Entity
@Table(name = "equipment_category_model")
public class EquipmentCategoryModel {

    @Id
    @GeneratedValue(generator = "equipment_category", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_category", sequenceName = "equipment_category_model",allocationSize=1)
    @Column
    private int id;

    @Column(columnDefinition = "int default 0")
    private int sortingGroup;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String description;

    public EquipmentCategoryModel() {
    }

    public EquipmentCategoryModel(int id, String name, int sortingGroup, String description) {
        this.id = id;
        this.name = name;
        this.sortingGroup = sortingGroup;
        this.description = description;
    }

    @Id
    @GeneratedValue
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
}
