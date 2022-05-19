package com.watchpad.watchpadbackend.Follow;

import com.watchpad.watchpadbackend.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void followUser() {
    }

    @Test
    void unfollowUser() {
    }

    @Test
    void isFollowingUser() {
    }
}