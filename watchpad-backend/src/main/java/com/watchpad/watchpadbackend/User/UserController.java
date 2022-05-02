package com.watchpad.watchpadbackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user){
        return userService.login(user);
    }
}
