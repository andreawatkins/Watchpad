package com.watchpad.watchpadbackend.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findUserByEmail() {
        String email = "test@gmail.com";
        User user = new User(email, "username", "password");

        userRepository.save(user);
        Optional<User> expect = userRepository.findUserByEmail(email);
        assertTrue(expect.isPresent());
    }

    @Test
    void findUserByEmailShouldReturnNull() {
        String email = "test@gmail.com";
        String wrongEmail = "wrongemail@gmail.com";
        User user = new User(email, "username", "password");

        userRepository.save(user);
        Optional<User> expect = userRepository.findUserByEmail(wrongEmail);
        assertTrue(expect.isEmpty());
    }

    @Test
    void findUserByEmailOrUsername() {
        String email = "test@gmail.com";
        String username = "username";
        User user = new User(email, username, "password");

        userRepository.save(user);
        Optional<User> expectEmail = userRepository.findUserByEmailOrUsername(email, "");
        Optional<User> expectUsername = userRepository.findUserByEmailOrUsername("", username);
        Optional<User> expectEmailAndUsername = userRepository.findUserByEmailOrUsername(email, username);

        assertTrue(expectEmail.isPresent());
        assertTrue(expectUsername.isPresent());
        assertTrue(expectEmailAndUsername.isPresent());
    }

    @Test
    void findUserByEmailOrUsernameShouldBeEmpty() {
        String email = "test@gmail.com";
        String username = "username";
        String wrongEmail = "wrongemail@gmail.com";
        String wrongUsername = "wrongUsername";
        User user = new User(email, username, "password");

        userRepository.save(user);
        Optional<User> expectEmail = userRepository.findUserByEmailOrUsername(wrongEmail, "");
        Optional<User> expectUsername = userRepository.findUserByEmailOrUsername("", wrongUsername);
        Optional<User> expectEmailAndUsername = userRepository.findUserByEmailOrUsername(wrongEmail, wrongUsername);

        assertTrue(expectEmail.isEmpty());
        assertTrue(expectUsername.isEmpty());
        assertTrue(expectEmailAndUsername.isEmpty());
    }
}