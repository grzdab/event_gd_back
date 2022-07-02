package com.event.equipmentBookingPeriods;

import com.event.equipment.Equipment;
import com.event.equipment.dao.EquipmentModel;
import com.event.equipmentBookingPeriods.dao.EquipmentBookingPeriodsModel;
import com.event.equipmentBookingPeriods.dao.EquipmentBookingPeriodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record EquipmentBookingPeriodsService(EquipmentBookingPeriodsRepository equipmentBookingPeriodsRepository) {

    @Autowired
    public EquipmentBookingPeriodsService(EquipmentBookingPeriodsRepository equipmentBookingPeriodsRepository) {
        this.equipmentBookingPeriodsRepository = equipmentBookingPeriodsRepository;
    }

    public List<EquipmentBookingPeriods> getEquipmentBookingPeriods(EquipmentModel model) {
        //TODO uncomment code below when finish booking periods
//        List<Integer> ids = model.getEquipmentBookingPeriodsId();
//        List<EquipmentBookingPeriods> periods = createListOfEquipmentPeriods(ids);
        return null;
    }

    private List<EquipmentBookingPeriods> createListOfEquipmentPeriods(List<Integer> ids) {
        List<EquipmentBookingPeriods> periods = new ArrayList<>();
        for (Integer id : ids) {
            EquipmentBookingPeriodsModel periodsModel = equipmentBookingPeriodsRepository.getById(id);
            EquipmentBookingPeriods createdPeriods = createBookingPeriods(periodsModel);
            periods.add(createdPeriods);
        }
        return periods;
    }

    private EquipmentBookingPeriods createBookingPeriods(EquipmentBookingPeriodsModel periodsModel) {
        return new EquipmentBookingPeriods();
    }

    public List<Integer> createListOfPeriodsIds(Equipment equipment) {
        List<Integer> ids = new ArrayList<>();
        List<EquipmentBookingPeriods> periods = equipment.getEquipmentBookingPeriods();
        for (EquipmentBookingPeriods period : periods) {
            ids.add(period.getId());
        }
        return ids;
    }
}
