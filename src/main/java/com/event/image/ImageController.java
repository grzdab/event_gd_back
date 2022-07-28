package com.event.image;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/equipment")
@CrossOrigin("http://localhost:3000")
public class ImageController {
    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping("/upload-image")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        String returnValue = "saved";
        if (imageFile.isEmpty()) {
            throw new IllegalStateException("Uploaded file is empty.");
        }
//        if (!Arrays.asList("imageFile/jpeg", "imageFile/png").contains(imageFile.getContentType())) {
//            throw new IllegalStateException("Unsupported file type (only .jpg and.png allowed");
//        }
        try {
            service.saveImage(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            returnValue = "error";
        }
        return returnValue;
    }

    @DeleteMapping("/delete-image")
    public String deleteImage(@RequestParam String path) {
        return service.deleteImage(path);
    }
}