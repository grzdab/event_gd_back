package com.event.appRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AppRoleController {

    private final AppRoleService service;

    @Autowired
    public AppRoleController(AppRoleService service) {
        this.service = service;
    }

    @PostMapping("/admin/role")
    public AppRole addRole(@RequestBody AppRole role) {
        return service.addRole(role);
    }

    @PutMapping("/admin/role/{roleId}")
    public ResponseEntity<Object> updateRole(@PathVariable UUID roleId, @RequestBody AppRole newRole) {
        AppRole updateRole = service.updateRole(roleId, newRole);
        if (updateRole == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateRole);
        }
//        return service.updateRole(roleId, newRole);
    }

    @GetMapping("/admin/role/{roleId}")
    public AppRole getRole(@PathVariable UUID roleId) {
        return service.getRole(roleId);
    }

    @GetMapping("/admin/role")
    public List<AppRole> getAllRoles(){return service.getAllRoles();}

    @DeleteMapping("/admin/role/{roleId}")
    public String deleteRole(@PathVariable UUID roleId) {
        return service.deleteRole(roleId);
    }
}

