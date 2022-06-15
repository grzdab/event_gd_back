package com.event.equipment;

import com.event.equipment.dao.EquipmentModel;
import com.event.equipment.dao.EquipmentRepository;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentCategory.EquipmentCategoryService;
import com.event.equipmentData.EquipmentData;
import com.event.equipmentData.EquipmentDataService;
import com.event.equipmentPhoto.EquipmentPhoto;
import com.event.equipmentPhoto.EquipmentPhotoService;
import com.event.equipmentStatus.EquipmentStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentCategoryService equipmentCategoryService;
    private final EquipmentDataService equipmentDataService;
    private final EquipmentPhotoService equipmentPhotoService;

    private  EquipmentService(EquipmentRepository equipmentRepository, EquipmentCategoryService equipmentCategoryService, EquipmentDataService equipmentDataService, EquipmentPhotoService equipmentPhotoService) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentCategoryService = equipmentCategoryService;
        this.equipmentDataService = equipmentDataService;
        this.equipmentPhotoService = equipmentPhotoService;
    }

    public Equipment addEquipment(Equipment equipment) {
        //to finish
        EquipmentModel equipmentModel = new EquipmentModel(0, equipment.getName(), equipment.getNotes(), 0, 0, 0, true);
        equipmentRepository.save(equipmentModel);
        equipment.setId(equipmentModel.getId());
        return equipment;
    }

    public Equipment getEquipmentById(String id) {
        EquipmentModel equipmentFromDb = equipmentRepository.findById(UUID.fromString(id)).orElseThrow();
        return createEquipment(equipmentFromDb);
    }

    public Equipment uploadEquipment(String id, Equipment equipment) {
        // TODO finish
        EquipmentModel toUpdate = equipmentRepository.findById(UUID.fromString(id)).orElseThrow();
        toUpdate.setSortingId(equipment.getSortingId());
        toUpdate.setName(equipment.getName());
        toUpdate.setNotes(equipment.getNotes());
        toUpdate.setEquipmentCategoryId(0);
        toUpdate.setInUse(true);

        equipmentRepository.save(toUpdate);
        return equipment;
    }

    public String deleteEquipment(String id) {

        //TODO propably to finish
        equipmentRepository.deleteById(UUID.fromString(id));
        return "DELETED";
    }

    public List<Equipment> getAllEquipment() {
        Iterable<EquipmentModel> allEquipment = equipmentRepository.findAll();
        List<Equipment> equipment = new ArrayList<>();
        allEquipment.forEach(model -> equipment.add(createEquipment(model)));
        return equipment;
    }

    private Equipment createEquipment(EquipmentModel equipmentFromDb) {
        // to finish
        EquipmentCategory equipmentCategory = equipmentCategoryService.getEquipmentCategory(equipmentFromDb.getEquipmentCategoryId());
        EquipmentData equipmentData = equipmentDataService.getEquipmentData(String.valueOf(equipmentFromDb.getEquipmentDataId()));
        //List<EquipmentPhoto> equipmentPhoto = equipmentPhotoService.getEquipmentPhoto(String.valueOf(equipmentFromDb.getEquipmentPhotoId()));
        return new Equipment(equipmentFromDb.getId(), equipmentFromDb.getSortingId(),
                equipmentFromDb.getName(), equipmentCategory,
                equipmentFromDb.getNotes(), equipmentData,
                new ArrayList<>(), new EquipmentStatus(UUID.randomUUID()), 1,
                new ArrayList<>());
    }
}
