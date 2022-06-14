package com.event.equipment.dao;

import com.event.equipment.models.EquipmentCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends CrudRepository<EquipmentModel, UUID> {
    Iterable<EquipmentCategory> findAllById(UUID fromString);
}
