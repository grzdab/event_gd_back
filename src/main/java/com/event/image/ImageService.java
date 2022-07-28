package com.event.image;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@CrossOrigin("http://localhost:3000")
public class ImageService {

    public void saveImage(MultipartFile imageFile) throws IOException {
        String folder = "d:\\ram\\";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }

    public String deleteImage(String path) {
        try {
            File fileToDelete = new File(path);
            if (fileToDelete.delete()) {
                return "deleted";
            }
        } catch (Exception e) {
            return "error while deleting" + e;
        }
        return "failed";
    }
}