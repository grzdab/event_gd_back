package com.event.admin;

import com.event.TestConfig;
import com.event.address.Address;
import com.event.companyData.CompanyData;
import com.event.companyData.CompanyDataController;
import com.event.companyData.CompanyDataService;
import com.event.contact.Contact;
import com.event.legalEntityType.LegalEntityType;
import com.event.taxInfo.TaxInfo;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CompanyDataController.class)
public class CompanyDataControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CompanyDataService service;

    TaxInfo taxInfo1 = new TaxInfo(1, new LegalEntityType(1,"one"), "12", "12", "12",
            "12","yes" );
    TaxInfo taxInfo2 = new TaxInfo(2, new LegalEntityType(2,"two"), "12", "12", "12",
            "12","yes");
    TaxInfo taxInfo3 = new TaxInfo(3, new LegalEntityType(3, "three"), "12", "12", "12",
            "12","yes");

    LegalEntityType legalEntityType1 = new LegalEntityType(1, "Zenek");
    LegalEntityType legalEntityType2 = new LegalEntityType(2, "Andrzej");
    LegalEntityType legalEntityType3 = new LegalEntityType(3, "Ziutek");

    Address address1 = new Address(1, "Akacjowa", "1","11-111","Krakow",
            1234,true, "f7169af6-dd50-427f-9a94-0ecffdb47f95");
    Address address2 = new Address(2, "Czeresniowa", "2","11-111","Krakow",
            1234,false, "f7169af6-dd50-427f-9a94-0ecffdb47f95");
    Address address3 = new Address(3, "Zlota", "3","11-111","Krakow",
            1234,true, "cb6a2ef9-4789-4ccb-b875-7e1fc9b16894");

    Contact contact1 = new Contact(1, "1234", "wp@wp.pl");
    Contact contact2 = new Contact(2,"123456", "olek@pp.pl");
    Contact contact3 = new Contact(3, "12321","tomek@pl.pl");

    CompanyData companyData1 = new CompanyData(UUID.randomUUID(),
            "ZenekMArtyniuk", "Akcent", taxInfo1, legalEntityType1, true, "nope",address1, contact1);
    CompanyData companyData2 = new CompanyData(UUID.fromString("cb6a2ef9-4789-4ccb-b875-7e1fc9b16894"),
            "DiscoPolo", "DP", taxInfo2, legalEntityType2, false, "nope",address2, contact2);
    CompanyData companyData3 = new CompanyData(UUID.randomUUID(),
            "BajerFull", "Chiny", taxInfo3, legalEntityType3, true, "nope",address3, contact3);

    @Test
    public void getAllCompanyData_success() throws Exception {
        List<CompanyData> modelList = new ArrayList<>(Arrays.asList(companyData1, companyData2, companyData3));
        when(service.getAllCompanyData()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/companyData")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTCompanyDataAPI_success() throws Exception {
        CompanyData model = new CompanyData();
        model.setId(UUID.randomUUID());
        model.setFullName("Maja_i_Guio");
        model.setShortName("Wodecki");

        when(service.addCompanyData(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/companyData")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void PUTCompanyDataAPI_success() throws Exception {
        CompanyData model = new CompanyData();
        model.setShortName("Polo");
        service.updateCompanyData(UUID.fromString("cb6a2ef9-4789-4ccb-b875-7e1fc9b16894"), model);

        when(service.updateCompanyData(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/companyData")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void DELETECompanyDataAPI_success() throws Exception{
        when(service.getCompanyData(UUID.fromString("cb6a2ef9-4789-4ccb-b875-7e1fc9b16894"))).thenReturn(companyData2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/companyData/cb6a2ef9-4789-4ccb-b875-7e1fc9b16894")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
