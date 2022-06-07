package com.watchpad.watchpadbackend.Media;

import com.watchpad.watchpadbackend.Comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public ResponseEntity<String> registerMedia(Media media) {
        try{
            Optional<Media> mediaOptional = mediaRepository.findById(media.getId());
            if (mediaOptional.isPresent()) {
                return new ResponseEntity("Media already exists with id!", HttpStatus.CONFLICT);
            }
            mediaRepository.save(media);
            return new ResponseEntity("Media registered!", HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Media>> getAllMedia() {
        return new ResponseEntity(mediaRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Media>> getMediaById(Long id) {
        Optional<Media> mediaOptional = mediaRepository.findById(id);
        if (mediaOptional.isEmpty()) {
            throw new IllegalStateException("No media exists with id!");
        }
        return new ResponseEntity(mediaOptional.get(), HttpStatus.OK);
    }
}
