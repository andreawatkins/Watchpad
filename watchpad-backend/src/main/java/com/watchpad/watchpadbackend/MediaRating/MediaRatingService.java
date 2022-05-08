package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaRatingService extends RatingService {

    private final MediaRatingRepository mediaRatingRepository;

    @Autowired
    public MediaRatingService(MediaRatingRepository mediaRatingRepository) {
        super(mediaRatingRepository);
        this.mediaRatingRepository = mediaRatingRepository;
    }


}
