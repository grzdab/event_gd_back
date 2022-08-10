package com.event.admin;

import com.event.TestConfig;
import com.event.address.Address;
import com.event.address.AddressController;
import com.event.address.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Import({TestConfig.class})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AddressService service;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    Address address1 = new Address(1, "Akacjowa", "1","11-111","Krakow", 1234,true, "f7169af6-dd50-427f-9a94-0ecffdb47f95");
    Address address2 = new Address(2, "Czeresniowa", "2","11-111","Krakow", 1234,false, "f7169af6-dd50-427f-9a94-0ecffdb47f95");
    Address address3 = new Address(3, "Zlota", "3","11-111","Krakow", 1234,true, "cb6a2ef9-4789-4ccb-b875-7e1fc9b16894");


    @Test
    public void getAllAddress_success() throws Exception {
        List<Address> modelList = new ArrayList<>(Arrays.asList(address1,address2,address3));
        when(service.getAllAddressForClient("1")).thenReturn(modelList);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/address/client/{clientId}","cb6a2ef9-4789-4ccb-b875-7e1fc9b16894")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void createPOSTAddressAPI_success() throws Exception {
        Address model = new Address();
        model.setId(4);
        model.setStreet("Mikolaj");
        model.setStreetNumber("11");
        model.setPostalCode("11-111");
        model.setCity("Krakow");
        model.setCountryId(1234);
        model.setPrimary(false);
        model.setClientId("f7169af6-dd50-427f-9a94-0ecffdb47f95");
        service.addAddress(model);

        when(service.addAddress(model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void PUTAddressAPI_success() throws Exception {
        Address model = new Address();
        model.setCity("Lodz");
        service.updateAddress(2, model);

        when(service.updateAddress(model.getId(), model)).thenReturn(model);

        MockHttpServletRequestBuilder mockRequest = post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(model));

        mvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void DELETEAddressAPI_success() throws Exception{
        when(service.getAddress(2)).thenReturn(address2);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/address/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}

