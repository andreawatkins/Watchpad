package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MediaRatingRepositoryTest {
    @Autowired
    private MediaRatingRepository mediaRatingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MediaRepository mediaRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        mediaRepository.deleteAll();
        mediaRatingRepository.deleteAll();
    }

    @Test
    void findRatingByUserAndMedia() {
        User user = new User("test@gmail.com", "username", "password");
        userRepository.save(user);

        Media media = new Media(184029L);
        mediaRepository.save(media);

        Rating rating = new MediaRating(user, media,true);
        mediaRatingRepository.save(rating);

        Optional<Rating> expect = mediaRatingRepository.findByUserIdAndRatableEntityId(user.getId(), media.getId());

        assertTrue(expect.isPresent());
        assertTrue(expect.get().getIsLiked());
    }

    @Test
    void findAllRatingsByUser() {
        User user = new User("test@gmail.com", "username", "password");
        userRepository.save(user);

        Media media = new Media(123123L);
        mediaRepository.save(media);

        Rating rating = new MediaRating(user, media,true);
        mediaRatingRepository.save(rating);

        Media media2 = new Media(444444L);
        mediaRepository.save(media2);

        Rating rating2 = new MediaRating(user, media2,false);
        mediaRatingRepository.save(rating2);

        Optional<List<Rating>> expect = mediaRatingRepository.findAllByUserId(user.getId());
        assertTrue(expect.get().size() == 2);

    }

    @Test
    void findAllRatingsByEntityId() {
        User user = new User("test@gmail.com", "username", "password");
        userRepository.save(user);

        User user2 = new User("test2@gmail.com", "username2", "password2");
        userRepository.save(user2);

        Media media = new Media(111111L);
        mediaRepository.save(media);

        Rating rating = new MediaRating(user, media,true);
        mediaRatingRepository.save(rating);

        Rating rating2 = new MediaRating(user2, media,false);
        mediaRatingRepository.save(rating2);

        Optional<List<Rating>> expect = mediaRatingRepository.findAllByRatableEntityId(media.getId());

        System.out.println("Test Ratings: ");
        for (Rating r : expect.get()) {
            System.out.println("\t" + r.toString());
        }

        assertTrue(expect.get().size() == 2);

    }

}

