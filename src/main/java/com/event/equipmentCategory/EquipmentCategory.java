package com.event.equipmentCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EquipmentCategory {
    private int id;
    private int sortingGroup;
    private String name;
    private String description;
}
