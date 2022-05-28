package com.watchpad.watchpadbackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init(){
        if(userRepository.findAll().isEmpty()){
            userRepository.save(new User(1L, "test@test.com", "test", new BCryptPasswordEncoder().encode("test"), "default-photo"));
        }
    }
}
