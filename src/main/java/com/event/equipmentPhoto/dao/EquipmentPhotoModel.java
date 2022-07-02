package com.event.equipmentPhoto.dao;

import javax.persistence.*;

@Entity
public class EquipmentPhotoModel {

    @Id
    @GeneratedValue(generator = "equipment_photo", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_photo", sequenceName = "equipment_photo",allocationSize=1)
    @Column
    private Integer id;

    @Column(columnDefinition = "varchar default 'undefined'")
    private String photoURI;

    public EquipmentPhotoModel() {
    }

    public EquipmentPhotoModel(Integer id, String photoURI) {
        this.id = id;
        this.photoURI = photoURI;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }
}
