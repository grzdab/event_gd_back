package com.event.equipment.models;

import java.util.Map;

public class BookingStatus {
    private Map<Integer, String> bookingStatuses;

    public BookingStatus() {
    }

    public Map<Integer, String> getBookingStatuses() {
        return bookingStatuses;
    }

    public void setBookingStatuses(Map<Integer, String> bookingStatuses) {
        this.bookingStatuses = bookingStatuses;
    }
}
