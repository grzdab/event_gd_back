package com.event.equipmentBookingStatus.dao;

import javax.persistence.*;

@Entity
@Table(name = "equipment_booking_status_model")
public class EquipmentBookingStatusModel {

    @Id
    @GeneratedValue(generator = "equipment_booking_status", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_booking_status", sequenceName = "equipment_booking_status",allocationSize=1)
    @Column
    private int id;

    @Column(columnDefinition = "varchar default 'unnamed'")
    private String name;

    public EquipmentBookingStatusModel(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public EquipmentBookingStatusModel() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String bookingStatus) {
        this.name = name;
    }
}
