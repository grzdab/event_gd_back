package com.event.databaseDefaults;

import com.event.equipment.Equipment;
import com.event.equipment.EquipmentService;
import com.event.equipmentBookingPeriods.EquipmentBookingPeriods;
import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentBookingStatus.EquipmentBookingStatusService;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentCategory.EquipmentCategoryService;
import com.event.equipmentData.EquipmentData;
import com.event.equipmentData.EquipmentDataService;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.EquipmentStatusService;
import com.event.photoPath.PhotoPath;
import com.event.photoPath.PhotoPathService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class FakeDataInflator {

    @Bean
    CommandLineRunner run(EquipmentService equipmentService,
                          EquipmentBookingStatusService equipmentBookingStatusService,
                          EquipmentStatusService equipmentStatusService,
                          EquipmentCategoryService equipmentCategoryService,
                          EquipmentDataService equipmentDataService,
                          PhotoPathService photoPathService) {
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


            EquipmentBookingStatus equipmentBookingStatus1 = new EquipmentBookingStatus(0, "a");
            EquipmentBookingStatus equipmentBookingStatus3 = new EquipmentBookingStatus(0, "af");
            EquipmentBookingStatus equipmentBookingStatus4 = new EquipmentBookingStatus(0, "ax");
            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus1);
            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus3);
            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus4);

            equipmentBookingStatusService.getEquipmentBookingStatusByName("a");

            EquipmentData equipmentData1 = new EquipmentData(1,2,3,4,5,6,7,8,9);
            equipmentDataService.addEquipmentData(equipmentData1);

            equipmentDataService.getEquipmentData(1);


            Equipment equipment1 = new Equipment(
                    0,
                    1,
                    "aaa",
                    equipmentCategory1,
                    "aaa",
                    new EquipmentData(1,2,3,4,5,6,7,8,9),
                    null,
                    equipmentStatus1,
                    equipmentBookingStatus1,
                    new ArrayList<>(List.of(new EquipmentBookingPeriods(
                            1,
                            LocalDateTime.of(
                                    2022,
                                    11,
                                    11,
                                    11,
                                    11),
                            LocalDateTime.of(
                                    2022,
                                    11,
                                    11,
                                    11,
                                    12)))),
                    true);
            equipmentService.addEquipment(equipment1);

            Equipment equipment2 = new Equipment(
                    0,
                    1,
                    "bbb",
                    equipmentCategory1,
                    "aaa",
                    new EquipmentData(2,277,34,466,5,68,7,8,9),
                    null,
                    equipmentStatus1,
                    equipmentBookingStatus1,
                    new ArrayList<>(List.of(new EquipmentBookingPeriods(
                            1,
                            LocalDateTime.of(
                                    2122,
                                    11,
                                    11,
                                    11,
                                    11),
                            LocalDateTime.of(
                                    2122,
                                    11,
                                    11,
                                    11,
                                    12)))),
                    true);
            equipmentService.addEquipment(equipment2);
        };
    }
}
