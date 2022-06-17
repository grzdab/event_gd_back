package com.event.privilege;

import com.event.privilege.dao.PrivilegeModel;
import com.event.privilege.dao.PrivilegeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegeService {

    PrivilegeRepository privilegesRepository;

    public PrivilegeService(PrivilegeRepository privilegesRepository) {
        this.privilegesRepository = privilegesRepository;
    }

    public Privilege addPrivileges(Privilege privileges) {
        PrivilegeModel model = new PrivilegeModel(privileges.getName(), privileges.getPrivilegesEnum());
        privilegesRepository.save(model);
        privileges.setId(model.getId());
        return privileges;
    }

    public Privilege getPrivileges(int id){
        PrivilegeModel model = privilegesRepository.findById(id).get();
        return createPrivileges(model);
    }

    public List<Privilege> getAllPrivileges(){
        List<Privilege>privileges = new ArrayList<>();
        Iterable<PrivilegeModel> privilegesModels = privilegesRepository.findAll();
        for (PrivilegeModel model: privilegesModels) {
            privileges.add(createPrivileges(model));
        }
        return privileges;
    }

    public String deletePrivileges(int id){
        privilegesRepository.deleteById(id);
        return "Delete";
    }

    public Privilege updatePrivileges(int id, Privilege newPrivileges){
        PrivilegeModel model = privilegesRepository.findById(id).get();
        model.setName(newPrivileges.getName());
        model.setType(newPrivileges.getPrivilegesEnum());
        privilegesRepository.save(model);
        return newPrivileges;
    }
    private Privilege createPrivileges(PrivilegeModel privilegesModel){
        return new Privilege(privilegesModel.getId(), privilegesModel.getName(), privilegesModel.getType());
    }
}
