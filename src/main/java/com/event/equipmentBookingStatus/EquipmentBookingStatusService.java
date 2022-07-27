package com.event.equipmentBookingStatus;

import com.event.equipment.Equipment;
import com.event.equipment.dao.EquipmentModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusRepository;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.dao.EquipmentStatusModel;
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
        try {
            EquipmentBookingStatusModel model = equipmentBookingStatusRepository.findById(id).get();
            return createEquipmentBookingStatus(model);
        } catch (Exception e) {
            return null;
        }
    }

    public EquipmentBookingStatus getEquipmentBookingStatus(EquipmentModel model) {
        if (model == null) return null;
        EquipmentBookingStatusModel bookingStatusModel = createEquipmentBookingStatusModel(model);
        return createEquipmentBookingStatus(bookingStatusModel);
    }

    private EquipmentBookingStatusModel createEquipmentBookingStatusModel(EquipmentModel model) {
            EquipmentBookingStatusModel bookingStatusModel;
            Integer bookingStatusId = model.getEquipmentBookingStatusId();
            if (bookingStatusId == 0) return null;
            bookingStatusModel = equipmentBookingStatusRepository.findById(bookingStatusId).orElseThrow();
        return bookingStatusModel;
    }

    private EquipmentBookingStatus createEquipmentBookingStatus(EquipmentBookingStatusModel model) {
        if (model == null) return null;
        return new EquipmentBookingStatus(model.getId(), model.getName());
    }

    public Integer getEquipmentBookingStatusId(Equipment equipment) {
        if (equipment.getBookingStatus() == null) return null;
        EquipmentBookingStatus bookingStatus = equipment.getBookingStatus();
        return bookingStatus.getId();
    }

    public EquipmentBookingStatus addEquipmentBookingStatus(EquipmentBookingStatus bookingStatus) {
        if (getEquipmentBookingStatusByName(bookingStatus.getName()) == null) {
            EquipmentBookingStatusModel model = new EquipmentBookingStatusModel(
                    bookingStatus.getId(),
                    bookingStatus.getName()
            );
            equipmentBookingStatusRepository.save(model);
            bookingStatus.setId(model.getId());
            return bookingStatus;
        }
        return null;
    }

    //TODO error while more than one equipmentBookingStatus
    public EquipmentBookingStatus getEquipmentBookingStatusByName(String name) {
        EquipmentBookingStatusModel model = equipmentBookingStatusRepository.findByName(name);
        if (model != null) {
            return new EquipmentBookingStatus(model.getId(), model.getName());
        }
        return null;
    }


    //TODO delete propably with List
    public String deleteEquipmentBookingStatus(Integer id) {
        return "null";
    }
}
