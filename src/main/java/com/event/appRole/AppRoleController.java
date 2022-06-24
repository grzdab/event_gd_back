package com.event.appRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppRoleController {

    private final AppRoleService service;
    @Autowired
    public AppRoleController(AppRoleService service) {
        this.service = service;
    }

    @GetMapping("/appRole/{id}")
    public AppRole getAppRole(@PathVariable int id) {
        return service.getAppRole(id);
    }

    @GetMapping("/appRole")
    public List<AppRole> getAllAppRoles() {
        return service.getAllAppRoles();
    }

    @PostMapping("/appRole")
    public AppRole addAppRole(@RequestBody AppRole appRole) {
        return service.addAppRole(appRole);
    }

    @PutMapping("/appRole/{id}")
    public AppRole updateAppRole(@PathVariable int id, @RequestBody AppRole newAppRole) {
        return service.updateAppRole(id, newAppRole);
    }

    @DeleteMapping("/appRole/{id}")
    public String deleteAppRole(@PathVariable int id) {
        return service.deleteAppRole(id);
    }

}
