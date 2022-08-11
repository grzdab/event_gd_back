package com.event.equipment;

import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentOwnership.EquipmentOwnership;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EquipmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(TestConfig.class)
public class EquipmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    EquipmentService service;

    Equipment equipment1 = new Equipment(2,
            1,
            "toy",
            new EquipmentCategory(),
            "null",
            new ArrayList<>(),
            new EquipmentStatus(),
            new EquipmentBookingStatus(),
            new EquipmentOwnership(),
            new ArrayList<>(),
            false,
            0,
            0 ,
            0, 0,
            0,
            0,
            0,
            0
    );

    Equipment equipment2 = new Equipment(2,
            2,
            "castle",
            new EquipmentCategory(),
            "Hello World",
            new ArrayList<>(),
            new EquipmentStatus(),
            new EquipmentBookingStatus(),
            new EquipmentOwnership(),
            new ArrayList<>(),
            true,
            0,
            6,
            0,
            5,
            0,
            5,
            7,
            0
    );

    Equipment equipment3 = new Equipment(2,
            3,
            "house",
            new EquipmentCategory(),
            "Goodbye World",
            new ArrayList<>(),
            new EquipmentStatus(),
            new EquipmentBookingStatus(),
            new EquipmentOwnership(),
            new ArrayList<>(),
            true,
            0,
            6,
            0,
            5,
            0,
            5,
            7,
            0
    );

    @Test
    void getAllEquipment() throws Exception {
        List<Equipment> equipmentList = new ArrayList<>(Arrays.asList(equipment1, equipment2, equipment3));
        when(service.getAllEquipment()).thenReturn(equipmentList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/equipment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
    }

    @Test
    void getEquipmentByIdTest() throws Exception {
        when(service.getEquipmentById(anyInt())).thenReturn(equipment1);

        mvc.perform(MockMvcRequestBuilders.get("/equipment/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("toy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes").value("null"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.inUse").value(false))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void addEquipment() throws Exception {
        when(service.updateEquipment(equipment2.getId(), equipment2)).thenReturn(equipment2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/equipment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(equipment2));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteEquipment() throws Exception {
        when(service.getEquipmentById(3)).thenReturn(equipment3);

        mvc.perform(MockMvcRequestBuilders
                    .delete("/equipment/3")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }
}
