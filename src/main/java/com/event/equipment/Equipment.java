package com.event.equipment;

import com.event.equipmentBookingPeriods.EquipmentBookingPeriods;
import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentData.EquipmentData;
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
    private EquipmentCategory category;
    private String notes;
    private EquipmentData equipmentData;
    private List<EquipmentPhoto> photos;
    private EquipmentStatus status;
    private List<EquipmentBookingStatus> bookingStatus;
    private List<EquipmentBookingPeriods> equipmentBookingPeriods;
    private boolean inUse;
}
