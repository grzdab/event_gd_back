package com.event.appRole;

import com.event.privilege.Privileges;
import com.event.role.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppRole {

    private List <Role> roleList;
    private List<Map<String, Boolean>> privilegesRole = new ArrayList<>();


//    public List<Privileges> getPrivileges() {
//        return privilegesList;
//    }

    public List<Role> getRole() {
        return roleList;
    }

    public List<Map<String, Boolean>> getPrivilegesRole() {
        return privilegesRole;
    }

//    public void addToPrivilegesList(Privileges privileges){
//        privilegesList.add(privileges);
//    }
//    public void deletePrivilegesFromList(Privileges privileges){
//        for (int i=0; privilegesList.size()>i;i++){
//            if (privilegesList.get(i).equals(privileges)){
//                privilegesList.remove(privileges);
//            }
//        }
//    }
    public void addToRoleList(Role role){
        roleList.add(role);
    }
    public void deleteRoleFromList(Role role){
        for (int i=0; roleList.size()>i;i++){
            if (roleList.get(i).equals(role)){
                roleList.remove(role);
            }
        }
    }


}
