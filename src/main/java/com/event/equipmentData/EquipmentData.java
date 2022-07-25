package com.event.equipmentData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EquipmentData {
    private int id;
    private int equipmentId;
    private int width;
    private int length;
    private int height;
    private int weight;
    private int powerRequired;
    private int staffNeeded;
    private int minimumAge;
    private int maxParticipants;

    public EquipmentData(int equipmentId, int width, int length, int height, int weight, int powerRequired, int staffNeeded, int minimumAge, int maxParticipants) {
        this.equipmentId = equipmentId;
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.powerRequired = powerRequired;
        this.staffNeeded = staffNeeded;
        this.minimumAge = minimumAge;
        this.maxParticipants = maxParticipants;
    }
}
