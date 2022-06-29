package com.event.appRole;

import com.event.appRole.dao.AppRoleModel;
import com.event.appRole.dao.AppRoleRepository;
import com.event.role.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppRoleService{

    AppRoleRepository appRoleRepository;

    public AppRoleService(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public AppRole addAppRole(AppRole appRole) {
        AppRoleModel model = new AppRoleModel();
        appRoleRepository.save(model);
        appRole.setId(model.getId());
        return appRole;
    }

    public AppRole getAppRole(int id){
        AppRoleModel model = appRoleRepository.findById(id).get();
        return createAppRole(model);
    }

    public List<AppRole> getAllAppRoles(){
        List<AppRole>appRoles = new ArrayList<>();
        Iterable<AppRoleModel> appRoleModels = appRoleRepository.findAll();
        for (AppRoleModel model: appRoleModels) {
            appRoles.add(createAppRole(model));
        }
        return appRoles;
    }

    public String deleteAppRole(int id){
        appRoleRepository.deleteById(id);
        return "Delete";
    }

    public AppRole updateAppRole(int id, AppRole newAppRole){
        AppRoleModel model = appRoleRepository.findById(id).get();
        appRoleRepository.save(model);
        return newAppRole;
    }
    private AppRole createAppRole(AppRoleModel appRoleModel){
        return new AppRole(appRoleModel.getId(),
                appRoleModel.getRoles().stream()
                .map(roleModel -> new Role(roleModel.getId(), roleModel.getName()))
                .collect(Collectors.toList()));
    }
}
