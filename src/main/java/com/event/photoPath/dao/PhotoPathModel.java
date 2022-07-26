package com.event.photoPath.dao;

import javax.persistence.*;

@Entity
@Table(name = "photo_path_model")
public class PhotoPathModel {

    @Id
    @GeneratedValue(generator = "photo_path", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "photo_path", sequenceName = "photo_path",allocationSize=1)
    @Column
    private Integer id;

    @Column(columnDefinition = "varchar default 'undefined'")
    private String photoURI;

    public PhotoPathModel() {
    }

    public PhotoPathModel(Integer id, String photoURI) {
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
