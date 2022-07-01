package com.event.equipmentBookingPeriods.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentBookingPeriodsModel {

    @Id
    @GeneratedValue(generator = "equipment_booking_periods", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_booking_periods", sequenceName = "equipment_periods",allocationSize=1)
    @Column
    private Integer id;
}
