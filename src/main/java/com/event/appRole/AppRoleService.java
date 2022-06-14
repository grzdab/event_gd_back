package com.event.appRole;

import com.event.appRole.dao.AppRoleModel;
import com.event.appRole.dao.AppRoleRepository;
import com.event.role.Role;
import com.event.role.roleDao.RoleModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record AppRoleService(AppRoleRepository appRoleRepository) {


    public AppRoleService(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public AppRole addAppRole(AppRole appRole) {
        AppRoleModel model = new AppRoleModel((RoleModel) appRole.getRoleList());
        appRoleRepository.save(model);
        appRole.setId(model.getId());
        return appRole;
    }

    public AppRole getAppRole(String id){
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

    public String deleteAppRole(String id){
        appRoleRepository.deleteById(id);
        return "Delete";
    }

    public AppRole updateAppRole(String id, AppRole newAppRole){
        AppRoleModel model = appRoleRepository.findById(id).get();
        appRoleRepository.save(model);
        return newAppRole;
    }
    private AppRole createAppRole(AppRoleModel appRoleModel){
        return new AppRole(appRoleModel.getId(), (List<Role>) appRoleModel.getRole());
    }
}
