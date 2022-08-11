package com.event.equipment;

import org.mockito.junit.MockitoJUnitRunner;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.EquipmentStatusController;
import com.event.equipmentStatus.EquipmentStatusService;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EquipmentStatusController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(TestConfig.class)
public class EquipmentStatusControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    EquipmentStatusService service;

    EquipmentStatus status1 = new EquipmentStatus(
            2,
            "empty/full");
    EquipmentStatus status2 = new EquipmentStatus(
            3,
            "nullnull");

    @Test
    public void getAllEquipmentStatus() throws Exception {
        List<EquipmentStatus> equipmentStatusList = new ArrayList<>(Arrays.asList(status1, status2));
        when(service.getEquipmentStatuses()).thenReturn(equipmentStatusList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/equipment-status")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getEquipmentStatusByIdTest() throws Exception {
        EquipmentStatus status = new EquipmentStatus();

        status.setId(1);
        status.setName("empty");

        when(service.getEquipmentStatusById(anyInt())).thenReturn(status);

        mvc.perform(MockMvcRequestBuilders.get("/equipment-status/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("empty"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void addEquipmentStatusTest() throws Exception {
        EquipmentStatus status = new EquipmentStatus();
        status.setId(2);
        status.setName("full");

        when(service.updateEquipmentStatus(status.getId(), status)).thenReturn(status);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/equipment-status")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(status));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteEquipmentStatusTest() throws Exception {
        when(service.getEquipmentStatusById(2)).thenReturn(status1);

        mvc.perform(MockMvcRequestBuilders
                .delete("/equipment-status/2")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
