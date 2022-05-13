package com.watchpad.watchpadbackend.Media;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class MediaRepositoryTest {

    @Autowired
    private MediaRepository mediaRepository;

    @AfterEach
    void tearDown() {
        mediaRepository.deleteAll();
    }

    @Test
    void findMediaById() {

        Media media = new Media(123123L);
        mediaRepository.save(media);

        Optional<Media> expect = mediaRepository.findById(123123L);
        assertTrue(expect.isPresent());
    }

    @Test
    void findMediaByIdShouldReturnNull() {
        Media media = new Media(565656L);
        mediaRepository.save(media);

        Media otherMedia = new Media(121212L);

        Optional<Media> expect = mediaRepository.findById(otherMedia.getId());
        assertTrue(expect.isEmpty());
    }
}