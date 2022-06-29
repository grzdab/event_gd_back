package com.event.equipmentPhoto;

public class EquipmentPhoto {
    private int id;
    private String photoURI;

    public EquipmentPhoto() {
    }

    public EquipmentPhoto(int id, String photoURI) {
        this.id = id;
        this.photoURI = photoURI;
    }

    public int getId() {
        return id;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }
}
