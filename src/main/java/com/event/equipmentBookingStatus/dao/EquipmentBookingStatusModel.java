package com.event.equipmentBookingStatus.dao;

import javax.persistence.*;

@Entity
public class EquipmentBookingStatusModel {

    @Id
    @GeneratedValue(generator = "equipment_booking_status", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_booking_status", sequenceName = "equipment_model",allocationSize=1)
    @Column
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
