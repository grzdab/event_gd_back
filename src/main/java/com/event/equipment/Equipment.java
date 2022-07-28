package com.event.equipment;

import com.event.equipmentBookingPeriods.EquipmentBookingPeriods;
import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentOwnership.EquipmentOwnership;
import com.event.equipmentPhoto.EquipmentPhoto;
import com.event.equipmentStatus.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private int id;
    private int sortingId;
    private String name;
    private EquipmentCategory equipmentCategory;
    private String notes;
//    private EquipmentData equipmentData;
    private List<String> photos;
    private EquipmentStatus equipmentStatus;
    private EquipmentBookingStatus bookingStatus;
    private EquipmentOwnership equipmentOwnership;
    private List<EquipmentBookingPeriods> equipmentBookingPeriods;
    private boolean inUse;
    private int width;
    private int length;
    private int height;
    private int weight;
    private int powerRequired;
    private int staffNeeded;
    private int minimumAge;
    private int maxParticipants;
}
