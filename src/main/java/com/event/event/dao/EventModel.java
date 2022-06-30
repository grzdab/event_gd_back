package com.event.event.dao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class EventModel {

    private UUID id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private LocalDateTime eventStartDate;
    @Column(nullable = false)
    private LocalDateTime eventEndDate;
    @Column(nullable = false, length = 100)
    private String eventPlace;
    private String description;
    private int eventStatusId;
    private int logisticsId;
    private String userId;
    private int representativeId;
    private List<Integer> equipments = new ArrayList<>();

    public EventModel() {
    }

    public EventModel(UUID id, String name, LocalDateTime creationDate, LocalDateTime eventStartDate, LocalDateTime eventEndDate, String eventPlace, String description, int eventStatusId, int logisticsId, String userId, int representativeId) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventPlace = eventPlace;
        this.description = description;
        this.eventStatusId = eventStatusId;
        this.logisticsId = logisticsId;
        this.userId = userId;
        this.representativeId = representativeId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(LocalDateTime eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public LocalDateTime getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(LocalDateTime eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEventStatusId() {
        return eventStatusId;
    }

    public void setEventStatusId(int eventStatusId) {
        this.eventStatusId = eventStatusId;
    }

    public int getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(int logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(int representativeId) {
        this.representativeId = representativeId;
    }

    @ElementCollection
    public List<Integer> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Integer> equipments) {
        this.equipments = equipments;
    }
}
