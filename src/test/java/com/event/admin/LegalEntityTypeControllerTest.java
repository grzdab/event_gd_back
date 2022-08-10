package com.event.admin;

import com.event.TestConfig;
import com.event.contact.Contact;
import com.event.legalEntityType.LegalEntityType;
import com.event.legalEntityType.LegalEntityTypeController;
import com.event.legalEntityType.LegalEntityTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(LegalEntityTypeController.class)
public class LegalEntityTypeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    LegalEntityTypeService service;

    LegalEntityType legalEntityType1 = new LegalEntityType(1, "Zenek");
    LegalEntityType legalEntityType2 = new LegalEntityType(2, "Andrzej");
    LegalEntityType legalEntityType3 = new LegalEntityType(3, "Ziutek");

    @Test
    public void getAllLegalEntityType_success() throws Exception {
        List<LegalEntityType> modelList = new ArrayList<>(Arrays.asList(legalEntityType1, legalEntityType2, legalEntityType3));
        when(service.getAllLegalEntityTypes()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/admin/legalEntityType")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTLegalEntityTypeAPI_success() throws Exception {
        LegalEntityType model = new LegalEntityType();
        model.setId(4);
        model.setTypeName("Mikolaj");
        service.addLegalEntityType(model);

        when(service.addLegalEntityType(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/legalEntityType")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void PUTLegalEntityTypeAPI_success() throws Exception {
        LegalEntityType model = new LegalEntityType();
        model.setTypeName("Brajanek");
        service.updateLegalEntityType(2, model);

        when(service.updateLegalEntityType(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/legalEntityType")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void DELETELegalEntityTypeAPI_success() throws Exception{
        when(service.getLegalEntityType(2)).thenReturn(legalEntityType2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/legalEntityType/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
