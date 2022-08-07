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

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String description;


    public EquipmentOwnershipModel() {
    }

    public EquipmentOwnershipModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public EquipmentOwnershipModel(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
