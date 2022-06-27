package com.event.equipmentCategory.DAO;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="equipment_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCategoryModel {

    @Id
    @SequenceGenerator(
        name = "equipment_category_sequence",
        sequenceName = "equipment_category_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "equipment_category_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;

}
