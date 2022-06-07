package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.MediaLike.MediaLike;
import com.watchpad.watchpadbackend.MediaLike.MediaLikeRepository;
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
    private MediaLikeRepository mediaLikeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MediaRepository mediaRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        mediaRepository.deleteAll();
        mediaLikeRepository.deleteAll();
    }

    @Test
    void findRatingByUserAndMedia() {
        User user = new User("test@gmail.com", "username", "password");
        userRepository.save(user);

        Media media = new Media(184029L);
        mediaRepository.save(media);

        Rating rating = new MediaLike(user,true);
        rating.setRatableEntity(media);
        mediaLikeRepository.save(rating);

        Optional<MediaLike> expect = mediaLikeRepository.getMediaLikeByUserIdAndMediaId(user.getId(), media.getId());

        assertTrue(expect.isPresent());
        assertTrue(expect.get().getIsLiked());
    }

    @Test
    void findAllRatingsByUser() {
        User user = new User("test@gmail.com", "username", "password");
        userRepository.save(user);

        Media media = new Media(123123L);
        mediaRepository.save(media);

        Rating rating = new MediaLike(user,true);
        rating.setRatableEntity(media);
        mediaLikeRepository.save(rating);

        Media media2 = new Media(444444L);
        mediaRepository.save(media2);

        Rating rating2 = new MediaLike(user,true);
        rating2.setRatableEntity(media2);
        mediaLikeRepository.save(rating2);

        Optional<List<MediaLike>> expect = mediaLikeRepository.getAllMediaLikesByUserId(user.getId());
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

        Rating rating = new MediaLike(user,true);
        rating.setRatableEntity(media);
        mediaLikeRepository.save(rating);

        Rating rating2 = new MediaLike(user2,true);
        rating2.setRatableEntity(media);
        mediaLikeRepository.save(rating2);

        Optional<List<MediaLike>> expect = mediaLikeRepository.getAllMediaLikesByRatableEntityId(media.getId());

        System.out.println("Test Ratings: ");
        for (Rating r : expect.get()) {
            System.out.println("\t" + r.toString());
        }

        assertTrue(expect.get().size() == 2);

    }

}

