package com.watchpad.watchpadbackend.WatchList;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class WatchListRepositoryTest {

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        watchListRepository.deleteAll();
        mediaRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findByUserIdShouldReturnTrue() {
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        userRepository.save(user);

        Media media = new Media(123123L);
        Long id = 333333L;
        mediaRepository.save(media);

        WatchListEntry watchListEntry = new WatchListEntry(id, user, media);

        watchListRepository.save(watchListEntry);
        Optional<List<WatchListEntry>> expect = watchListRepository.findByUserId(user.getId());
        assertTrue(!expect.isEmpty());
    }

    @Test
    void findByUserAndMediaShouldReturnTrue() {
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        userRepository.save(user);

        Media media = new Media(123123L);
        Long id = 333333L;
        mediaRepository.save(media);

        WatchListEntry watchListEntry = new WatchListEntry(id, user, media);

        watchListRepository.save(watchListEntry);
        Optional<WatchListEntry> expect = watchListRepository.findByUserAndMedia(user, media);
        assertTrue(expect.isPresent());

    }

    @Test
    void findByMediaIdShouldReturnTrue() {
        String username = "test";
        String email = "test@gmail.com";
        String password = "test";
        User user = new User(username, email, password);
        userRepository.save(user);

        Media media = new Media(123123L);
        Long id = 333333L;
        mediaRepository.save(media);

        WatchListEntry watchListEntry = new WatchListEntry(id, user, media);

        watchListRepository.save(watchListEntry);
        Optional<WatchListEntry> expect = watchListRepository.findByMediaId(media.getId());
        assertTrue(expect.isPresent());
    }

}
