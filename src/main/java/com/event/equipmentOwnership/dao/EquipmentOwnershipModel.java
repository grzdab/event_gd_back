package com.event.equipmentOwnership.dao;
import javax.persistence.*;

@Entity
@Table(name="equipment_ownership_model")
public class EquipmentOwnershipModel {

    @Id
    @GeneratedValue(generator = "equipment_ownership", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_ownership", sequenceName = "equipment_ownership",allocationSize=1)
    @Column
    private int id;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    public EquipmentOwnershipModel() {
    }

    public EquipmentOwnershipModel(String name) {
        this.name = name;
    }

    public EquipmentOwnershipModel(int id, String name) {
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
