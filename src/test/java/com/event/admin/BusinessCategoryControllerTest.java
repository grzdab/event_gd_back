package com.event.admin;

import com.event.businessCategory.BusinessCategory;
import com.event.businessCategory.BusinessCategoryController;
import com.event.businessCategory.BusinessCategoryService;
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

@WebMvcTest(BusinessCategoryController.class)
public class BusinessCategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BusinessCategoryService service;

    BusinessCategory businessCategory1 = new BusinessCategory(1, "Wesela");
    BusinessCategory businessCategory2 = new BusinessCategory(2, "Urodziny");
    BusinessCategory businessCategory3 = new BusinessCategory(3, "Komunie");

    @Test
    public void getAllBusinessCategories_success() throws Exception {
        List<BusinessCategory> modelList = new ArrayList<>(Arrays.asList(businessCategory1, businessCategory2, businessCategory3));
        when(service.getAllBusinessCategories()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/admin/businessCategory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTBusinessCategoryAPI_success() throws Exception {
        BusinessCategory model = new BusinessCategory();
        model.setId(4);
        model.setName("18stki");

        Mockito.when(service.addBusinessCategory(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/businessCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void PUTBusinessCategoryAPI_success() throws Exception {
        BusinessCategory model = new BusinessCategory();
        model.setName("Integracja");
        service.updateBusinessCategory(2, model);

        Mockito.when(service.updateBusinessCategory(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/businessCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void DELETEBusinessCategoryAPI_success() throws Exception{
        Mockito.when(service.getBusinessCategory(2)).thenReturn(businessCategory2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/businessCategory/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
