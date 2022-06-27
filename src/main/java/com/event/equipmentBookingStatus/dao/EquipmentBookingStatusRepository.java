package com.event.equipmentBookingStatus.dao;

import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentBookingStatusRepository extends JpaRepository<EquipmentBookingStatusModel, Integer> {
}
