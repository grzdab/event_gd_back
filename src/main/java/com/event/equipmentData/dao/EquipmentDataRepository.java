package com.event.equipmentData.dao;

import com.event.equipment.EquipmentController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDataRepository extends JpaRepository<EquipmentDataModel, Integer> {
}
