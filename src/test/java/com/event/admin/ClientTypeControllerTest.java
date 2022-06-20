package com.event.admin;

import com.event.clientType.ClientType;
import com.event.clientType.ClientTypeService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientType.class)
public class ClientTypeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ClientTypeService service;

    ClientType clientType1 = new ClientType(1, "admin");
    ClientType clientType2 = new ClientType(2, "user");
    ClientType clientType3 = new ClientType(3, "nonuser");

    @Test
    public void getAllClientType_success() throws Exception {
        List<ClientType> modelList = new ArrayList<>(Arrays.asList(clientType1, clientType2, clientType3));
        when(service.getAllClientTypes()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/admin/clientType")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTClientTypeAPI_success() throws Exception {
        ClientType model = new ClientType();
        model.setId(4);
        model.setTypeName("mikolaj");
        service.addClientType(model);

        Mockito.when(service.addClientType(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/clientType")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());

    }

    @Test
    public void PUTClientTypeAPI_success() throws Exception {
        ClientType model = new ClientType();
        model.setTypeName("michal");
        service.updateClientType(2, model);

        Mockito.when(service.updateClientType(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/clientType")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void DELETEClientTypeAPI_success() throws Exception{
        Mockito.when(service.getClientType(2)).thenReturn(clientType2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/clientType/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
