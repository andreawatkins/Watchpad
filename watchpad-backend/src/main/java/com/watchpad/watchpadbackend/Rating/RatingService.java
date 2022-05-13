package com.watchpad.watchpadbackend.Rating;

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

    public ResponseEntity<Optional<List<Rating>>> getRatingsByUserId(Long userId) {
        return new ResponseEntity<>(ratingRepository.findAllByUserId(userId), HttpStatus.OK);
    }

    public ResponseEntity<Optional<List<Rating>>> getRatingsByRatableEntityId(Long ratableEntityId) {
        return new ResponseEntity<>(ratingRepository.findAllByRatableEntityId(ratableEntityId), HttpStatus.OK);
    }

    public ResponseEntity<String> updateRating(Rating rating) {
        try {
            Optional<Rating> ratingOptional = ratingRepository.findByUserIdAndRatableEntityId(rating.getUser().getId(), rating.getRatableEntity().getId());
            if (ratingOptional.isPresent()) {
                Rating ratingToUpdate = ratingOptional.get();
                ratingToUpdate.setIsLiked(rating.getIsLiked());
                return new ResponseEntity("Rating updated!", HttpStatus.OK);

            } else {
                return new ResponseEntity("No rating found by user for media!", HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }

    }

}
