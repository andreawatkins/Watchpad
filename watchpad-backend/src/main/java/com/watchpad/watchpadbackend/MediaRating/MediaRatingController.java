package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.Rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/media-rating")
public class MediaRatingController {

    private final MediaRatingService mediaRatingService;

    @Autowired
    public MediaRatingController(MediaRatingService mediaRatingService) {
        this.mediaRatingService = mediaRatingService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Rating>> getRatings(){
        return mediaRatingService.getAllRatings();
    }
//
//    @GetMapping("/get-user-ratings")
//    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("userId") Long userId){
//        return mediaRatingService.getRatingsByUserId(userId);
//    }
//
//    @GetMapping("/get-entity-ratings")
//    public ResponseEntity<List<Rating>> getRatingsByMediaId(@PathVariable("entityId") Long entityId){
//        return mediaRatingService.getRatingsByRatableEntityId(entityId);
//    }

    @PostMapping("/save-rating")
    public ResponseEntity<String> saveNewRating(@RequestBody MediaRating mediaRating)  {
        return mediaRatingService.saveNewMediaRating(mediaRating);
    }


//    @DeleteMapping
//    public void deleteRating(@Param("userId") Long userId,
//                             @Param("ratableEntityId") Long ratableEntityId) {
//        mediaRatingService.deleteRating(userId, ratableEntityId);
//    }
//
//    @PutMapping
//    public void updateRating(@Param("userId") Long userId,
//                             @Param("ratableEntityId") Long ratableEntityId,
//                             @Param("rating") boolean rating) {
//        mediaRatingService.updateRating(userId, ratableEntityId, rating);
//    }


}
