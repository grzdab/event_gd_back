package com.event.role;

import com.event.role.roleDao.RoleModel;
import com.event.role.roleDao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    RoleRepository appRoleRepository;

    @Autowired
    public RoleService(RoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public Role addRole(Role role) {
        RoleModel model = new RoleModel(role.getName());
        appRoleRepository.save(model);
        role.setId(model.getId());
        return role;
    }

    public Role getRole(int id){
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

    public String deleteRole(int id){
        appRoleRepository.deleteById(id);
        return "Delete";
    }

    public Role updateRole(int id, Role newRole){
        RoleModel model = appRoleRepository.findById(id).get();
        model.setName(newRole.getName());
        appRoleRepository.save(model);
        return newRole;
    }

    private Role createRole(RoleModel roleModel){
        return new Role(roleModel.getId(), roleModel.getName());
    }
}
