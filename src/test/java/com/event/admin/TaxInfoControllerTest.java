package com.event.admin;

import com.event.legalEntityType.LegalEntityType;
import com.event.taxInfo.TaxInfo;
import com.event.taxInfo.TaxInfoController;
import com.event.taxInfo.TaxInfoService;
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

@WebMvcTest(TaxInfoController.class)
public class TaxInfoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    TaxInfoService service;

    TaxInfo taxInfo1 = new TaxInfo(1, new LegalEntityType("1","one"), "12", "12", "12", "12","yes" );
    TaxInfo taxInfo2 = new TaxInfo(2, new LegalEntityType("2","two"), "12", "12", "12", "12","yes");
    TaxInfo taxInfo3 = new TaxInfo(3, new LegalEntityType("3", "three"), "12", "12", "12", "12","yes");

    @Test
    public void getAllTaxInfo_success() throws Exception {
        List<TaxInfo> modelList = new ArrayList<>(Arrays.asList(taxInfo1, taxInfo2, taxInfo3));
        when(service.getAllTaxInfo()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/tax-info")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTTaxInfoAPI_success() throws Exception {
        TaxInfo model = new TaxInfo();
        model.setId(4);
        model.setLegalEntityType(new LegalEntityType("4", "cztery"));
        model.setRegon("1");
        model.setPesel("1");
        model.setNip("1");
        model.setKrs("1");
        model.setInsurance("no");
        service.addTaxInfo(model);

        Mockito.when(service.addTaxInfo(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/tax-info")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());

    }

    @Test
    public void PUTTaxInfoAPI_success() throws Exception {
        TaxInfo model = new TaxInfo();
//        model.getId(2);
        model.setLegalEntityType(new LegalEntityType("4", "cztery"));
        model.setRegon("1");
        model.setPesel("1");
        model.setNip("1");
        model.setKrs("1");
        model.setInsurance("no");
        service.updateTaxInfo(2, model);

        Mockito.when(service.updateTaxInfo(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/tax-info")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void DELETETaxInfoAPI_success() throws Exception{
        Mockito.when(service.getTaxInfo(2)).thenReturn(taxInfo2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/tax-info/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
