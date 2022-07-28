package com.event.photoPath;

import com.event.equipment.Equipment;
import com.event.equipment.EquipmentService;
import com.event.equipment.dao.EquipmentModel;
import com.event.photoPath.dao.PhotoPathModel;
import com.event.photoPath.dao.PhotoPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PhotoPathService {
    private PhotoPathRepository photoPathRepository;

    @Autowired
    public PhotoPathService(PhotoPathRepository photoPathRepository) {
        this.photoPathRepository = photoPathRepository;
    }


    public String addEquipmentPhotoPaths(List<String> photoNames) {
        List<PhotoPath> listOfPaths = addPhotoNameToList(photoNames);
        List<PhotoPathModel> pathsToSave = createListOfPhotoPathModels(listOfPaths);
        photoPathRepository.saveAll(pathsToSave);
        setIds(listOfPaths, pathsToSave);
        return "Uploaded";
    }


//    private List<PhotoPath> addPathsFromDbToList(List<Integer> pathFromDbIds) {
//        if (pathFromDbIds.isEmpty()) return null;
//        List<PhotoPath> pathsToSave = new ArrayList<>();
//        for (int pathFromDbId : pathFromDbIds) {
//            PhotoPath path = getPhotoPath(pathFromDbId);
//            if (!pathsToSave.contains(path)) {
//                pathsToSave.add(path);
//            }
//        }
//        return pathsToSave;
//    }

    private List<PhotoPath> addPhotoNameToList(List<String> photoNames) {
        if (photoNames.isEmpty()) return null;
        List<PhotoPath> paths = new ArrayList<>();
        for (String photoName : photoNames) {
            PhotoPath photoPath = new PhotoPath(0, photoName);
            paths.add(photoPath);
        }
        return paths;
    }

    private List<PhotoPath> setIds(List<PhotoPath> paths, List<PhotoPathModel> patModels) {
        if (paths == null) return null;
        for (int i = 0; i >= paths.size(); i++) {
            PhotoPath path = paths.get(i);
            PhotoPathModel model = patModels.get(i);
            path.setId(model.getId());
        }
        return paths;
    }


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

    public List<PhotoPathModel> createListOfPhotoPathModels(List<PhotoPath> paths) {
        if (paths.isEmpty()) return null;
        List<PhotoPathModel> models = new ArrayList<>();
        for (PhotoPath path : paths) {
            models.add(createPhotoPathModel(path));
        }
        return models;
    }

    private PhotoPathModel createPhotoPathModel(PhotoPath path) {
        PhotoPathModel model = new PhotoPathModel();
        model.setId(path.getId());
        model.setPhotoURI(model.getPhotoURI());
        return model;
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
