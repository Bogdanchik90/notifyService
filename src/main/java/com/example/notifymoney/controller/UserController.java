package com.example.notifymoney.controller;

import com.example.notifymoney.repository.entity.User;
import com.example.notifymoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getUsers(@PathVariable(value = "id") Long id){
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/users/create")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        userService.createUser(newUser);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User updateUser) {
            userService.updateUser(id, updateUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable(value = "id") Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
