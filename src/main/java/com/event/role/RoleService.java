package com.event.role;

import com.event.role.roleDao.RoleModel;
import com.event.role.roleDao.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public record RoleService(RoleRepository appRoleRepository) {

    public RoleService(RoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public Role addRole(Role role) {
        RoleModel model = new RoleModel(role.getName());
        appRoleRepository.save(model);
        role.setId(model.getId());
        return role;
    }

    public Role getRole(UUID id){
        RoleModel model = appRoleRepository.findById(id).get();
        return createRole(model);
    }

    public List<Role> getAllRoles(){
        List<Role>roles = new ArrayList<>();
        Iterable<RoleModel> appRoleModels = appRoleRepository.findAll();
        for (RoleModel model: appRoleModels) {
            roles.add(createRole(model));
        }
        return roles;
    }


    public String deleteRole(UUID id){
        appRoleRepository.deleteById(id);
        return "Delete";
    }

    public Role updateRole(UUID id, Role newRole){
        RoleModel model = appRoleRepository.findById(id).get();
        model.setName(newRole.getName());
        appRoleRepository.save(model);
        return newRole;
    }
    private Role createRole(RoleModel roleModel){
        return new Role(roleModel.getId(), roleModel.getName());
    }
}
