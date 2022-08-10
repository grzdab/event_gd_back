package com.event.admin;

import com.event.TestConfig;
import com.event.contact.Contact;
import com.event.representative.Representative;
import com.event.representative.RepresentativeController;
import com.event.representative.RepresentativeService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestConfig.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(RepresentativeController.class)
public class RepresentativeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    RepresentativeService service;

    Representative representative1 = new Representative(1, "Zenek", "Martyniuk", new Contact(1, "aa@aa.pl", "1234"), "1");
    Representative representative2 = new Representative(2, "Andrzej", "Andrzej", new Contact(2, "1aa@aa.pl", "123"), "2");
    Representative representative3 = new Representative(3, "Angelika", "Angelika", new Contact(3, "2aa@aa.pl", "12345"), "3");

    @Test
    public void getAllRepresentative_success() throws Exception {
        List<Representative> modelList = new ArrayList<>(Arrays.asList(representative1, representative2, representative3));
        when(service.getAllRepresentative()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/representative")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTRepresentativeAPI_success() throws Exception {
        Representative model = new Representative();
        model.setId(4);
        model.setFirstName("Mikolaj");
        model.setLastName("Loka");
        model.setContact(new Contact(4, "aa3@wp.pl", "4111"));
        model.setClientId("4");
        service.addRepresentative(model);

        when(service.addRepresentative(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/representative")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void PUTRepresentativeAPI_success() throws Exception {
        Representative model = new Representative();
        model.setFirstName("Brajanek");
        service.updateRepresentative(2, model);

        when(service.updateRepresentative(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/representative")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void DELETERepresentativeAPI_success() throws Exception{
        when(service.getRepresentative(2)).thenReturn(representative2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/representative/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
