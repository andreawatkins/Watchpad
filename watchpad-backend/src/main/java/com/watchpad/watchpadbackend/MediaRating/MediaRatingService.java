package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.Rating.RatingService;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaRatingService extends RatingService {

    private final MediaRatingRepository mediaRatingRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;

    @Autowired
    public MediaRatingService(MediaRatingRepository mediaRatingRepository, MediaRepository mediaRepository, UserRepository userRepository) {
        super(mediaRatingRepository);
        this.mediaRatingRepository = mediaRatingRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> saveNewMediaRating(Long userId, Long mediaId, boolean isLiked){
        try {

            //if rating already exists for Media by User, return conflict
            Optional<Rating> ratingOptional = mediaRatingRepository.findByUserIdAndRatableEntityId(userId, mediaId);
            if (ratingOptional.isPresent()) {
                return new ResponseEntity("Rating already exists by user for this media!", HttpStatus.CONFLICT);
            }

            //If media not in media repo, save new media
            Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
            if(!mediaOptional.isPresent()){
                Media media = new Media(mediaId);
                mediaRepository.save(media);
            }

            //If user does not exist in user repo, return conflict
            Optional<User> userOptional = userRepository.findById(userId);
            if(!userOptional.isPresent()){
                return new ResponseEntity("Could not find user, media rating not saved!", HttpStatus.CONFLICT);
            }

            MediaRating newMediaRating = new MediaRating(userOptional.get(),mediaOptional.get(),isLiked);
            mediaRatingRepository.save(newMediaRating);

            return new ResponseEntity("Media rating registered!", HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}