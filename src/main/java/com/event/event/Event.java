package com.event.event;

import com.event.user.MiniUser;
import com.event.equipment.Equipment;
import com.event.eventStatus.EventStatus;
import com.event.logistic.Logistic;
import com.event.representative.Representative;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Event {

    private UUID id;
    private String name;
    private MiniUser user;
    private Representative representative;
    private LocalDateTime creationDate;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private String eventPlace;
    private String notes;
    private EventStatus eventStatus;
    private List<Equipment> equipment;
    private Logistic logistic;

    public Event() {
    }

    public Event(UUID id, String name, MiniUser user, Representative representative, LocalDateTime creationDate, LocalDateTime eventStart, LocalDateTime eventEnd, String eventPlace, String notes, EventStatus eventStatus, List<Equipment> equipment, Logistic logistic) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.representative = representative;
        this.creationDate = creationDate;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eventPlace = eventPlace;
        this.notes = notes;
        this.eventStatus = eventStatus;
        this.equipment = equipment;
        this.logistic = logistic;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MiniUser getUser() {
        return user;
    }

    public void setUser(MiniUser user) {
        this.user = user;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalDateTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalDateTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Logistic getLogistic() {
        return logistic;
    }

    public void setLogistic(Logistic logistic) {
        this.logistic = logistic;
    }
}
