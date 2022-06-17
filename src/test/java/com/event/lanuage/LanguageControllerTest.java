package com.event.lanuage;

import com.event.language.Language;
import com.event.language.LanguageController;
import com.event.language.LanguageService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageController.class)
public class LanguageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    LanguageService service;

    Language language1 = new Language(1, "Polski");
    Language language2 = new Language(2, "Francuski");
    Language language3 = new Language(3, "Estonski");

    @Test
    public void getAllLanguages_success() throws Exception {
        List<Language> modelList = new ArrayList<>(Arrays.asList(language1, language2, language3));
        when(service.getAllLanguages()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/admin/language")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

//        assertEquals(responseContent, "dupa");
//                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
//                .andExpect((ResultMatcher) jsonPath("$[2].propertyName", is("Estonski")));
    }
    @Test
    public void createPOSTLanguageAPI_success() throws Exception {
        Language model = new Language();
                model.setId(4);
                model.setPropertyName("slaski");
                service.addLanguage(model);

        Mockito.when(service.addLanguage(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/language")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.name", is("John Doe")));

    }

    @Test
    public void PUTLanguageAPI_success() throws Exception {
        Language model = new Language();
        model.getId(1);
        model.setPropertyName("DiscoPolo");
        service.updateLanguage(1, model);

        Mockito.when(service.updateLanguage(1, model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/language")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
    @Test
    public void PUTLanguageAPI_nullId() throws Exception {
//        Language model = new Language();
//        model.setPropertyName("Zenek");
//        service.updateLanguage(1, model);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/language")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(model));
//
//        mvc.perform(mockRequest)
//                .andExpect(status().isBadRequest())
//                .andExpect(result ->
//                        assertTrue(result.getResolvedException() instanceof LanguageController.InvalidRequestException))
//                .andExpect(result ->
//                        assertEquals("Language or ID must not be null!", result.getResolvedException().getMessage()));
    }

    @Test
    public void PUTLanguageAPI_recordNotFound() throws Exception {
//        Language model = new Language();
//                model.getId(51);
//                model.setPropertyName("Sherlock");
//                service.updateLanguage(51, model);
//
//        Mockito.when(service.updateLanguage(51, model)).thenReturn(null);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/language")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(model));
//
//        mvc.perform(mockRequest)
//                .andExpect(status().isBadRequest())
//                .andExpect(result ->
//                        assertNotNull(result.getResolvedException()))
//                .andExpect(result ->
//                        assertEquals("Language with ID 51 does not exist.", result.getResolvedException().getMessage()));
    }
    @Test
    public void DELETELanguageAPI_success() throws Exception{
        Mockito.when(service.getLanguage(2)).thenReturn(language2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/language/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePatientById_notFound() throws Exception {
        Mockito.when(service.getLanguage(51)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/language/51")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
//                .andExpect(result ->
//                        assertTrue(result.getResolvedException() instanceof NotFoundException))
//                .andExpect(result ->
//                        assertEquals("Patient with ID 5 does not exist.", result.getResolvedException().getMessage()));
    }

}