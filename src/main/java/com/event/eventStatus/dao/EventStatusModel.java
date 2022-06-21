package com.event.eventStatus.dao;

import io.swagger.models.auth.In;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class EventStatusModel {

    @Id
    Integer id;

    @NotEmpty
    String eventStatus;

    public EventStatusModel() {
    }

    public EventStatusModel(Integer id, String eventStatus) {
        this.id = id;
        this.eventStatus = eventStatus;
    }

    public EventStatusModel(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        EventStatusModel that = (EventStatusModel) o;
        return Objects.equals(id, that.id) && Objects.equals(eventStatus, that.eventStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventStatus);
    }
}
