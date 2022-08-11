package com.event.equipmentBookingStatus;

import com.event.equipment.Equipment;
import com.event.equipment.dao.EquipmentModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusModel;
import com.event.equipmentBookingStatus.dao.EquipmentBookingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public record EquipmentBookingStatusService(EquipmentBookingStatusRepository repository) {

    @Autowired
    public EquipmentBookingStatusService(EquipmentBookingStatusRepository repository) {
        this.repository = repository;
    }

    public EquipmentBookingStatus getEquipmentBookingStatusById(int id) {
        EquipmentBookingStatusModel model = repository.findById(id).get();
        return createEquipmentBookingStatus(model);
    }

    public List<EquipmentBookingStatus> getEquipmentBookingStatuses() {
        Collection<EquipmentBookingStatusModel> models = repository.findAll();
        return models
            .stream()
            .map(model -> new EquipmentBookingStatus(model.getId(), model.getName(), model.getDescription(), model.getColor()))
            .collect(Collectors.toList());
    }

    public EquipmentBookingStatus updateEquipmentBookingStatus(int id, EquipmentBookingStatus equipmentBookingStatus) {
        EquipmentBookingStatusModel model = repository.findById(id).orElseThrow(() -> new IllegalStateException("Could not find equipment booking status with specified ID"));
        model.setName(equipmentBookingStatus.getName());
        model.setDescription(equipmentBookingStatus.getDescription());
        model.setColor(equipmentBookingStatus.getColor());
        repository.save(model);
        return createBookingStatus(model);
    }

    public EquipmentBookingStatus getEquipmentBookingStatus(EquipmentModel model) {
        EquipmentBookingStatusModel bookingStatusModel = createEquipmentBookingStatusModel(model);
        return createBookingStatus(bookingStatusModel);
    }

    private EquipmentBookingStatusModel createEquipmentBookingStatusModel(EquipmentModel model) {
        // TODO Change to optional
        EquipmentBookingStatusModel bookingStatusModel;
        Integer bookingStatusId = model.getEquipmentBookingStatusId();
        if (bookingStatusId == 0) return null;
        bookingStatusModel = repository.findById(bookingStatusId).orElseThrow();
        return bookingStatusModel;
    }


    private EquipmentBookingStatus createBookingStatus(EquipmentBookingStatusModel bookingStatusModel) {
        EquipmentBookingStatus bookingStatus;
        if (bookingStatusModel == null) return null;
        bookingStatus = createEquipmentBookingStatus(bookingStatusModel);
        return bookingStatus;
    }

    private EquipmentBookingStatus createEquipmentBookingStatus(EquipmentBookingStatusModel model) {
        return new EquipmentBookingStatus(
            model.getId(),
            model.getName(),
            model.getDescription(),
            model.getColor());
    }


    private List<Integer> setEquipmpentBookingStatusColor(String color) {
        // was used when colors were stored as rgb
        List<Integer> rgb = new ArrayList<>();
        for (String s : color.split(",", -1)) {
            rgb.add(Integer.valueOf(s));
        }
        return rgb;
    }



    public Integer getEquipmentBookingStatusId(Equipment equipment) {
        EquipmentBookingStatus bookingStatus = equipment.getBookingStatus();
        return bookingStatus.getId();
    }


    public EquipmentBookingStatus addEquipmentBookingStatus(EquipmentBookingStatus equipmentBookingStatus) {
        if (getEquipmentBookingStatusByName(equipmentBookingStatus.getName()) == null) {
            EquipmentBookingStatusModel model = new EquipmentBookingStatusModel(
                equipmentBookingStatus.getId(),
                equipmentBookingStatus.getName(),
                equipmentBookingStatus.getDescription(),
                equipmentBookingStatus.getColor());
            repository.save(model);
            equipmentBookingStatus.setId(model.getId());
            return equipmentBookingStatus;
        }
        return null;
    }


    private String setModelColor(List<Integer> color) {
        // was used when colors were stored as rgb
        StringBuilder sb = new StringBuilder();
        for (Integer i : color) {
            sb.append(i);
            sb.append(",");
        }
        String colorString = sb.toString();
        return colorString.substring(0, colorString.length() - 1);
    }

    private EquipmentBookingStatus getEquipmentBookingStatusByName(String name) {
        EquipmentBookingStatusModel model = repository.findByName(name);
        if (model != null) {
            return new EquipmentBookingStatus(model.getId(), model.getName(), model.getDescription(), model.getColor());
        }
        return null;
    }

    public String deleteEquipmentBookingStatus(int id) {
        String bookingStatusName = getEquipmentBookingStatusById(id).getName();
        repository.deleteById(id);
        return String.format("Booking status #%d (%s) has been deleted from the database.", id, bookingStatusName);
    }

}
