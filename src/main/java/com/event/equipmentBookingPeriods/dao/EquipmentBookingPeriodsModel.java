package com.event.equipmentBookingPeriods.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="equipment_booking_periods_model")
public class EquipmentBookingPeriodsModel {

    @Id
    @GeneratedValue(generator = "equipment_booking_periods", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "equipment_booking_periods", sequenceName = "equipment_booking_periods",allocationSize=1)
    @Column
    private Integer id;
    private LocalDateTime bookingStart;
    private LocalDateTime bookingEnd;
}
