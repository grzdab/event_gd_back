package com.event.photoPath;

public class PhotoPath {
    private int id;
    private String photoURI;

    public PhotoPath() {
    }

    public PhotoPath(int id, String photoURI) {
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
