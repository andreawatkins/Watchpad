package com.watchpad.watchpadbackend.Follow;

import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {
    @Mock
    private FollowRepository followRepository;

    @Mock
    private UserRepository userRepository;

    private FollowService followService;

    @BeforeEach
    void setUp() {
        followService = new FollowService(followRepository, userRepository);
    }

    @Test
    void getAllFollowerInformation() {
        followService.getAllFollowerInformation();
        Mockito.verify(followRepository).findAll();
    }

    @Test
    void getFollowingList() {
        User user = new User("test@gmail.com", "test", "password");

        when(followRepository.getFollowingList("test")).thenReturn(Optional.of(List.of(user)));

        followRepository.getFollowingList("test");
        ResponseEntity<List<User>> actual = followService.getFollowingList("test");
        ResponseEntity<List<User>> expected = new ResponseEntity<>(List.of(user), HttpStatus.OK);

        assertEquals(expected, actual);

    }

    @Test
    void getFollowingListShouldReturnAnEmptyList() {
        when(followRepository.getFollowingList("test")).thenReturn(Optional.of(new ArrayList<>()));

        followRepository.getFollowingList("test");
        ResponseEntity<List<User>> actual = followService.getFollowingList("test");
        ResponseEntity<List<User>> expected = new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

        assertEquals(expected, actual);

    }

    @Test
    void followUser() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        when(followRepository.getFolloweeUsername(user1.getUsername(), user2.getUsername())).thenReturn(Optional.empty());

        followService.followUser(followData);
        ArgumentCaptor<Follow> followArgumentCaptor = ArgumentCaptor.forClass(Follow.class);
        Mockito.verify(followRepository).save(followArgumentCaptor.capture());

        Follow capturedFollowData = followArgumentCaptor.getValue();
        assertEquals(followData, capturedFollowData);
        ResponseEntity<String> actual = followService.followUser(followData);
        ResponseEntity<String> expected = new ResponseEntity<>("(test) is now following (test2)", HttpStatus.OK);
        assertEquals(expected, actual);

    }

    @Test
    void userIsAlreadyFollowingFollowee() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        when(followRepository.getFolloweeUsername(user1.getUsername(), user2.getUsername())).thenReturn(Optional.of(user2.getUsername()));

        ResponseEntity<String> actual = followService.followUser(followData);
        ResponseEntity<String> expected = new ResponseEntity<>("(test) is already following (test2)", HttpStatus.CONFLICT);
        assertEquals(expected, actual);
    }

    @Test
    void followUserShouldThrowAnException() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        when(followRepository.getFolloweeUsername(user1.getUsername(), user2.getUsername())).thenThrow(new IllegalArgumentException("my exception"));

        ResponseEntity<String> actual = followService.followUser(followData);
        ResponseEntity<String> expected = new ResponseEntity<>("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        assertEquals(expected, actual);
    }

    @Test
    void unfollowUser() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        ResponseEntity<String> actual = followService.unfollowUser(followData);
        ResponseEntity<String> expected = new ResponseEntity<>("(test) is no longer following (test2)", HttpStatus.OK);
        assertEquals(expected, actual);

    }
    @Test
    void unfollowUserShouldThrowAnException() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        doThrow(new IllegalArgumentException("my exception")).when(followRepository).deleteByFolloweeUsername(user1.getUsername(), user2.getUsername());

        ResponseEntity<String> actual = followService.unfollowUser(followData);
        ResponseEntity<String> expected = new ResponseEntity<>("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        assertEquals(expected, actual);

    }

    @Test
    void isFollowingUser() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        when(followRepository.getFolloweeUsername(user1.getUsername(), user2.getUsername())).thenReturn(Optional.of("test2"));

        ResponseEntity<Object> actual = followService.isFollowingUser(followData);
        ResponseEntity<Boolean> expected = new ResponseEntity<>(true, HttpStatus.OK);
        assertEquals(expected, actual);
    }
    @Test
    void isFollowingUserShouldReturnFalse() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        when(followRepository.getFolloweeUsername(user1.getUsername(), user2.getUsername())).thenReturn(Optional.empty());

        ResponseEntity<Object> actual = followService.isFollowingUser(followData);
        ResponseEntity<Boolean> expected = new ResponseEntity<>(false, HttpStatus.OK);
        assertEquals(expected, actual);
    }

    @Test
    void isFollowingUserShouldThrowException() {
        User user1 = new User("test@gmail.com", "test", "password");
        User user2 = new User("test2@gmail.com", "test2", "password");
        Follow followData = new Follow(user1.getUsername(), user2.getUsername());

        when(followRepository.getFolloweeUsername(user1.getUsername(), user2.getUsername())).thenThrow(new IllegalArgumentException("my exception"));

        ResponseEntity<Object> actual = followService.isFollowingUser(followData);
        ResponseEntity<String> expected = new ResponseEntity<>("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        assertEquals(expected, actual);
    }
}