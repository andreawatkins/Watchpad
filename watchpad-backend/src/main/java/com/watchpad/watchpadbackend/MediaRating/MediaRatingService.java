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



    public ResponseEntity<List<Rating>> getMediaRatingsByMediaId(String mediaExternalId) {
        return new ResponseEntity<>(mediaRatingRepository.findAllByMediaExternalId(mediaExternalId), HttpStatus.OK);
    }


    public ResponseEntity<String> saveNewMediaRating(MediaRating mediaRating){
        try {

            //if rating already exists for Media by User, return conflict
            Optional<Rating> ratingOptional = mediaRatingRepository.findRatingByUserAndMediaId(mediaRating.getUser().getId(), mediaRating.getMedia().getId());
            if (ratingOptional.isPresent()) {
                return new ResponseEntity("Media Rating already exists by user for Media!", HttpStatus.CONFLICT);
            }


//            //If a media record does not exist yet, save Media
//            Media media;
//            Optional<Media> mediaOptional = mediaRepository.findByExternalId(mediaExternalId);
//            if(!mediaOptional.isPresent()){
//                media = new Media(mediaExternalId);
//                mediaRepository.save(media);
//            } else {
//                media = mediaOptional.get();
//            }
////            System.out.println();
////            System.out.println();
////            System.out.println(media);
////            System.out.println();
////            System.out.println();
////
////            //If user does not exist in database, return conflict
////            Optional<User> userOptional = userRepository.findById(userId);
////
////            if(!userOptional.isPresent()){
////                return new ResponseEntity("Could not find user id, Media Rating not saved!", HttpStatus.CONFLICT);
////            }


            mediaRatingRepository.save(mediaRating);
            return new ResponseEntity("Media Rating registered!", HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }

    }


}
