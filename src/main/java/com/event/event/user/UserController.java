package com.event.event.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @PutMapping("/user/{userId}")
    User updateUser(@PathVariable int userId, @RequestBody User newUser) {
        return service.updateUser(userId, newUser);
    }

    @GetMapping("/user/{userId}")
    User getUser(@PathVariable int userId) {
        return service.getUser(userId);
    }

    @DeleteMapping("/user/{userId}")
    String deleteUser(@PathVariable int userId) {
        return service.deleteUser(userId);
    }
}
