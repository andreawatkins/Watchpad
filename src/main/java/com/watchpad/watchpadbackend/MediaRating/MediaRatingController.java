package com.watchpad.watchpadbackend.MediaRating;

import com.watchpad.watchpadbackend.Rating.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/get-ratings-for-user")
    public ResponseEntity<Optional<List<MediaRating>>> getRatingsByUserId(@Param("userId") Long userId){
        return mediaRatingService.getRatingsByUserId(userId);
    }

    @GetMapping("/get-ratings-for-media")
    public ResponseEntity<Optional<List<MediaRating>>> getRatingsByMediaId(@Param("mediaId") Long mediaId){
        return mediaRatingService.getRatingsByRatableEntityId(mediaId);
    }

    @GetMapping("/get-rating")
    public ResponseEntity<Optional<MediaRating>> getMediaRating(@Param("userId") Long userId,
                                                                @Param("mediaId") Long mediaId){
        return mediaRatingService.getRating(userId, mediaId);
    }

    @GetMapping("/get-average-rating-for-media")
    public ResponseEntity<Optional<Float>> getAverageRatingByMediaId(@Param("mediaId") Long mediaId){
        return mediaRatingService.getAverageRatingForMedia(mediaId);
    }

    @PostMapping("/save-rating")
    public ResponseEntity<String> saveNewRating(@Param("userId") Long userId,
                                                @Param("mediaId") Long mediaId,
                                                @Param("rating") Float rating) {
        return mediaRatingService.saveOrUpdateMediaRating(userId, mediaId, rating);
    }

    @PostMapping("/delete-rating")
    public ResponseEntity<String> deleteRating(@Param("userId") Long userId,
                                               @Param("mediaId") Long mediaId) {
        return mediaRatingService.deleteRating(userId, mediaId);
    }

}