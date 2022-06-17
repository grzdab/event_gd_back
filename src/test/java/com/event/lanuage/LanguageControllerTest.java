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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageController.class)
public class LanguageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    LanguageService languageService;

    Language language1 = new Language(1, "Polski");
    Language language2 = new Language(2, "Francuski");
    Language language3 = new Language(3, "Estonski");

    @Test
    public void getAllLanguages_success() throws Exception {
        List<Language> modelList = new ArrayList<>(Arrays.asList(language1, language2, language3));
        when(languageService.getAllLanguages()).thenReturn(modelList);

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
                languageService.addLanguage(model);

        Mockito.when(languageService.addLanguage(model)).thenReturn(model);

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
        languageService.updateLanguage(1, model);

        Mockito.when(languageService.updateLanguage(1, model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/language")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
}