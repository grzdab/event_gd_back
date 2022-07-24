package com.event.equipmentCategory;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategory {

    private Long id;
    private String name;
    private int sortingGroup;
    private String description;

}