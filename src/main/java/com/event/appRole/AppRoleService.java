package com.event.appRole;

import com.event.appRole.roleDao.AppRoleModel;
import com.event.appRole.roleDao.AppRoleRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public record AppRoleService(AppRoleRepository appRoleRepository) {

    static AppRoleMapper appRoleMapper;

    public AppRoleService(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public AppRole addRole(AppRole role) {
        AppRoleModel model = new AppRoleModel(role.getPrivilege());
        appRoleRepository.save(model);
        role.setId(model.getId());
        return role;
    }

    public AppRole getRole(UUID id){
        AppRoleModel model = appRoleRepository.findById(id).get();
        return createRole(model);
    }

    public List<AppRole> getAllRoles(){
        List<AppRole>roles = new ArrayList<>();
        Iterable<AppRoleModel> appRoleModels = appRoleRepository.findAll();
        for (AppRoleModel model: appRoleModels) {
            roles.add(createRole(model));
        }
        return roles;
    }


    public String deleteRole(UUID id){
        appRoleRepository.deleteById(id);
        return "Delete";
    }

//    public AppRole updateRole(UUID roleId, AppRole newRole){
//        AppRoleModel myRole = appRoleRepository.findById(roleId).get();
////        Optional<AppRoleModel> myRole = appRoleRepository.findById(newRole.id);
//        appRoleMapper.updateRoleFromAppRole(newRole, Optional.of(myRole));
//        appRoleRepository.save(myRole);
//        return newRole;
//    }
    public AppRole updateRole(UUID id, AppRole newRole){
        AppRoleModel model = appRoleRepository.findById(id).get();
        model.setPrivilege(newRole.getPrivilege());
        appRoleRepository.save(model);
        return newRole;
    }
    private AppRole createRole(AppRoleModel appRoleModel){
        return new AppRole(appRoleModel.getId(), appRoleModel.getPrivilege());
    }
}
