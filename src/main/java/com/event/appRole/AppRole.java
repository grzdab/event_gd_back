package com.event.appRole;

import com.event.role.Role;

import java.util.List;

public class AppRole {

    private String id;
    private List <Role> roleList;
//    private List<Map<String, Boolean>> privilegesRole = new ArrayList<>();


//    public List<Privileges> getPrivileges() {
//        return privilegesList;
//    }


    public AppRole(List<Role> roleList) {
        this.roleList = roleList;
    }

    public AppRole(String id, List<Role> roleList) {
        this.id = id;
        this.roleList = roleList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
//    public List<Map<String, Boolean>> getPrivilegesRole() {
//        return privilegesRole;
//    }

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
