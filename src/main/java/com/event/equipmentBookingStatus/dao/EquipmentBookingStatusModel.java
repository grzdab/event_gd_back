package com.event.equipmentBookingStatus.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EquipmentBookingStatusModel {

    @Id
    private Integer id;

    @Column(columnDefinition = "int default 0")
    private int bookingStatus;

    public EquipmentBookingStatusModel(Integer id, int bookingStatus) {
        this.id = id;
        this.bookingStatus = bookingStatus;
    }


    public EquipmentBookingStatusModel() {
    }

    public Integer getId() {
        return id;
    }

    public int getBookingStatus() {
        return bookingStatus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBookingStatus(int bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
