package com.watchpad.watchpadbackend.Rating;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
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
public class RatingRepositoryTest {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MediaRepository mediaRepository;


    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        mediaRepository.deleteAll();
        ratingRepository.deleteAll();
    }

    @Test
    void findRatingByUserAndMedia() {
        User user = new User("test@gmail.com", "username", "password");
        userRepository.save(user);

        Media media = new Media("184029");
        mediaRepository.save(media);

        Rating rating = new Rating(user, media,true);
        ratingRepository.save(rating);

        Optional<Rating> expect = ratingRepository.findRatingByUserAndRatableEntity(user.getId(), media.getId());
        assertTrue(expect.isPresent());
    }

    @Test
    void findAllRatingsByUser() {
        User user = new User("test@gmail.com", "username", "password");
        userRepository.save(user);

        Media media = new Media("184029");
        mediaRepository.save(media);

        Rating rating = new Rating(user, media,true);
        ratingRepository.save(rating);

        Media media2 = new Media("401728");
        mediaRepository.save(media2);

        Rating rating2 = new Rating(user, media,true);
        ratingRepository.save(rating2);

        List<Rating> expect = ratingRepository.findAllByUserId(user.getId());
        assertTrue(expect.size() == 2);
    }

}

