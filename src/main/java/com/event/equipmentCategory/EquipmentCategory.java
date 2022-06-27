package com.event.equipmentCategory;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategory {

    private Long id;
    private String name;
    private String description;

}
