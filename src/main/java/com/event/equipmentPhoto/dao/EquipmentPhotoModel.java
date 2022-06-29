package com.event.equipmentPhoto.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EquipmentPhotoModel {

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
