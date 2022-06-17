package com.event.lanuage;

import com.event.language.Language;
import com.event.language.LanguageController;
import com.event.language.LanguageService;
import com.event.language.dao.LanguageModel;
import com.event.language.dao.LanguageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
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
//    @Test
//    public void createPOSTLanguageAPI_success() throws Exception {
//        LanguageModel model = LanguageModel.buildier()
//                .propertyName("śląski")
//                .build;
//
//    }
}