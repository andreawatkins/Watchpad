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

    public ResponseEntity<Long> getCountOfLikesByEntityId(Long ratableEntityId) {
        return new ResponseEntity<>(ratingRepository.getCountOfLikesByEntityId(ratableEntityId), HttpStatus.OK);
    }

    public ResponseEntity<Long> getCountOfDislikesByEntityId(Long ratableEntityId) {
        return new ResponseEntity<>(ratingRepository.getCountOfDislikesByEntityId(ratableEntityId), HttpStatus.OK);
    }

}
