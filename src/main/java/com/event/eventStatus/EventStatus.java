package com.event.eventStatus;

import java.util.Objects;

public class EventStatus {

    private int id;
    private String eventStatus;


    public EventStatus() {
    }

    public EventStatus(int id, String eventStatus) {
        this.id = id;
        this.eventStatus = eventStatus;
    }

    public EventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventStatus that = (EventStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(eventStatus, that.eventStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventStatus);
    }
}
