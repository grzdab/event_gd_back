package com.event.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrivilegeController {
    private final PrivilegeService service;

    @Autowired
    public PrivilegeController(PrivilegeService service) {
        this.service = service;
    }

    @PostMapping("/admin/privileges")
    public Privilege addPrivileges(@RequestBody Privilege privileges) {
        return service.addPrivileges(privileges);
    }

    @PutMapping("/admin/privileges/{privilegesId}")
    public ResponseEntity<Object> updatePrivileges(@PathVariable String privilegesId, @RequestBody Privilege newPrivileges) {
        Privilege updatePrivileges = service.updatePrivileges(privilegesId, newPrivileges);
        if (updatePrivileges == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatePrivileges);
        }
//        return service.updateRole(roleId, newRole);
    }

    @GetMapping("/admin/privileges/{privilegesId}")
    public Privilege getPrivileges(@PathVariable String privilegesId) {
        return service.getPrivileges(privilegesId);
    }

    @GetMapping("/admin/privileges")
    public List<Privilege> getAllRoles(){return service.getAllPrivileges();}

    @DeleteMapping("/admin/privileges/{privilegesId}")
    public String deletePrivileges(@PathVariable String privilegesId) {
        return service.deletePrivileges(privilegesId);
    }
}
