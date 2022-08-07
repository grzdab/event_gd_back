package com.event.equipmentBookingStatus.dao;
import javax.persistence.*;

@Entity
@Table(name="equipment_booking_status_model")
public class EquipmentBookingStatusModel {

    @Id
    @GeneratedValue(generator = "equipment_booking_status", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_booking_status", sequenceName = "equipment_booking_status", allocationSize=1)
    @Column
    private int id;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String description;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String color;

    public EquipmentBookingStatusModel(Integer id, String name, String description, String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public EquipmentBookingStatusModel() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String bookingStatus) {
        this.name = bookingStatus;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
