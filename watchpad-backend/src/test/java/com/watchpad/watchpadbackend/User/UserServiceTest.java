package com.watchpad.watchpadbackend.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void getUsers() {
        userService.getAllUsers();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void addUser() {
        User user = new User("test@gmail.com", "username", "password");

        userService.addUser(user);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(capturedUser, user);
    }

    @Test
    void userCantBeAddedBecauseUserExists() {
        User user = new User("test@gmail.com", "username", "password");
        when(userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername())).thenReturn(Optional.of(user));
        when(userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername())).thenReturn(Optional.of(user));


        ResponseEntity<String> result = userService.addUser(user);
        ResponseEntity<String> expected = new ResponseEntity("User Exists", HttpStatus.CONFLICT);
        assertEquals(result, expected);
    }

    @Test
    void userCantBeAddedBecauseOfIllegalArgumentException() {
        User user = new User("test@gmail.com", "username", "password");
        IllegalArgumentException ex = new IllegalArgumentException("some error");

        when(userRepository.save(user)).thenThrow(ex);

        ResponseEntity<String> result = userService.addUser(user);
        ResponseEntity<String> expected = new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        assertEquals(expected, result);
    }

    @Test
    void loginShouldBeSuccessful() {
        User user = new User("test@gmail.com", "username", "$2a$10$UoGCN5fLF5VxbJJqgniWV.gdusUWMB0tI.RtYmqMJq/OvKZs8h1Nq");
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User userCredentials = new User("test@gmail.com", "", "password");

        ResponseEntity<String> result = userService.login(userCredentials);
        ResponseEntity<String> expected = new ResponseEntity<>("User logged in!", HttpStatus.OK);
        assertEquals(expected, result);
    }

    @Test
    void loginShouldFailBecauseUserDoesNotExist() {
        User user = new User("test@gmail.com", "username", "$2a$10$UoGCN5fLF5VxbJJqgniWV.gdusUWMB0tI.RtYmqMJq/OvKZs8h1Nq");
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());


        ResponseEntity<String> result = userService.login(user);
        ResponseEntity<String> expected = new ResponseEntity<>("Email is not in our database", HttpStatus.BAD_REQUEST);
        assertEquals(expected, result);
    }

    @Test
    void loginShouldFailBecausePasswordsDoNotMatch() {
        User user = new User("test@gmail.com", "username", "$2a$10$UoGCN5fLF5VxbJJqgniWV.gdusUWMB0tI.RtYmqMJq/OvKZs8h1Nq");
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User userCredentials = new User("test@gmail.com", "", "password2");

        ResponseEntity<String> result = userService.login(userCredentials);
        ResponseEntity<String> expected = new ResponseEntity<>("Passwords don't match!", HttpStatus.CONFLICT);
        assertEquals(expected, result);
    }
}