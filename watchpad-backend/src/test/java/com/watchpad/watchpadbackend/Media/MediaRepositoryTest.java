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

        Optional<Media> expect = mediaRepository.findMediaByIdOrExternalId(media.getId(),"123123");
        assertTrue(expect.isPresent());
    }

    @Test
    void findMediaByExternalId() {
        Long externalId = 111111L;
        Media media = new Media(externalId);
        media.setExternalId(externalId.toString());

        mediaRepository.save(media);
        System.out.println(mediaRepository.findAll());

        Optional<Media> expect = mediaRepository.findByExternalId(externalId.toString());
        assertTrue(expect.isPresent());
    }

    @Test
    void findMediaByIdOrExternalId() {
        Long externalId = 456234L;
        Media media = new Media(externalId);
        media.setExternalId(externalId.toString());

        mediaRepository.save(media);
        Optional<Media> expect = mediaRepository.findMediaByIdOrExternalId(media.getId(), "456234");
        assertTrue(expect.isPresent());
    }

    @Test
    void findMediaByIdShouldReturnNull() {
        Media media = new Media(565656L);
        mediaRepository.save(media);

        Media otherMedia = new Media();

        Optional<Media> expect = mediaRepository.findMediaByIdOrExternalId(otherMedia.getId(),otherMedia.getExternalId());
        assertTrue(expect.isEmpty());
    }


}
