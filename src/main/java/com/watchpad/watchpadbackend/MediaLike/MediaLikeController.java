package com.watchpad.watchpadbackend.MediaLike;

import com.watchpad.watchpadbackend.Rating.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/media-rating/likes")
public class MediaLikeController {

    private final MediaLikeService mediaLikeService;

    @Autowired
    public MediaLikeController(MediaLikeService mediaRatingService) {
        this.mediaLikeService = mediaRatingService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Rating>> getLikes(){
        return mediaLikeService.getAllLikes();
    }

    @GetMapping("/get-likes-for-user")
    public ResponseEntity<Optional<List<MediaLike>>> getLikesByUserId(@Param("userId") Long userId){
        return mediaLikeService.getLikesByUserId(userId);
    }

    @GetMapping("/get-like")
    public ResponseEntity<Optional<MediaLike>> getMediaLike(@Param("userId") Long userId,
                                                            @Param("mediaId") Long mediaId){
        return mediaLikeService.getLike(userId, mediaId);
    }

    @GetMapping("/get-likes-for-media")
    public ResponseEntity<Optional<List<MediaLike>>> getLikesByMediaId(@Param("mediaId") Long mediaId){
        return mediaLikeService.getLikesByRatableEntityId(mediaId);
    }

    @GetMapping("/count-likes")
    public ResponseEntity<Long> getCountOfLikesByEntityId(@Param("mediaId") Long mediaId){
        return mediaLikeService.getCountOfLikesByEntityId(mediaId);
    }

    @GetMapping("/count-dislikes")
    public ResponseEntity<Long> getCountOfDislikesByEntityId(@Param("mediaId") Long mediaId){
        return mediaLikeService.getCountOfDislikesByEntityId(mediaId);
    }

    @PostMapping("/save-like")
    public ResponseEntity<String> saveNewLike(@Param("userId") Long userId,
                                              @Param("mediaId") Long mediaId,
                                              @Param("isLiked") boolean isLiked) {
        return mediaLikeService.saveOrUpdateMediaLike(userId, mediaId, isLiked);
    }

    @PostMapping("/delete-like")
    public ResponseEntity<String> deleteLike(@Param("userId") Long userId,
                                             @Param("mediaId") Long mediaId) {
        return mediaLikeService.deleteLike(userId, mediaId);
    }

}