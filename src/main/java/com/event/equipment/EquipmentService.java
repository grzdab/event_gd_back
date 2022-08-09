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
import com.event.equipmentData.EquipmentDataService;
import com.event.equipmentData.dao.EquipmentDataRepository;
import com.event.equipmentOwnership.EquipmentOwnership;
import com.event.equipmentOwnership.EquipmentOwnershipService;
import com.event.equipmentPhoto.EquipmentPhoto;
import com.event.equipmentPhoto.EquipmentPhotoService;
import com.event.equipmentPhoto.dao.EquipmentPhotoRepository;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.EquipmentStatusService;
import com.event.equipmentStatus.dao.EquipmentStatusRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentDataRepository equipmentDataRepository;
    private final EquipmentCategoryRepository equipmentCategoryRepository;
    private final EquipmentPhotoRepository equipmentPhotoRepository;
    private final EquipmentStatusRepository equipmentStatusRepository;
    private final EquipmentBookingStatusRepository equipmentBookingStatusRepository;
    private final EquipmentCategoryService equipmentCategoryService;
    private final EquipmentDataService equipmentDataService;
    private final EquipmentPhotoService equipmentPhotoService;
    private final EquipmentStatusService equipmentStatusService;
    private final EquipmentBookingStatusService equipmentBookingStatusService;
    private final EquipmentBookingPeriodsService equipmentBookingPeriodsService;
    private final EquipmentOwnershipService equipmentOwnershipService;

    private  EquipmentService(
        EquipmentRepository equipmentRepository,
        EquipmentDataRepository equipmentDataRepository,
        EquipmentCategoryRepository equipmentCategoryRepository,
        EquipmentPhotoRepository equipmentPhotoRepository,
        EquipmentStatusRepository equipmentStatusRepository,
        EquipmentBookingStatusRepository equipmentBookingStatusRepository,
        EquipmentCategoryService equipmentCategoryService,
        EquipmentDataService equipmentDataService,
        EquipmentPhotoService equipmentPhotoService,
        EquipmentStatusService equipmentStatusService,
        EquipmentBookingStatusService equipmentBookingStatusService,
        EquipmentOwnershipService equipmentOwnershipService,
        EquipmentBookingPeriodsService equipmentBookingPeriodsService) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentDataRepository = equipmentDataRepository;
        this.equipmentCategoryRepository = equipmentCategoryRepository;
        this.equipmentPhotoRepository = equipmentPhotoRepository;
        this.equipmentStatusRepository = equipmentStatusRepository;
        this.equipmentBookingStatusRepository = equipmentBookingStatusRepository;
        this.equipmentCategoryService = equipmentCategoryService;
        this.equipmentDataService = equipmentDataService;
        this.equipmentPhotoService = equipmentPhotoService;
        this.equipmentStatusService = equipmentStatusService;
        this.equipmentBookingStatusService = equipmentBookingStatusService;
        this.equipmentBookingPeriodsService = equipmentBookingPeriodsService;
        this.equipmentOwnershipService = equipmentOwnershipService;
    }


    public Equipment addEquipment(Equipment equipment) {
//        EquipmentData equipmentData = null;
//        if (equipment.getEquipmentData().getEquipmentId() != 0) {
//            equipmentData = equipmentDataService.addEquipmentData(equipment.getEquipmentData());
//        }
        List<Integer> periodIds = equipmentBookingPeriodsService.createListOfPeriodsIds(equipment);
        EquipmentModel equipmentModel = new EquipmentModel(
            equipment.getId(),
            equipment.getSortingId(),
            equipment.getName(),
            equipment.getNotes(),
            equipment.getEquipmentCategory().getId(),
            ListToString(equipment.getPhotos()),
            equipment.getEquipmentStatus().getId(),
            equipmentBookingStatusService.getEquipmentBookingStatusId(equipment),
            equipment.getEquipmentOwnership().getId(),
            periodIds,
            equipment.isInUse(),
            equipment.getWidth(),
            equipment.getLength(),
            equipment.getHeight(),
            equipment.getWeight(),
            equipment.getPowerRequired(),
            equipment.getStaffNeeded(),
            equipment.getMinimumAge(),
            equipment.getMaxParticipants());
        equipmentRepository.save(equipmentModel);
        equipment.setId(equipmentModel.getId());
        return equipment;
    }

    public Equipment getEquipmentById(int id) {
        EquipmentModel equipmentFromDb = equipmentRepository.findById(id).orElseThrow();
        return createEquipment(equipmentFromDb);
    }

    public Equipment updateEquipment(int id, Equipment equipment) {
        //TODO to finish photos
        EquipmentModel toUpdate = equipmentRepository.findById(id).orElseThrow();
        toUpdate.setSortingId(equipment.getSortingId());
        toUpdate.setName(equipment.getName());
        toUpdate.setNotes(equipment.getNotes());
        toUpdate.setEquipmentCategoryId(equipment.getEquipmentCategory().getId());
        toUpdate.setPhotos(ListToString(equipment.getPhotos()));
        toUpdate.setEquipmentStatusId(equipment.getEquipmentStatus().getId());
        toUpdate.setEquipmentOwnershipId(equipment.getEquipmentOwnership().getId());
        toUpdate.setInUse(equipment.isInUse());
        toUpdate.setWidth(equipment.getWidth());
        toUpdate.setLength(equipment.getLength());
        toUpdate.setHeight(equipment.getHeight());
        toUpdate.setWeight(equipment.getWeight());
        toUpdate.setPowerRequired(equipment.getPowerRequired());
        toUpdate.setStaffNeeded(equipment.getStaffNeeded());
        toUpdate.setMinimumAge(equipment.getMinimumAge());
        toUpdate.setMaxParticipants(equipment.getMaxParticipants());
        equipmentRepository.save(toUpdate);
        return createEquipment(toUpdate);
    }

    public String deleteEquipment(int id) {
        EquipmentModel model = equipmentRepository.findById(id).get();
        equipmentRepository.deleteById(id);
        return "DELETED";
    }

    public List<Equipment> getAllEquipment() {
        Iterable<EquipmentModel> allEquipment = equipmentRepository.findAll();
        List<Equipment> equipment = new ArrayList<>();
        allEquipment.forEach(model -> equipment.add(createEquipment(model)));
        return equipment;
    }

    private Equipment createEquipment(EquipmentModel equipmentModel) {
        //TODO finish booking periods as we already agree
        EquipmentCategory equipmentCategory = equipmentCategoryService.getEquipmentCategoryById(equipmentModel.getEquipmentCategoryId());
//        EquipmentData equipmentData = equipmentDataService.getEquipmentData(String.valueOf(equipmentModel.getEquipmentDataId()));
        List<String> equipmentPhotos = StringToList(equipmentModel.getPhotos());
        List<EquipmentBookingPeriods> periods = equipmentBookingPeriodsService.getEquipmentBookingPeriods(equipmentModel);
        EquipmentStatus status = equipmentStatusService.getEquipmentStatusById(equipmentModel.getEquipmentStatusId());
        EquipmentBookingStatus equipmentBookingStatus = equipmentBookingStatusService.getEquipmentBookingStatus(equipmentModel);
        EquipmentOwnership equipmentOwnership = equipmentOwnershipService.getEquipmentOwnershipById(equipmentModel.getEquipmentOwnershipId());
        return new Equipment(
            equipmentModel.getId(),
            equipmentModel.getSortingId(),
            equipmentModel.getName(),
            equipmentCategory,
            equipmentModel.getNotes(),
//            equipmentData,
            equipmentPhotos,
            status,
            equipmentBookingStatus,
            equipmentOwnership,
            // do not write here periods because it's not ready yet
            new ArrayList<>(),
            equipmentModel.isInUse(),
            equipmentModel.getWidth(),
            equipmentModel.getLength(),
            equipmentModel.getHeight(),
            equipmentModel.getWeight(),
            equipmentModel.getPowerRequired(),
            equipmentModel.getStaffNeeded(),
            equipmentModel.getMinimumAge(),
            equipmentModel.getMaxParticipants());
    }

    public List<Equipment> getEquipmentByCategoryId(int id) {
        Iterable<EquipmentModel> equipmentModels = equipmentRepository.findAllByEquipmentCategoryId(id);
        return getEquipmentByItemId(equipmentModels);
    }


    public List<Equipment> getEquipmentByStatusId(int id) {
        Iterable<EquipmentModel> equipmentModels = equipmentRepository.findAllByEquipmentStatusId(id);
        return getEquipmentByItemId(equipmentModels);
    }

    public List<Equipment> getEquipmentByOwnershipId(int id) {
        Iterable<EquipmentModel> equipmentModels = equipmentRepository.findAllByEquipmentOwnershipId(id);
        return getEquipmentByItemId(equipmentModels);
    }

    public List<Equipment> getEquipmentByBookingStatusId(int id) {
        Iterable<EquipmentModel> equipmentModels = equipmentRepository.findAllByEquipmentBookingStatusId(id);
        return getEquipmentByItemId(equipmentModels);
    }

    private List<Equipment> getEquipmentByItemId(Iterable<EquipmentModel> equipmentModels) {
        List<Equipment> equipment = new ArrayList<>();
        for (EquipmentModel model : equipmentModels) {
            equipment.add(createEquipment(model));
        }
        return equipment;
    }

    private List<String> StringToList(String string) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, string.split(",", -1));
        return list;
    }

    private String ListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(",");
        }
        String string = sb.toString();
        return string.substring(0, string.length() - 1);
    }


}

