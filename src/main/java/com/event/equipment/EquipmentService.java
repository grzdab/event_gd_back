package com.event.equipment;

import com.event.equipment.dao.EquipmentModel;
import com.event.equipment.dao.EquipmentRepository;
import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentBookingStatus.EquipmentBookingStatusService;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentCategory.EquipmentCategoryService;
import com.event.equipmentData.EquipmentData;
import com.event.equipmentData.EquipmentDataService;
import com.event.equipmentPhoto.EquipmentPhoto;
import com.event.equipmentPhoto.EquipmentPhotoService;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.EquipmentStatusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentCategoryService equipmentCategoryService;
    private final EquipmentDataService equipmentDataService;
    private final EquipmentPhotoService equipmentPhotoService;
    private final EquipmentStatusService equipmentStatusService;
    private final EquipmentBookingStatusService equipmentBookingStatusService;

    private  EquipmentService(EquipmentRepository equipmentRepository, EquipmentCategoryService equipmentCategoryService, EquipmentDataService equipmentDataService, EquipmentPhotoService equipmentPhotoService, EquipmentStatusService equipmentStatusService, EquipmentBookingStatusService equipmentBookingStatusService) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentCategoryService = equipmentCategoryService;
        this.equipmentDataService = equipmentDataService;
        this.equipmentPhotoService = equipmentPhotoService;
        this.equipmentStatusService = equipmentStatusService;
        this.equipmentBookingStatusService = equipmentBookingStatusService;
    }

    public Equipment addEquipment(Equipment equipment) {
        //TODO finish
        EquipmentData equipmentData = equipmentDataService.addEquipmentData(equipment.getEquipmentData());
        EquipmentModel equipmentModel = new EquipmentModel(equipment.getSortingId(), equipment.getName(),
                    equipment.getNotes(), equipmentData.getId(), equipment.getCategory().getId(),
                    equipmentPhotoService.createListOfPhotoId(equipment.getPhotos()),
                    //what is equipment booking periods
                    equipment.getStatus().getId(), new ArrayList<>(), equipment.isInUse());
        equipmentRepository.save(equipmentModel);
        equipment.setId(equipmentModel.getId());
        return equipment;
    }

    public Equipment getEquipmentById(int id) {
        EquipmentModel equipmentFromDb = equipmentRepository.findById(id).orElseThrow();
        return createEquipment(equipmentFromDb);
    }

    public Equipment uploadEquipment(int id, Equipment equipment) {
        //TODO to finish
        EquipmentModel toUpdate = equipmentRepository.findById(id).orElseThrow();
        toUpdate.setSortingId(equipment.getSortingId());
        toUpdate.setName(equipment.getName());
        toUpdate.setNotes(equipment.getNotes());
        toUpdate.setEquipmentCategoryId(equipment.getCategory().getId());
        toUpdate.setInUse(equipment.isInUse());

        equipmentRepository.save(toUpdate);
        return equipment;
    }

    public String deleteEquipment(int id) {
        //TODO to finish when finish other parts of this
        EquipmentModel model = equipmentRepository.findById(id).get();
        int equipmentDataId = model.getEquipmentDataId();
        int equipmentCategoryId = model.getEquipmentCategoryId();
        int equipmentSortingId = model.getSortingId();
        int equipmentStatusId = model.getEquipmentStatusId();
        equipmentRepository.deleteById(id);
        equipmentRepository.deleteById(equipmentDataId);
        equipmentRepository.deleteById(equipmentCategoryId);
        equipmentRepository.deleteById(equipmentSortingId);
        equipmentRepository.deleteById(equipmentStatusId);
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
        List<EquipmentPhoto> equipmentPhotos = equipmentPhotoService.createListOfEquipmentPhoto(equipmentFromDb);
        List<EquipmentBookingStatus> bookingStatuses = equipmentBookingStatusService.getEquipmentBookingStatuses(equipmentFromDb);
        return new Equipment(equipmentFromDb.getId(), equipmentFromDb.getSortingId(),
                equipmentFromDb.getName(), equipmentCategory,
                equipmentFromDb.getNotes(), equipmentData,
                equipmentPhotos, equipmentStatusService.getEquipmentStatus(equipmentFromDb.getEquipmentStatusId()),
                1, bookingStatuses, equipmentFromDb.isInUse());
    }
}
