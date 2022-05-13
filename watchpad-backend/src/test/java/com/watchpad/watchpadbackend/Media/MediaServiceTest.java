package com.watchpad.watchpadbackend.Media;

import com.watchpad.watchpadbackend.User.User;
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
public class MediaServiceTest {

    @Mock
    private MediaRepository mediaRepository;
    private MediaService mediaService;

    @BeforeEach
    void setUp() {
        mediaService = new MediaService(mediaRepository);
    }

    @Test
    void getMedia() {
        mediaService.getAllMedia();
        Mockito.verify(mediaRepository).findAll();
    }


    @Test
    void registerMedia() {
        Media media = new Media(123456L);

        mediaService.registerMedia(media);
        ArgumentCaptor<Media> userArgumentCaptor = ArgumentCaptor.forClass(Media.class);
        Mockito.verify(mediaRepository).save(userArgumentCaptor.capture());

        Media capturedMedia = userArgumentCaptor.getValue();
        assertEquals(capturedMedia, media);
    }

    @Test
    void mediaCantBeRegisteredBecauseMediaExists() {
        Media media = new Media(123456L);
        when(mediaRepository.findById(media.getId())).thenReturn(Optional.of(media));

        ResponseEntity<String> result = mediaService.registerMedia(media);
        ResponseEntity<String> expected = new ResponseEntity("Media already exists with id!", HttpStatus.CONFLICT);
        assertEquals(result, expected);
    }

    @Test
    void mediaCantBeAddedBecauseOfIllegalArgumentException() {
        Media media = new Media(123336L);
        IllegalArgumentException ex = new IllegalArgumentException("error");

        when(mediaRepository.save(media)).thenThrow(ex);

        ResponseEntity<String> result = mediaService.registerMedia(media);
        ResponseEntity<String> expected = new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        assertEquals(expected, result);
    }

}
