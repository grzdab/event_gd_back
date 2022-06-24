package com.event.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RoleController {

    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping("/admin/role")
    public Role addRole(@RequestBody Role role) {
        return service.addRole(role);
    }

    @PutMapping("/admin/role/{roleId}")
    public ResponseEntity<Object> updateRole(@PathVariable int roleId, @RequestBody Role newRole) {
        Role updateRole = service.updateRole(roleId, newRole);
        if (updateRole == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateRole);
        }
//        return service.updateRole(roleId, newRole);
    }

    @GetMapping("/admin/role/{roleId}")
    public Role getRole(@PathVariable int roleId) {
        return service.getRole(roleId);
    }

    @GetMapping("/admin/role")
    public List<Role> getAllRoles(){return service.getAllRoles();}

    @DeleteMapping("/admin/role/{roleId}")
    public String deleteRole(@PathVariable int roleId) {
        return service.deleteRole(roleId);
    }
}

