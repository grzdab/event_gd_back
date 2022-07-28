package com.event.equipmentCategory;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategory {

    private int id;
    private int sortingGroup;
    private String name;
    private String description;

}