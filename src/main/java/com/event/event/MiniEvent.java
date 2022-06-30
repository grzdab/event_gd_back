package com.event.event;

import com.event.eventStatus.EventStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class MiniEvent {

    private UUID id;
    private String userId;
    private String userName;
    private int representativeId;
    private String representativeName;
    private String clientId;
    private String clientName;
    private String clientUserId;
    private String clientUserName;
    private LocalDateTime creationDate;
    private EventStatus eventStatus;
    private int equipmentsAmount;

    public MiniEvent() {
    }

    public MiniEvent(UUID id, String userId, String userName, int representativeId, String representativeName, String clientId, String clientName, String clientUserId, String clientUserName, LocalDateTime creationDate, EventStatus eventStatus, int equipmentsAmount) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.representativeId = representativeId;
        this.representativeName = representativeName;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientUserId = clientUserId;
        this.clientUserName = clientUserName;
        this.creationDate = creationDate;
        this.eventStatus = eventStatus;
        this.equipmentsAmount = equipmentsAmount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRepresentativeId() {
        return representativeId;
    }

    public void setRepresentativeId(int representativeId) {
        this.representativeId = representativeId;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(String clientUserId) {
        this.clientUserId = clientUserId;
    }

    public String getClientUserName() {
        return clientUserName;
    }

    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public int getEquipmentsAmount() {
        return equipmentsAmount;
    }

    public void setEquipmentsAmount(int equipmentsAmount) {
        this.equipmentsAmount = equipmentsAmount;
    }
}
