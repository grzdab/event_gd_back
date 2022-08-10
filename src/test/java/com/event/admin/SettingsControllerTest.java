package com.event.admin;

import com.event.TestConfig;
import com.event.representative.settings.Settings;
import com.event.representative.settings.SettingsController;
import com.event.representative.settings.SettingsService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(SettingsController.class)
public class SettingsControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    SettingsService service;

    Settings settings1 = new Settings(1, "zenek");
    Settings settings2 = new Settings(2, "olek");
    Settings settings3 = new Settings(3, "tomek");

    @Test
    public void getAllSettings_success() throws Exception {
        List<Settings> modelList = new ArrayList<>(Arrays.asList(settings1, settings2, settings3));
        when(service.getAllSettings()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/admin/settings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTSettingsAPI_success() throws Exception {
        Settings model = new Settings();
        model.setId(4);
        model.setResourcesURI("123456");

        when(service.addSettings(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/settings")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void PUTSettingsAPI_success() throws Exception {
        Settings model = new Settings();
        model.setResourcesURI("aaaaaa");
        service.updateSettings(2, model);

        when(service.updateSettings(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/settings")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void DELETESettingsAPI_success() throws Exception{
        when(service.getSettings(2)).thenReturn(settings2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/admin/settings/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
