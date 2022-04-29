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
                return new ResponseEntity("User Exists", HttpStatus.CONFLICT);
            }
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            user.setPhoto("default-photo");
            userRepository.save(user);
            return new ResponseEntity<>("User Created", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> login(User user){
        Optional<User> queriedUser = userRepository.findUserByEmail(user.getEmail());
        if(queriedUser.isEmpty()) {
            return new ResponseEntity<>("Email is not in our database", HttpStatus.BAD_REQUEST);
        }

        if(!passwordEncoder().matches(user.getPassword(), queriedUser.get().getPassword())){
            return new ResponseEntity<>("Passwords don't match!", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("User logged in!", HttpStatus.OK);

    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}