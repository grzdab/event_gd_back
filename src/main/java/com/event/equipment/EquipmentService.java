package com.event.equipment;

import com.event.equipment.dao.EquipmentModel;
import com.event.equipment.dao.EquipmentRepository;
import com.event.equipment.models.EquipmentCategory;
import com.event.equipment.models.EquipmentData;
import com.event.equipment.models.EquipmentStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    private  EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment addEquipment(Equipment equipment) {
        //to finish
        EquipmentModel equipmentModel = new EquipmentModel(0, equipment.getName(), equipment.getNotes(), equipment.getEquipmentData().getWidth(), equipment.getEquipmentData().getLength(), equipment.getEquipmentData().getHeight(), equipment.getEquipmentData().getWeight(), equipment.getEquipmentData().getPowerRequired(), equipment.getEquipmentData().getStaffNeeded(), equipment.getEquipmentData().getMinimumAge(), equipment.getEquipmentData().getMaxParticipants(), 0, true);
        equipmentRepository.save(equipmentModel);
        equipment.setId(equipmentModel.getId());
        return equipment;
    }

    public Equipment getEquipmentById(String id) {
        EquipmentModel equipmentFromDb = equipmentRepository.findById(UUID.fromString(id)).orElseThrow();
        return createEquipment(equipmentFromDb);
    }

    public Equipment uploadEquipment(String id, Equipment equipment) {
        EquipmentModel toUpdate = equipmentRepository.findById(UUID.fromString(id)).orElseThrow();
        toUpdate.setSortingId(equipment.getSortingId());
        toUpdate.setName(equipment.getName());
        toUpdate.setNotes(equipment.getNotes());
        toUpdate.setWidth(equipment.getEquipmentData().getWidth());
        toUpdate.setLength(equipment.getEquipmentData().getLength());
        toUpdate.setHeight(equipment.getEquipmentData().getHeight());
        toUpdate.setWeight(equipment.getEquipmentData().getWeight());
        toUpdate.setPowerRequired(equipment.getEquipmentData().getPowerRequired());
        toUpdate.setStaffNeeded(equipment.getEquipmentData().getStaffNeeded());
        toUpdate.setMinimumAge(equipment.getEquipmentData().getMinimumAge());
        toUpdate.setMaxParticipants(equipment.getEquipmentData().getMaxParticipants());
        toUpdate.setEquipmentCategoryId(0);
        toUpdate.setInUse(true);

        equipmentRepository.save(toUpdate);
        return equipment;
    }

    public String deleteEquipment(String id) {
        equipmentRepository.deleteById(UUID.fromString(id));
        return "DELETED";
    }

    public List<Equipment> getAllEquipment() {
        Iterable<EquipmentModel> allEquipment = equipmentRepository.findAll();
        List<Equipment> equipment = new ArrayList<>();
        allEquipment.forEach(model -> equipment.add(createEquipment(model)));
        return equipment;
    }

    public List<EquipmentCategory> getAllCategories(String id) {//btw to jest w ogule potrzebne?
        //TODO pobrać equipment i z niego pobrać wszystkie kategorie
        return null;
    }

    public EquipmentCategory getCategory(String id) {
        EquipmentModel equipmentModel = equipmentRepository.findById(UUID.fromString(id)).orElseThrow();
        return null;
        //EquipmentCategory category = new EquipmentCategory()
    }

    private EquipmentCategory getCategoryById(String id) {
        EquipmentModel categoreey = equipmentRepository.findById(UUID.fromString(id)).orElseThrow();
        EquipmentCategory category = equipmentRepository.findById()categoreey.getEquipmentCategoryId()
    }

    private Equipment createEquipment(EquipmentModel equipmentFromDb) {
        // to finish
        return new Equipment(equipmentFromDb.getId(), equipmentFromDb.getSortingId(),
                equipmentFromDb.getName(), new EquipmentCategory(),
                equipmentFromDb.getNotes(), new EquipmentData(),
                new ArrayList<>(), new EquipmentStatus(UUID.randomUUID()), 1,
                new ArrayList<>());
    }
}
