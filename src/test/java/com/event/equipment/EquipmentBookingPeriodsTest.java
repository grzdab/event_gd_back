package com.event.equipment;

import com.event.equipmentBookingPeriods.EquipmentBookingPeriods;
import com.event.equipmentBookingPeriods.EquipmentBookingPeriodsController;
import com.event.equipmentBookingPeriods.EquipmentBookingPeriodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@WebMvcTest(EquipmentBookingPeriodsController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(TestConfig.class)
public class EquipmentBookingPeriodsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    EquipmentBookingPeriodsService service;

    EquipmentBookingPeriods periods1 = new EquipmentBookingPeriods(
            1,
            LocalDateTime.of(
                    2019,
                    01,
                    28,
                    14,
                    33
            ),
            LocalDateTime.of(
                    2019,
                    04,
                    02,
                    15,
                    11
            ));

    EquipmentBookingPeriods periods2 = new EquipmentBookingPeriods(
            2,
            LocalDateTime.of(
                    2022,
                    01,
                    28,
                    14,
                    33
            ),
            LocalDateTime.of(
                    2019,
                    04,
                    02,
                    15,
                    11
            ));

    EquipmentBookingPeriods periods3 = new EquipmentBookingPeriods(
            3,
            LocalDateTime.of(
                    2003,
                    01,
                    18,
                    14,
                    33
            ),
            LocalDateTime.of(
                    2019,
                    05,
                    02,
                    15,
                    11
            ));

    @Test
    void getEquipmentBookingPeriodsById() {
        assertTrue(true);
    }
}
