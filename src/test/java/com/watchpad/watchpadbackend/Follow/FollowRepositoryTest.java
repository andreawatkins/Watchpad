package com.watchpad.watchpadbackend.Follow;

import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FollowRepositoryTest {

    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        followRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getFollowingList(){
        User user1 = new User("devin@test.com", "devin", "password");
        User user2 = new User("test@test.com", "test", "password");
        String username1 = "devin";
        String username2 = "test";
        Follow followObject = new Follow(username1, username2);

        userRepository.save(user1);
        userRepository.save(user2);
        followRepository.save(followObject);

        Optional<List<User>> expected = Optional.of(new ArrayList<>(List.of(user2)));
        Optional<List<User>> actual = followRepository.getFollowingList(username1);

        assertEquals(expected, actual);
    }

    @Test
    void getFollowingListShouldReturnEmptyArray(){
        User user1 = new User("devin@test.com", "devin", "password");
        User user2 = new User("test@test.com", "test", "password");

        String username1 = "devin";
        String username2 = "test";

        Follow followObject = new Follow(username1, username2);

        userRepository.save(user1);
        userRepository.save(user2);
        followRepository.save(followObject);

        Optional<List<User>> actual = followRepository.getFollowingList(username2);

        assertEquals(actual.get(), new ArrayList<>());
    }

    @Test
    void getFolloweeUsername() {
        String username1 = "devin";
        String username2 = "test";
        Follow followObject = new Follow(username1, username2);

        followRepository.save(followObject);

        Optional<String> expected = Optional.of("test");
        Optional<String> actual = followRepository.getFolloweeUsername(username1, username2);

        assertEquals(expected, actual);
    }

    @Test
    void getFolloweeUsernameShouldBeEmpty() {
        String username1 = "devin";
        String username2 = "test";
        String username3 = "randomUsername";
        Follow followObject = new Follow(username1, username2);

        followRepository.save(followObject);

        Boolean expected = true;
        Optional<String> actual = followRepository.getFolloweeUsername(username3, username2);

        assertEquals(expected, actual.isEmpty());
    }

    @Test
    void deleteByFolloweeUsername() {
        String username1 = "devin";
        String username2 = "test";
        Follow followObject = new Follow(username1, username2);

        followRepository.save(followObject);
        assertEquals(1, followRepository.count());

        followRepository.deleteByFolloweeUsername(username1, username2);
        assertEquals(0, followRepository.count());
    }
}