package com.event.admin;

import com.event.TestConfig;
import com.event.contact.Contact;
import com.event.contact.ContactController;
import com.event.contact.ContactService;
import com.event.role.Role;
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
@WebMvcTest(ContactController.class)
public class ContactControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ContactService service;

    Contact contact1 = new Contact(1, "1234", "wp@wp.pl");
    Contact contact2 = new Contact(2,"123456", "olek@pp.pl");
    Contact contact3 = new Contact(3, "12321","tomek@pl.pl");

    @Test
    public void getAllContacts_success() throws Exception {
        List<Contact> modelList = new ArrayList<>(Arrays.asList(contact1, contact2, contact3));
        when(service.getAllContacts()).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/contact")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

    }
    @Test
    public void createPOSTContactAPI_success() throws Exception {
        Contact model = new Contact();
        model.setId(4);
        model.setEmail("tt@wt.pl");
        model.setPhone("54321");

        when(service.addContact(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void PUTContactAPI_success() throws Exception {
        Contact model = new Contact();
        model.setPhone("232323");
        service.updateContact(2, model);

        when(service.updateContact(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void DELETEContactAPI_success() throws Exception{
        when(service.getContact(2)).thenReturn(contact2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/contact/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
