package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.lang.Float;

@Service
public class MediaRatingService {

    private final MediaRatingRepository mediaRatingRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;

    @Autowired
    public MediaRatingService(MediaRatingRepository mediaRatingRepository,
                              MediaRepository mediaRepository,
                              UserRepository userRepository) {
        this.mediaRatingRepository = mediaRatingRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<String> saveOrUpdateMediaRating(Long userId, Long mediaId, Float rating){
        try {
            //If user does not exist in user repo, return conflict
            Optional<User> userOptional = userRepository.findById(userId);
            if(!userOptional.isPresent()){
                return new ResponseEntity("Could not find user, media rating not saved!", HttpStatus.CONFLICT);
            }

            //if rating already exists for Media by User, update rating
            Optional<MediaRating> mediaRatingOptional = mediaRatingRepository.getMediaRatingByUserIdAndMediaId(userId, mediaId);
            if (mediaRatingOptional.isPresent()) {
                mediaRatingRepository.setRating(rating, mediaRatingOptional.get().getId());
                return new ResponseEntity("Existing rating updated with new value!", HttpStatus.CREATED);
            }

            //If media not in media repo, save new media
            Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
            if(!mediaOptional.isPresent()){
                Media media = new Media(mediaId);
                mediaRepository.save(media);

                //Try to pull new media from repo
                mediaOptional = mediaRepository.findById(mediaId);
            }

            if(mediaOptional.isPresent()){
                MediaRating newMediaRating = new MediaRating(userOptional.get(),rating);
                newMediaRating.setRatableEntity(mediaOptional.get());
                mediaRatingRepository.save(newMediaRating);
                return new ResponseEntity("Media rating registered!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity("Could not save media id in media repo, rating not saved!", HttpStatus.CONFLICT);
            }

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<Float>> getAverageRatingForMedia(Long mediaId) {
        return new ResponseEntity<>(mediaRatingRepository.getAverageRatingForRatableEntityId(mediaId), HttpStatus.OK);
    }

    public ResponseEntity<Optional<List<MediaRating>>> getRatingsByUserId(Long userId) {
        return new ResponseEntity<>(mediaRatingRepository.getAllMediaRatingsByUserId(userId), HttpStatus.OK);
    }

    public ResponseEntity<Optional<List<MediaRating>>> getRatingsByRatableEntityId(Long mediaId) {
        return new ResponseEntity<>(mediaRatingRepository.getAllMediaRatingsByRatableEntityId(mediaId), HttpStatus.OK);
    }

    public ResponseEntity<Optional<MediaRating>> getRating(Long userId, Long mediaId) {
        return new ResponseEntity<>(mediaRatingRepository.getMediaRatingByUserIdAndMediaId(userId, mediaId), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteRating(Long userId, Long mediaId) {
        Optional<MediaRating> mediaRatingOptional = mediaRatingRepository.getMediaRatingByUserIdAndMediaId(userId, mediaId);
        if (mediaRatingOptional.isPresent()) {
            mediaRatingRepository.deleteById(userId, mediaId);
            return new ResponseEntity<>("Media rating deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No existing media rating to delete!", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<List<Rating>> getAllRatings() {
        return new ResponseEntity<>(mediaRatingRepository.findAll(), HttpStatus.OK);
    }
}