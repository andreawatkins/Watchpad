package com.watchpad.watchpadbackend.Media;

import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.Rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void reigsterMedia(Media media) {
        Optional<Media> mediaOptional = mediaRepository.findByMediaId(media.getId());
        if (mediaOptional.isPresent()) {
            throw new IllegalStateException("Media already exists with id.");
        }
            mediaRepository.save(mediaOptional.get());
        }
}
