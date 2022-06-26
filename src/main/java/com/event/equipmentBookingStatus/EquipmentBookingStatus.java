package com.event.equipmentBookingStatus;

public class EquipmentBookingStatus {
    private Integer id;
    private int bookingStatus;

    public EquipmentBookingStatus(Integer id, int bookingStatus) {
        this.id = id;
        this.bookingStatus = bookingStatus;
    }

    public EquipmentBookingStatus() {
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
