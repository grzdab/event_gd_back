package com.event.equipment;

import com.event.equipment.dao.EquipmentModel;
import com.event.equipment.dao.EquipmentRepository;
import com.event.equipmentBookingPeriods.EquipmentBookingPeriods;
import com.event.equipmentBookingPeriods.EquipmentBookingPeriodsService;
import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentBookingStatus.EquipmentBookingStatusService;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusRepository;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentCategory.EquipmentCategoryService;
import com.event.equipmentCategory.dao.EquipmentCategoryRepository;
import com.event.equipmentData.EquipmentData;
import com.event.equipmentData.EquipmentDataService;
import com.event.equipmentData.dao.EquipmentDataRepository;
import com.event.photoPath.PhotoPath;
import com.event.photoPath.PhotoPathService;
import com.event.photoPath.dao.PhotoPathRepository;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.EquipmentStatusService;
import com.event.equipmentStatus.dao.EquipmentStatusRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentDataRepository equipmentDataRepository;
    private final EquipmentCategoryRepository equipmentCategoryRepository;
    private final PhotoPathRepository photoPathRepository;
    private final EquipmentStatusRepository equipmentStatusRepository;
    private final EquipmentBookingStatusRepository equipmentBookingStatusRepository;
    private final EquipmentCategoryService equipmentCategoryService;
    private final EquipmentDataService equipmentDataService;
    private final PhotoPathService photoPathService;
    private final EquipmentStatusService equipmentStatusService;
    private final EquipmentBookingStatusService equipmentBookingStatusService;
    private final EquipmentBookingPeriodsService equipmentBookingPeriodsService;

    private  EquipmentService(EquipmentRepository equipmentRepository, EquipmentDataRepository equipmentDataRepository, EquipmentCategoryRepository equipmentCategoryRepository, PhotoPathRepository photoPathRepository, EquipmentStatusRepository equipmentStatusRepository, EquipmentBookingStatusRepository equipmentBookingStatusRepository, EquipmentCategoryService equipmentCategoryService, EquipmentDataService equipmentDataService, PhotoPathService photoPathService, EquipmentStatusService equipmentStatusService, EquipmentBookingStatusService equipmentBookingStatusService, EquipmentBookingPeriodsService equipmentBookingPeriodsService) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentDataRepository = equipmentDataRepository;
        this.equipmentCategoryRepository = equipmentCategoryRepository;
        this.photoPathRepository = photoPathRepository;
        this.equipmentStatusRepository = equipmentStatusRepository;
        this.equipmentBookingStatusRepository = equipmentBookingStatusRepository;
        this.equipmentCategoryService = equipmentCategoryService;
        this.equipmentDataService = equipmentDataService;
        this.photoPathService = photoPathService;
        this.equipmentStatusService = equipmentStatusService;
        this.equipmentBookingStatusService = equipmentBookingStatusService;
        this.equipmentBookingPeriodsService = equipmentBookingPeriodsService;
    }

    public Equipment addEquipment(Equipment equipment) {
        //TODO finish
        EquipmentData equipmentData = null;
        if (equipment.getEquipmentData().getEquipmentId() != 0) {
            equipmentData = equipmentDataService.addEquipmentData(equipment.getEquipmentData());
        }
        List<Integer> periodIds = equipmentBookingPeriodsService.createListOfPeriodsIds(equipment);
        EquipmentModel equipmentModel = new EquipmentModel(
                equipment.getId(),
                equipment.getSortingId(),
                equipment.getName(),
                    equipment.getNotes(),
                (equipmentData != null? equipmentData.getId():0),
                equipment.getCategory().getId(),
                    photoPathService.createListOfPhotoId(equipment.getPhotos()),
                    equipment.getStatus().getId(),
                equipmentBookingStatusService.getEquipmentBookingStatusId(equipment),
                periodIds,
                equipment.isInUse());
        equipmentRepository.save(equipmentModel);
        equipment.setId(equipmentModel.getId());
        return equipment;
    }

    public Equipment getEquipmentById(int id) {
        try {
            EquipmentModel equipmentFromDb = equipmentRepository.findById(id).orElseThrow();
            return createEquipment(equipmentFromDb);
        } catch (Exception e) {
            return null;
        }
    }

    public EquipmentModel getEquipmentModelById(int id) {
        try {
            EquipmentModel equipmentModel = equipmentRepository.findById(id).get();
            return equipmentModel;
        } catch (Exception e) {
            return null;
        }
    }

    public Equipment uploadEquipment(int id, Equipment equipment) {
        //TODO to finish
        EquipmentModel toUpdate = equipmentRepository.findById(id).orElse(null);
        if (toUpdate == null) return null;
        toUpdate.setSortingId(equipment.getSortingId());
        toUpdate.setName(equipment.getName());
        toUpdate.setNotes(equipment.getNotes());
        toUpdate.setEquipmentDataId(equipment.getEquipmentData().getId());
        toUpdate.setEquipmentCategoryId(equipment.getCategory().getId());
        //photoId
        toUpdate.setEquipmentStatusId(equipment.getStatus().getId());
        //TODO deleting equipmentBookingPeriods when done in equipmentModel
        toUpdate.setInUse(equipment.isInUse());

        equipmentRepository.save(toUpdate);
        return equipment;
    }

    public String deleteEquipment(int id) {
        //TODO to finish when finish other parts of equipment service
        EquipmentModel model = equipmentRepository.findById(id).get();
        int equipmentDataId = model.getEquipmentDataId();
        int equipmentCategoryId = model.getEquipmentCategoryId();
        List<Integer> equipmentPhotoId = model.getEquipmentPhotoId();
        int equipmentStatusId = model.getEquipmentStatusId();
        Integer equipmentBookingStatusId = model.getEquipmentBookingStatusId();
        //TODO add deleting of periods when finish it in the other pars of app
        equipmentRepository.deleteById(id);
        equipmentDataRepository.deleteById(equipmentDataId);
        equipmentCategoryRepository.deleteById(equipmentCategoryId);
        deletePhotoByListOfIds(equipmentPhotoId);
        equipmentRepository.deleteById(equipmentStatusId);
        deleteBookingStatusById(equipmentBookingStatusId);
        //TODO add deleting of periods when finish it in the other pars of app
        return "DELETED";
    }

    private void deletePhotoByListOfIds(List<Integer> ids) {
        for (int id : ids) {
            photoPathRepository.deleteById(id);
        }
    }

    private void deleteBookingStatusById(Integer id) {
        equipmentBookingStatusRepository.deleteById(id);
    }

    public List<Equipment> getAllEquipment() {
        Iterable<EquipmentModel> allEquipment = equipmentRepository.findAll();
        List<Equipment> equipment = new ArrayList<>();
        allEquipment.forEach(model -> equipment.add(createEquipment(model)));
        return equipment;
    }

    private Equipment createEquipment(EquipmentModel equipmentFromDb) {
        //TODO finish booking periods as we already agree
        EquipmentCategory equipmentCategory = equipmentCategoryService.getEquipmentCategoryById(equipmentFromDb.getEquipmentCategoryId());
        EquipmentData equipmentData = equipmentDataService.getEquipmentData(equipmentFromDb.getEquipmentDataId());
        List<PhotoPath> equipmentPhotos = photoPathService.createListOfEquipmentPhotoPaths(equipmentFromDb);
        List<EquipmentBookingPeriods> periods = equipmentBookingPeriodsService.getEquipmentBookingPeriods(equipmentFromDb);
        EquipmentStatus status = equipmentStatusService.getEquipmentStatus(equipmentFromDb.getEquipmentStatusId());
        EquipmentBookingStatus equipmentBookingStatus = equipmentBookingStatusService.getEquipmentBookingStatus(equipmentFromDb);
        return new Equipment(
                equipmentFromDb.getId(),
                equipmentFromDb.getSortingId(),
                equipmentFromDb.getName(),
                equipmentCategory,
                equipmentFromDb.getNotes(),
                equipmentData,
                equipmentPhotos,
                status,
                equipmentBookingStatus,
                // do not write here periods because it's not ready yet
                new ArrayList<>(),
                equipmentFromDb.isInUse());
    }
}
