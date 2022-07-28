package com.event.equipmentCategory.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="equipment_category_model")
public class EquipmentCategoryModel {

    @Id
    @GeneratedValue(generator = "equipment_category", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_category", sequenceName = "equipment_category",allocationSize=1)
    @Column
    private int id;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    @Column(columnDefinition = "int default 0")
    private int SortingGroup;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String description;

    public EquipmentCategoryModel() {
    }

    public EquipmentCategoryModel(int id, String name, int sortingGroup, String description) {
        this.id = id;
        this.SortingGroup = sortingGroup;
        this.name = name;
        this.description = description;
    }

//    @Id
//    @GeneratedValue
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getSortingGroup() {
//        return SortingGroup;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setSortingGroup(int sortingGroup) {
//        SortingGroup = sortingGroup;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}