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
    public ResponseEntity<Optional<List<Rating>>> getRatingsByUserId(@Param("userId") Long userId){
        return mediaRatingService.getRatingsByUserId(userId);
    }

    @GetMapping("/get-rating")
    public ResponseEntity<Optional<Rating>> getMediaRating(@Param("userId") Long userId,
                                                      @Param("mediaId") Long mediaId){
        return mediaRatingService.getMediaRating(userId, mediaId);
    }

    @GetMapping("/get-ratings-for-media")
    public ResponseEntity<Optional<List<Rating>>> getRatingsByMediaId(@Param("entityId") Long entityId){
        return mediaRatingService.getRatingsByRatableEntityId(entityId);
    }

    @GetMapping("/count-likes")
    public ResponseEntity<Long> getCountOfLikesByEntityId(@Param("mediaId") Long mediaId){
        return mediaRatingService.getCountOfLikesByEntityId(mediaId);
    }

    @GetMapping("/count-dislikes")
    public ResponseEntity<Long> getCountOfDislikesByEntityId(@Param("mediaId") Long mediaId){
        return mediaRatingService.getCountOfDislikesByEntityId(mediaId);
    }


    @PostMapping("/save-rating")
    public ResponseEntity<String> saveNewRating(@Param("userId") Long userId,
                                                @Param("mediaId") Long mediaId,
                                                @Param("isLiked") boolean isLiked) {
        return mediaRatingService.saveNewMediaRating(userId, mediaId, isLiked);
    }

    @PostMapping("/delete-rating")
    public ResponseEntity<String> saveNewRating(@Param("userId") Long userId,
                                                @Param("mediaId") Long mediaId) {
        return mediaRatingService.deleteMediaRating(userId, mediaId);
    }


}
