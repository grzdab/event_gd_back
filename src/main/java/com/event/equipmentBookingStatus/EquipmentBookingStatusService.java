package com.event.equipmentBookingStatus;

import com.event.equipment.Equipment;
import com.event.equipment.dao.EquipmentModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record EquipmentBookingStatusService(EquipmentBookingStatusRepository equipmentBookingStatusRepository) {

    @Autowired
    public EquipmentBookingStatusService(EquipmentBookingStatusRepository equipmentBookingStatusRepository) {
        this.equipmentBookingStatusRepository = equipmentBookingStatusRepository;
    }

    public EquipmentBookingStatus getEquipmentBookingStatusById(int id) {
        EquipmentBookingStatusModel model = equipmentBookingStatusRepository.findById(id).get();
        return createEquipmentBookingStatus(model);
    }

    public List<EquipmentBookingStatus> getEquipmentBookingStatus(EquipmentModel model) {
        List<EquipmentBookingStatusModel> bookingStatusModels = createListOfEquipmentBookingStatusModel(model);
        return createListOfBookingStatus(bookingStatusModels);
    }

    private List<EquipmentBookingStatusModel> createListOfEquipmentBookingStatusModel(EquipmentModel model) {
        List<EquipmentBookingStatusModel> bookingStatusModels = new ArrayList<>();
        List<Integer> ids = model.getEquipmentBookingStatusId();
        for (int id : ids) {
            bookingStatusModels.add(equipmentBookingStatusRepository.findById(id).orElseThrow());
        }
        return bookingStatusModels;
    }

    private List<EquipmentBookingStatus> createListOfBookingStatus(List<EquipmentBookingStatusModel> bookingStatusModel) {
        List<EquipmentBookingStatus> bookingStatuses = new ArrayList<>();
        for (EquipmentBookingStatusModel bookinSstatus : bookingStatusModel) {
            bookingStatuses.add(createEquipmentBookingStatus(bookinSstatus));
        }
        return bookingStatuses;
    }

    private EquipmentBookingStatus createEquipmentBookingStatus(EquipmentBookingStatusModel model) {
        return new EquipmentBookingStatus(model.getId(), model.getBookingStatus());
    }

    public List<Integer> getEquipmentBookingStatusId(Equipment equipment) {
        List<EquipmentBookingStatus> bookingStatus = equipment.getBookingStatus();
        return createListOfId(bookingStatus);
    }

    private List<Integer> createListOfId(List<EquipmentBookingStatus> statuses) {
        List<Integer> ids = new ArrayList<>();
        for (EquipmentBookingStatus status : statuses) {
            ids.add(status.getId());
        }
        return ids;
    }
}
