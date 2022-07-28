package com.event.equipmentOwnership.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentOwnershipRepository extends JpaRepository<EquipmentOwnershipModel, Integer> {
    EquipmentOwnershipModel findByName(String name);
}
