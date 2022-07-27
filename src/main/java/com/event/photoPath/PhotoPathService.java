package com.event.photoPath;

import com.event.equipment.dao.EquipmentModel;
import com.event.photoPath.dao.PhotoPathModel;
import com.event.photoPath.dao.PhotoPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoPathService {
    private PhotoPathRepository photoPathRepository;
    private String PATH_TO_PHOTO_FOLDER = "src/main/java/com/event/photoPath/photo/";

    @Autowired
    public PhotoPathService(PhotoPathRepository photoPathRepository) {
        this.photoPathRepository = photoPathRepository;
    }

    public List<String> addPhotoPaths(List<String> photoNames) {
        //TODO logic to get list of paths to update write here
        List<PhotoPath> pathsToSave = new ArrayList<>();//in future rebuild for logic
        for (String photoName : photoNames) {
            String path = PATH_TO_PHOTO_FOLDER + photoName;
            PhotoPath photoPath = new PhotoPath(0, path);
            pathsToSave.add(photoPath);
        }
//        getPhotoPathsById(id);
        return null;
    }

//    public List<PhotoPath> getPhotoPathsById(int id) {
//
//    }

    public PhotoPath getPhotoPath(Integer equipmentPhotoId) {
        try {
            PhotoPathModel model = photoPathRepository.findById(equipmentPhotoId).get();
            return createPhotoPath(model);
        } catch (Exception e) {
            return null;
        }
    }

    public List<PhotoPath> getAllPhotoPaths() {
        List<PhotoPathModel> pathModels = photoPathRepository.findAll();
        if (pathModels.isEmpty()) return null;
        return createListOfPhotoPaths(pathModels);
    }

    private PhotoPath createPhotoPath(PhotoPathModel model) {
        if (model == null) return null;
        return new PhotoPath(model.getId(), model.getPhotoURI());
    }

    public List<Integer> createListOfPhotoId(List<PhotoPath> photos) {
        if (photos == null) return null;
        try {
            List<Integer> photoIds = new ArrayList<>();
            for (PhotoPath photo : photos) {
                photoIds.add(photo.getId());
            }
            return photoIds;
        } catch (Exception e) {
            return null;
        }
    }

    public List<PhotoPath> createListOfPhotoPaths(List<PhotoPathModel> pathModels) {
        List<PhotoPath> paths = new ArrayList<>();
        for (PhotoPathModel pathModel : pathModels) {
            PhotoPath path = new PhotoPath(0, pathModel.getPhotoURI());
            paths.add(path);
        }
        return paths;
    }

    public List<PhotoPath> createListOfEquipmentPhotoPaths(EquipmentModel model) {
        List<Integer> photoId = model.getEquipmentPhotoId();
        List<PhotoPath> photos = new ArrayList<>();
        for (Integer id : photoId) {
            photos.add(getPhotoPath(id));
        }
        return photos;
    }
}
