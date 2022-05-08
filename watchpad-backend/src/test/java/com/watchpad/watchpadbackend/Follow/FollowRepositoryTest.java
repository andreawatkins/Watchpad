package com.watchpad.watchpadbackend.Follow;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        followRepository.deleteAll();
    }

    @Test
    void getFollowingList(){
        Long id_1 = 1L;
        Long id_2 = 2L;
        Follow followObject = new Follow(id_1, id_2);

        followRepository.save(followObject);

        List<Long> expected = new ArrayList<>(List.of(id_2));
        List<Long> actual = followRepository.getFollowingList(id_1);

        assertTrue(expected.equals(actual));
    }

    @Test
    void getFollowingListShouldReturnNull(){
        Long id_1 = 1L;
        Long id_2 = 2L;
        Long id_3 = 3L;
        Follow followObject = new Follow(id_1, id_2);

        followRepository.save(followObject);

        List<Long> actual = followRepository.getFollowingList(id_3);

        assertTrue(actual.isEmpty());
    }
}