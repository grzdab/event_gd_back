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
        EquipmentData equipmentData = equipmentDataService.addEquipmentData(equipment.getEquipmentData());
        EquipmentModel equipmentModel = new EquipmentModel(equipment.getSortingId(), equipment.getName(),
                equipment.getNotes(), equipmentData.getId(), equipment.getCategory().getId(),
                createListOfPhotoId(equipment), equipment.isInUse());
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
        //TODO to finish
        EquipmentCategory equipmentCategory = equipmentCategoryService.getEquipmentCategory(equipmentFromDb.getEquipmentCategoryId());
        EquipmentData equipmentData = equipmentDataService.getEquipmentData(String.valueOf(equipmentFromDb.getEquipmentDataId()));
        //List<EquipmentPhoto> equipmentPhoto = equipmentPhotoService.getEquipmentPhoto(String.valueOf(equipmentFromDb.getEquipmentPhotoId()));
        return new Equipment(equipmentFromDb.getId(), equipmentFromDb.getSortingId(),
                equipmentFromDb.getName(), equipmentCategory,
                equipmentFromDb.getNotes(), equipmentData,
                new ArrayList<>(), new EquipmentStatus(UUID.randomUUID()), 1,
                new ArrayList<>(), equipmentFromDb.isInUse());
    }

    private List<Integer> createListOfPhotoId(Equipment equipment) {
        List<EquipmentPhoto> photos = equipment.getPhotos();
        List<Integer> photoIds = new ArrayList<>();
        for (EquipmentPhoto photo : photos) {
            photoIds.add(photo.getId());
        }
        return photoIds;
    }
}
