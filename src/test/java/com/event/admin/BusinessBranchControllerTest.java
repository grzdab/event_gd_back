package com.event.admin;

import com.event.businessBranch.BusinessBranch;
import com.event.businessBranch.BusinessBranchController;
import com.event.businessBranch.BusinessBranchService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BusinessBranchController.class)
public class BusinessBranchControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BusinessBranchService service;

    BusinessBranch businessBranch1 = new BusinessBranch(1, "Wesela");
    BusinessBranch businessBranch2 = new BusinessBranch(2, "Urodziny");
    BusinessBranch businessBranch3 = new BusinessBranch(3, "Komunie");

    @Test
    public void getAllBusinessBranch_success() throws Exception {
        List<BusinessBranch> modelList = new ArrayList<>(Arrays.asList(businessBranch1, businessBranch2, businessBranch3));
        when(service.getAllBusinessBranches()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/admin/businessBranch")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTBusinessBranchAPI_success() throws Exception {
        BusinessBranch model = new BusinessBranch();
        model.setId(4);
        model.setName("18stki");

        when(service.addBusinessBranch(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/businessBranch")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void PUTBusinessBranchAPI_success() throws Exception {
        BusinessBranch model = new BusinessBranch();
        model.setName("Integracja");
        service.updateBusinessBranch(2, model);

        when(service.updateBusinessBranch(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/businessBranch")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void DELETEBusinessBranchAPI_success() throws Exception{
        when(service.getBusinessBranch(2)).thenReturn(businessBranch2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/businessBranch/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
