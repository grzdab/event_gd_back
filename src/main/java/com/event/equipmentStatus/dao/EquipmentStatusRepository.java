package com.event.equipmentStatus.dao;

import com.event.equipmentCategory.dao.EquipmentCategoryModel;
import com.event.equipmentStatus.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentStatusRepository extends JpaRepository<EquipmentStatusModel, Integer> {
    EquipmentStatusModel findByName(String name);
}
