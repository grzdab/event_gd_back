//package com.event.admin;
//
//import com.event.appRole.AppRole;
//import com.event.appRole.AppRoleController;
//import com.event.appRole.AppRoleService;
//import com.event.role.Role;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AppRoleController.class)
//public class AppRoleControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    ObjectMapper mapper;
//
//    @MockBean
//    AppRoleService service;
//
//    Role role1 = new Role(1, "zenek");
//    Role role2 = new Role(2, "olek");
//    Role role3 = new Role(3, "tomek");
//
//    AppRole appRole1 = new AppRole(1, new ArrayList<>(Arrays.asList(role1, role2)));
//    AppRole appRole2 = new AppRole(2, new ArrayList<>(Arrays.asList(role2, role3, role1)));
//    AppRole appRole3 = new AppRole(3, new ArrayList<>(Arrays.asList(role3, role1)));
//
//    @Test
//    public void getAllAppRole_success() throws Exception {
//        List<AppRole> modelList = new ArrayList<>(Arrays.asList(appRole1, appRole2, appRole3));
//        when(service.getAllAppRoles()).thenReturn(modelList);
//
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
//                        .get("/appRole")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//        String responseContent = mvcResult.getResponse().getContentAsString();
//
//    }
//    @Test
//    public void createPOSTAppRoleAPI_success() throws Exception {
//        AppRole model = new AppRole();
//        model.setId(4);
//        model.setRoleList(new ArrayList<>(Arrays.asList(role1)));
//
//        when(service.addAppRole(model)).thenReturn(model);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/appRole")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(model));
//
//        mvc.perform(mockRequest)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void PUTAppRoleAPI_success() throws Exception {
//        AppRole model = new AppRole();
//        model.setRoleList(new ArrayList<>(Arrays.asList(role1, role2)));
//        service.updateAppRole(2, model);
//
//        when(service.updateAppRole(model.getId(), model)).thenReturn(model);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/appRole")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(model));
//
//        mvc.perform(mockRequest)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void DELETEAppRoleAPI_success() throws Exception{
//        when(service.getAppRole(2)).thenReturn(appRole2);
//
//        mvc.perform(MockMvcRequestBuilders
//                        .delete("/appRole/2")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//}
