package com.watchpad.watchpadbackend.Rating;

import com.watchpad.watchpadbackend.Media.Media;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public abstract class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public ResponseEntity<List<Rating>> getAllRatings() {
        return new ResponseEntity<>(ratingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Rating>> getRatingsByUserId(Long userId) {
        return new ResponseEntity<>(ratingRepository.findAllByUserId(userId), HttpStatus.OK);
    }

//    public ResponseEntity<String> saveNewRating(Rating rating) {
//        try {
//            Optional<Rating> ratingOptional = ratingRepository.findRatingByUserAndRatableEntity(rating.getUser().getId(), rating.getRatableEntity().getId());
//            if (ratingOptional.isPresent()) {
//                return new ResponseEntity("Rating already exists by user for entity!", HttpStatus.CONFLICT);
//            }
//            ratingRepository.save(rating);
//            return new ResponseEntity<>("Rating registered!", HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
//        }
//    }

//    public ResponseEntity<String> deleteRating(Long userId, Long ratableEntityId) {
//        try{
//            Optional<Rating> ratingOptional = ratingRepository.findRatingByUserAndRatableEntity(userId, ratableEntityId);
//            if (ratingOptional.isPresent()) {
//                ratingRepository.delete(ratingOptional.get());
//                return new ResponseEntity("Rating deleted!", HttpStatus.OK);
//
//            } else {
//                return new ResponseEntity("No rating found by user for entity!", HttpStatus.NOT_FOUND);
//            }
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
//        }
//    }

//    public ResponseEntity<String> updateRating(Long userId, Long ratableEntityId, boolean isLiked) {
//        try {
//            Optional<Rating> ratingOptional = ratingRepository.findRatingByUserAndRatableEntity(userId, ratableEntityId);
//            if (ratingOptional.isPresent()) {
//                Rating ratingToUpdate = ratingOptional.get();
//                ratingToUpdate.setIsLiked(isLiked);
//                return new ResponseEntity("Rating updated!", HttpStatus.OK);
//
//            } else {
//                return new ResponseEntity("No rating found by user for entity!", HttpStatus.NOT_FOUND);
//            }
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
//        }
//
//    }

}
