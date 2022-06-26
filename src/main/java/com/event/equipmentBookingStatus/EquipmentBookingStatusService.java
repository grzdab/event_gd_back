package com.event.equipmentBookingStatus;

import com.event.equipment.dao.EquipmentModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusRepository;
import com.event.equipmentPhoto.dao.EquipmentPhotoRepository;
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

    public List<EquipmentBookingStatus> getEquipmentBookingStatuses(EquipmentModel model) {
        List<Integer> ids = model.getEquipmentBookingStatusId();
        return createListOfEquipmentStatuses(ids);
    }

    private EquipmentBookingStatus createEquipmentStatus(EquipmentBookingStatusModel model) {
        return new EquipmentBookingStatus(model.getId(), model.getBookingStatus());
    }

    private List<EquipmentBookingStatus> createListOfEquipmentStatuses(List<Integer> ids) {
        List<EquipmentBookingStatus> bookingStatuses = new ArrayList<>();
        for (Integer id : ids) {
            EquipmentBookingStatus eqStatus = createEquipmentStatus(equipmentBookingStatusRepository.findById(id).get());
            bookingStatuses.add(eqStatus);
        }
        return bookingStatuses;
    }
}
