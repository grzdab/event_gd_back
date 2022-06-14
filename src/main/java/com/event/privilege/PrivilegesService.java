package com.event.privilege;

import com.event.privilege.dao.PrivilegesModel;
import com.event.privilege.dao.PrivilegesRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public record PrivilegesService(PrivilegesRepository privilegesRepository) {

    public PrivilegesService(PrivilegesRepository privilegesRepository) {
        this.privilegesRepository = privilegesRepository;
    }

    public Privileges addPrivileges(Privileges privileges) {
        PrivilegesModel model = new PrivilegesModel(privileges.getName());
        privilegesRepository.save(model);
        privileges.setId(model.getId());
        return privileges;
    }

    public Privileges getPrivileges(String id){
        PrivilegesModel model = privilegesRepository.findById(id).get();
        return createPrivileges(model);
    }

    public List<Privileges> getAllPrivileges(){
        List<Privileges>privileges = new ArrayList<>();
        Iterable<PrivilegesModel> privilegesModels = privilegesRepository.findAll();
        for (PrivilegesModel model: privilegesModels) {
            privileges.add(createPrivileges(model));
        }
        return privileges;
    }

    public String deletePrivileges(String id){
        privilegesRepository.deleteById(id);
        return "Delete";
    }

    public Privileges updatePrivileges(String id, Privileges newPrivileges){
        PrivilegesModel model = privilegesRepository.findById(id).get();
        model.setName(newPrivileges.getName());
        privilegesRepository.save(model);
        return newPrivileges;
    }
    private Privileges createPrivileges(PrivilegesModel privilegesModel){
        return new Privileges(privilegesModel.getId(), privilegesModel.getName(), privilegesModel.);
    }
}
