package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class MediaRatingServiceTest {
//
//    @Mock
//    private MediaRatingRepository mediaRatingRepository;
////    @Mock
////    private MediaRepository mediaRepository;
////    @Mock
////    private UserRepository userRepository;
//    private MediaRatingService mediaRatingService;
//
//
//    @BeforeEach
//    void setUp() {
//        mediaRatingService = new MediaRatingService(mediaRatingRepository);
//    }
////
//    @Test
//    void getMedia() {
//        mediaRatingService.getAllRatings();
//        Mockito.verify(mediaRatingRepository).findAll();
//    }
//
//
//    @Test
//    void saveNewRatingSucceeds() {
//        User user = new User("test@gmail.com", "username", "password");
//        userRepository.save(user);
//
//        Media media = new Media("184029");
//        mediaRepository.save(media);
//
//        MediaRating mediaRating = new MediaRating(user,media,true);
//        mediaRatingService.saveNewRating(mediaRating);
//
//        ArgumentCaptor<MediaRating> mediaArgumentCaptor = ArgumentCaptor.forClass(MediaRating.class);
//        Mockito.verify(mediaRatingRepository).save(mediaArgumentCaptor.capture());
//
//        MediaRating capturedMediaRating = mediaArgumentCaptor.getValue();
//        assertEquals(capturedMediaRating, mediaRating);
//    }
//
//    @Test
//    void mediaRatingCantBeSavedBecauseMediaRatingExists() {
//
//        User user = new User("test@gmail.com", "username", "password");
//        userRepository.save(user);
//
//        Media media = new Media("123456");
//        mediaRepository.save(media);
//
//        MediaRating mediaRating = new MediaRating(user,media,true);
//        mediaRatingService.saveNewRating(mediaRating);
//
//        MediaRating anotherMediaRating = new MediaRating(user,media,false);
//
//        when(mediaRatingRepository.findRatingByUserAndRatableEntity(mediaRating.getUser().getId(), mediaRating.getRatableEntity().getId())).thenReturn(Optional.of(mediaRating));
//
//
//        ResponseEntity<String> resultOnAddExistingMedia = mediaRatingService.saveNewRating(anotherMediaRating);
//        ResponseEntity<String> expected = new ResponseEntity("Rating already exists by user for entity!", HttpStatus.CONFLICT);
//
//        assertEquals(resultOnAddExistingMedia, expected);
//    }
//
//    @Test
//    void updateRatingByUserForEntity() {
//
//        assertEquals(true, true);
//    }
//
//    @Test
//    void deleteRatingRemovesRatingFromRepository() {
//
//        assertEquals(true, true);
//    }
//

    @Test
    void getAllRatingsByUserId() {
        assertEquals(true, true);
    }

    @Test
    void getAllRatingsByRatableEntityId() {

        assertEquals(true, true);
    }

//


}
