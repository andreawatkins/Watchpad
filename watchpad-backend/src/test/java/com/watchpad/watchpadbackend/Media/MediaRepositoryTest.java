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

        Media media = new Media();
        mediaRepository.save(media);

        Optional<Media> expect = mediaRepository.findMediaByIdOrExternalId(media.getId(),"");
        assertTrue(expect.isPresent());
    }

    @Test
    void findMediaByExternalId() {
        String externalId = "123456";
        Media media = new Media(externalId);

        mediaRepository.save(media);
        Optional<Media> expect = mediaRepository.findByExternalId(externalId);
        assertTrue(expect.isPresent());
    }

    @Test
    void findMediaByIdOrExternalId() {
        String externalId = "456234";
        Media media = new Media(externalId);

        mediaRepository.save(media);
        Optional<Media> expect = mediaRepository.findMediaByIdOrExternalId(media.getId(), externalId);
        assertTrue(expect.isPresent());
    }

    @Test
    void findMediaByIdShouldReturnNull() {
        Media media = new Media();
        mediaRepository.save(media);

        Media otherMedia = new Media();

        Optional<Media> expect = mediaRepository.findMediaByIdOrExternalId(otherMedia.getId(),otherMedia.getExternalId());
        assertTrue(expect.isEmpty());
    }

    @Test
    void findMediaByExternalIdShouldReturnNull() {
        String externalId = "123456";
        String wrongExternalId = "345234";

        Media media = new Media(externalId);

        mediaRepository.save(media);
        Optional<Media> expect = mediaRepository.findByExternalId(wrongExternalId);
        assertTrue(expect.isEmpty());
    }

}
