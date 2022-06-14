package com.event.appRole;

import com.event.role.Role;

import java.util.List;

public class AppRole {

    private int id;
    private List <Role> roleList;

    public AppRole(List<Role> roleList) {
        this.roleList = roleList;
    }

    public AppRole(int id, List<Role> roleList) {
        this.id = id;
        this.roleList = roleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

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
