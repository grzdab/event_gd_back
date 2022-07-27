package com.event.photo;

import com.event.address.AddressController;
import com.event.address.AddressService;
import com.event.photoPath.PhotoPath;
import com.event.photoPath.PhotoPathController;
import com.event.photoPath.PhotoPathService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PhotoPathController.class)
public class PhotoPathControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PhotoPathService service;

    PhotoPath photoPath1 = new PhotoPath(1, "null1");
    PhotoPath photoPath2 = new PhotoPath(2, "null2");
    PhotoPath photoPath3 = new PhotoPath(3, "null3");

    @Test
    public void addPhotoPathTest() {
        System.out.println("uio");
//        service.addPhotoPath(photoPath1);
//        service.addPhotoPath(photoPath2);
//        service.addPhotoPath(photoPath3);
    }
}
