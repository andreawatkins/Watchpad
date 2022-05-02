package com.watchpad.watchpadbackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addUser(User user) {
        try {
            Optional<User> queriedUser = userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername());
            if (queriedUser.isPresent()) {
                return new ResponseEntity("User already exists!", HttpStatus.CONFLICT);
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            user.setPhoto("default-photo");
            userRepository.save(user);
            return new ResponseEntity<>("User created!", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> login(User user){
        Optional<User> queriedUser = userRepository.findUserByEmail(user.getEmail());
        if(queriedUser.isEmpty()) {
            return new ResponseEntity<>("This email does not exist in our database.", HttpStatus.BAD_REQUEST);
        }

        if(!passwordEncoder().matches(user.getPassword(), queriedUser.get().getPassword())){
            return new ResponseEntity<>("Passwords don't match!", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(queriedUser, HttpStatus.OK);

    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}