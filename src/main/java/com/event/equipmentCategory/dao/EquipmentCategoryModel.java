package com.event.equipmentCategory.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class EquipmentCategoryModel {

    @Id
    @GeneratedValue(generator = "equipment_category", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_category", sequenceName = "equipment_category",allocationSize=1)
    @Column
    private Long id;

    @Column(columnDefinition = "varchar default unnamed")
    private String name;

    @Column(columnDefinition = "int default 0")
    private int SortingGroup;

    @Column(columnDefinition = "varchar default nunnamed")
    private String description;

    public EquipmentCategoryModel() {
    }

    public EquipmentCategoryModel(Long id, String name, int sortingGroup, String description) {
        this.id = id;
        this.name = name;
        this.SortingGroup = sortingGroup;
        this.description = description;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSortingGroup() {
        return SortingGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortingGroup(int sortingGroup) {
        SortingGroup = sortingGroup;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}