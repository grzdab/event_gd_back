package com.event.equipment.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipment_model")
public class EquipmentModel {

    @Id
    @GeneratedValue(generator = "equipment", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment", sequenceName = "equipment",allocationSize=1)
    @Column
    private Integer id;

    @Column(columnDefinition = "int default 1")
    private int sortingId;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    @Column(columnDefinition = "varchar default 'null'")
    private String notes;

    @Column(columnDefinition = "int default 0")
    private int equipmentDataId;

    @Column(columnDefinition = "int default 0")
    private int equipmentCategoryId;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> equipmentPhotoId;

    @Column(columnDefinition = "int default 0")
    private int equipmentStatusId;

    @Column(columnDefinition = "int default 0")
    private Integer equipmentBookingStatusId;

    @Column
    @ElementCollection(targetClass = Integer.class)
    private List<Integer> equipmentBookingPeriodsId;

    @Column(columnDefinition = "boolean default true")
    private boolean inUse;
}


