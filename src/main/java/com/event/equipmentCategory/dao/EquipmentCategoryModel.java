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
    @SequenceGenerator(name = "equipment_category", sequenceName = "equipment_category", allocationSize = 1)
    @Column
    private int id;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    @Column(columnDefinition = "int default 0")
    private int sortingGroup;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String description;

    public EquipmentCategoryModel() {
    }

    public EquipmentCategoryModel(int id, String name, int sortingGroup, String description) {
        this.id = id;
        this.sortingGroup = sortingGroup;
        this.name = name;
        this.description = description;
    }
}
