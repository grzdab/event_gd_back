package com.event.admin;

import com.event.privilege.Privilege;
import com.event.privilege.PrivilegeController;
import com.event.privilege.PrivilegeEnum;
import com.event.privilege.PrivilegeService;
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

@WebMvcTest(PrivilegeController.class)
public class PrivilegeControllerTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        ObjectMapper mapper;

        @MockBean
        PrivilegeService service;

        Privilege privilege1 = new Privilege(1, "akcent", PrivilegeEnum.READ);
        Privilege privilege2 = new Privilege(2, "klient", PrivilegeEnum.DELETE);
        Privilege privilege3 = new Privilege(3, "akcent", PrivilegeEnum.CREATE);

        @Test
        public void getAllPrivileges_success() throws Exception {
            List<Privilege> modelList = new ArrayList<>(Arrays.asList(privilege1, privilege2, privilege3));
            when(service.getAllPrivileges()).thenReturn(modelList);

            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                            .get("/admin/privileges")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseContent = mvcResult.getResponse().getContentAsString();

//        assertEquals(responseContent, "dupa");
//                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
//                .andExpect((ResultMatcher) jsonPath("$[2].propertyName", is("Estonski")));
        }
        @Test
        public void createPOSTPrivilegeAPI_success() throws Exception {
            Privilege model = new Privilege();
            model.setId(4);
            model.setName("slaski");
            model.setPrivilegesEnum(PrivilegeEnum.READ);
            service.addPrivileges(model);

            Mockito.when(service.addPrivileges(model)).thenReturn(model);

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/privilege")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.mapper.writeValueAsString(model));

            mvc.perform(mockRequest)
                    .andExpect(status().isOk());
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.name", is("John Doe")));

        }

        @Test
        public void PUTPrivilegeAPI_success() throws Exception {
            Privilege model = new Privilege();
//            model.getId(1);
            model.setName("DiscoPolo");
            model.setPrivilegesEnum(PrivilegeEnum.READ);
            service.updatePrivileges(1, model);

            Mockito.when(service.updatePrivileges(model.getId(), model)).thenReturn(model);

            MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/admin/privilege")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(this.mapper.writeValueAsString(model));

            mvc.perform(mockRequest)
                    .andExpect(status().isOk());
        }
        @Test
        public void PUTPrivilegeAPI_nullId() throws Exception {
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
        public void PUTPrivilegeAPI_recordNotFound() throws Exception {
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
        public void DELETEPrivilegeAPI_success() throws Exception{
            Mockito.when(service.getPrivileges(2)).thenReturn(privilege2);

            mvc.perform(MockMvcRequestBuilders
                            .delete("/admin/privilege/2")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        public void deletePatientById_notFound() throws Exception {
//        Mockito.when(service.getLanguage(51)).thenReturn(null);
//
//        mvc.perform(MockMvcRequestBuilders
//                        .delete("/admin/language/51")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(result ->
//                        assertNotNull(result.getResolvedException()))
//                .andExpect(result ->
//                        assertEquals("Patient with ID 51 does not exist.", result.getResolvedException().getMessage()));
        }

    }

