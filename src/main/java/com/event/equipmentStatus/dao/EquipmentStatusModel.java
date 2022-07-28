package com.event.equipmentStatus.dao;
import javax.persistence.*;

@Entity
@Table(name="equipment_status_model")
public class EquipmentStatusModel {

    @Id
    @GeneratedValue(generator = "equipment_status", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_status", sequenceName = "equipment_status",allocationSize=1)
    @Column
    private int id;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    public EquipmentStatusModel() {
    }

    public EquipmentStatusModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
