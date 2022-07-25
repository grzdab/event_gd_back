package com.event.equipment.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends CrudRepository<EquipmentModel, Integer> {
    List<EquipmentModel> findAllByEquipmentCategoryId(Integer id);
}
