package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.Rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/media-rating")
public class MediaRatingController {

    private final RatingService ratingService;

    @Autowired
    public MediaRatingController(RatingService ratingsService) {
        this.ratingService = ratingsService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Rating>> getRatings(){
        return ratingService.getAllRatings();
    }

    @GetMapping("/get-user-ratings")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("userId") Long userId){
        return ratingService.getRatingsByUserId(userId);
    }

    @GetMapping("/get-entity-ratings")
    public ResponseEntity<List<Rating>> getRatingsByMediaId(@PathVariable("entityId") Long entityId){
        return ratingService.getRatingsByRatableEntityId(entityId);
    }

    @PostMapping("/save-rating")
    public void saveNewRating(@RequestBody Rating rating) {
        ratingService.saveNewRating(rating);
    }

    @DeleteMapping
    public void deleteRating(@PathVariable("userId") Long userId,
                             @PathVariable("ratableEntityId") Long ratableEntityId) {
        ratingService.deleteRating(userId, ratableEntityId);
    }

    @PutMapping
    public void updateRating(@PathVariable("userId") Long userId,
                             @PathVariable("ratableEntityId") Long ratableEntityId,
                             @PathVariable("rating") boolean rating) {
        ratingService.updateRating(userId, ratableEntityId, rating);
    }


}
