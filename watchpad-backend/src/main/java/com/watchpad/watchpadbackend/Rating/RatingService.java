package com.watchpad.watchpadbackend.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    public ResponseEntity<List<Rating>> getAllRatings() {
        return new ResponseEntity<>(ratingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Rating>> getRatingsByUserId(Long userId) {
        return new ResponseEntity<>(ratingRepository.findAllByUserId(userId), HttpStatus.OK);
    }

    public ResponseEntity<List<Rating>> getRatingsByRatableEntityId(Long ratableEntityId) {
        return new ResponseEntity<>(ratingRepository.findAllByRatableEntityId(ratableEntityId), HttpStatus.OK);
    }
    public void saveNewRating(Rating rating) {
        Optional<Rating> ratingOptional = ratingRepository.findRatingByUserAndRatableEntity(rating.getUser().getId(), rating.getRatableEntity().getId());
        if (ratingOptional.isPresent()) {
            throw new IllegalStateException("Rating already exists for entity by user.");
        }
        ratingRepository.save(rating);
    }

    public void deleteRating(Long userId, Long ratableEntityId) {
        Optional<Rating> ratingOptional = ratingRepository.findRatingByUserAndRatableEntity(userId, ratableEntityId);
        if (ratingOptional.isPresent()) {
            ratingRepository.delete(ratingOptional.get());
        } else {
            throw new IllegalStateException("No rating found for entity by user.");
        }
    }

    public void updateRating(Long userId, Long ratableEntityId, boolean rating) {
        Optional<Rating> ratingOptional = ratingRepository.findRatingByUserAndRatableEntity(userId, ratableEntityId);
        if (ratingOptional.isPresent()) {
            Rating ratingToUpdate = ratingOptional.get();
            ratingToUpdate.setRating(rating);
        } else {
            throw new IllegalStateException("No rating found for entity by user.");
        }
    }

}
