package com.event.equipmentCategory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategoryModel, Integer> {

    EquipmentCategoryModel findByName(String name);

}