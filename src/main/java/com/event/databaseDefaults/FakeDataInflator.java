package com.event.databaseDefaults;

import com.event.equipment.EquipmentService;
import com.event.equipmentBookingStatus.EquipmentBookingStatusService;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentCategory.EquipmentCategoryService;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.EquipmentStatusService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeDataInflator {

    @Bean
    CommandLineRunner run(EquipmentService equipmentService,
                          EquipmentBookingStatusService equipmentBookingStatusService,
                          EquipmentStatusService equipmentStatusService,
                          EquipmentCategoryService equipmentCategoryService) {
        return args -> {
            EquipmentCategory equipmentCategory1 = new EquipmentCategory(0, 0, "aaa", "description");
            EquipmentCategory equipmentCategory2 = new EquipmentCategory(0, 0, "avvaa", "desdfggcription");
            EquipmentCategory equipmentCategory3 = new EquipmentCategory(0, 0, "aahhha", "uuudescription");
            equipmentCategoryService.addEquipmentCategory(equipmentCategory1);
            equipmentCategoryService.addEquipmentCategory(equipmentCategory2);
            equipmentCategoryService.addEquipmentCategory(equipmentCategory3);
            EquipmentStatus equipmentStatus1 = new EquipmentStatus(0, "a");
            EquipmentStatus equipmentStatus3 = new EquipmentStatus(0, "af");
            EquipmentStatus equipmentStatus4 = new EquipmentStatus(0, "ag");
            equipmentStatusService.addEquipmentStatus(equipmentStatus1);
            equipmentStatusService.addEquipmentStatus(equipmentStatus3);
            equipmentStatusService.addEquipmentStatus(equipmentStatus4);

        };
    }
}
