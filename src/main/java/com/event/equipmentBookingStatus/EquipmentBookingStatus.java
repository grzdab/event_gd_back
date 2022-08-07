package com.event.equipmentBookingStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentBookingStatus {
    private int id;
    private String name;
    private String description;
    private String color;
}
