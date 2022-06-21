package com.event.admin;

import com.event.eventStatus.EventStatus;
import com.event.eventStatus.EventStatusController;
import com.event.eventStatus.EventStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventStatusController.class)
public class EventStatusControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    EventStatusService service;

    EventStatus eventStatus1 = new EventStatus(1, "100");
    EventStatus eventStatus2 = new EventStatus(2, "75");
    EventStatus eventStatus3 = new EventStatus(3, "50");

    @Test
    public void getAllEventStatus_success() throws Exception {
        List<EventStatus> modelList = new ArrayList<>(Arrays.asList(eventStatus1, eventStatus2, eventStatus3));
        when(service.getAllEventStatuses()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/admin/eventStatus")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTEventStatusAPI_success() throws Exception {
        EventStatus model = new EventStatus();
        model.setId(4);
        model.setEventStatus("30");

        Mockito.when(service.addEventStatus(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/eventStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void PUTEventStatusAPI_success() throws Exception {
        EventStatus model = new EventStatus();
        model.setEventStatus("90");
        service.updateEventStatus(2, model);

        Mockito.when(service.updateEventStatus(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/eventStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void DELETEEventStatusAPI_success() throws Exception{
        Mockito.when(service.getEventStatus(2)).thenReturn(eventStatus2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/eventStatus/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
