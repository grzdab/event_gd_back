package com.event.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userId, @RequestBody User newUser) {
        User updateUser = service.updateUser(userId, newUser);
        if (updateUser == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateUser);
        }
//        return service.updateUser(roleId, newRole);
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable UUID userId) {
        return service.getUser(userId);
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable UUID userId) {
        return service.deleteUser(userId);
    }
}