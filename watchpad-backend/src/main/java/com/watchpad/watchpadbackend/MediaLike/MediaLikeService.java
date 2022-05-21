package com.watchpad.watchpadbackend.MediaLike;

import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.Rating.Rating;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MediaLikeService {

    private final MediaLikeRepository mediaLikeRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;

    @Autowired
    public MediaLikeService(MediaLikeRepository mediaLikeRepository,
                            MediaRepository mediaRepository,
                            UserRepository userRepository) {
        this.mediaLikeRepository = mediaLikeRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Long> getCountOfLikesByEntityId(Long ratableEntityId) {
        return new ResponseEntity<>(mediaLikeRepository.getCountOfLikesByEntityId(ratableEntityId), HttpStatus.OK);
    }

    public ResponseEntity<Long> getCountOfDislikesByEntityId(Long ratableEntityId) {
        return new ResponseEntity<>(mediaLikeRepository.getCountOfDislikesByEntityId(ratableEntityId), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> saveOrUpdateMediaLike(Long userId, Long mediaId, boolean isliked){
        try {
            System.out.println();
            System.out.println("SAVE OR UPDATE NEW MEDIA LIKE");
            System.out.println("userId " + userId);
            System.out.println("mediaId " + mediaId);
            System.out.println("isliked " + isliked);
            System.out.println();

            //If user does not exist in user repo, return conflict
            Optional<User> userOptional = userRepository.findById(userId);
            if(!userOptional.isPresent()){
                return new ResponseEntity("Could not find user, media like/dislike not saved!", HttpStatus.CONFLICT);
            }

            //if rating already exists for Media by User, update rating
            Optional<MediaLike> mediaLikeOptional = mediaLikeRepository.getMediaLikeByUserIdAndMediaId(userId, mediaId);
            if (mediaLikeOptional.isPresent()) {
                mediaLikeRepository.setIsLiked(isliked, mediaLikeOptional.get().getId());
                return new ResponseEntity("Existing like/dislike updated with new value!", HttpStatus.CREATED);
            }

            //If media not in media repo, save new media
            Optional<Media> mediaOptional = mediaRepository.findById(mediaId);
            if(!mediaOptional.isPresent()){
                Media media = new Media(mediaId);
                mediaRepository.save(media);

                //Try to pull new media from repo that we just saved
                mediaOptional = mediaRepository.findById(mediaId);
            }

            if(mediaOptional.isPresent()){
                MediaLike newMediaLike = new MediaLike(userOptional.get(),isliked);
                newMediaLike.setRatableEntity(mediaOptional.get());
                mediaLikeRepository.save(newMediaLike);
                return new ResponseEntity("Media like/dislike registered!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity("Could not save media id in media repository, like/dislike not saved!", HttpStatus.CONFLICT);
            }

        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Optional<List<MediaLike>>> getLikesByUserId(Long userId) {
        return new ResponseEntity<>(mediaLikeRepository.getAllMediaLikesByUserId(userId), HttpStatus.OK);
    }

    public ResponseEntity<Optional<List<MediaLike>>> getLikesByRatableEntityId(Long mediaId) {
        return new ResponseEntity<>(mediaLikeRepository.getAllMediaLikesByRatableEntityId(mediaId), HttpStatus.OK);
    }

    public ResponseEntity<Optional<MediaLike>> getLike(Long userId, Long mediaId) {
        return new ResponseEntity<>(mediaLikeRepository.getMediaLikeByUserIdAndMediaId(userId, mediaId), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteLike(Long userId, Long mediaId) {
        Optional<MediaLike> mediaLikeOptional = mediaLikeRepository.getMediaLikeByUserIdAndMediaId(userId, mediaId);
        if (mediaLikeOptional.isPresent()) {
            mediaLikeRepository.deleteById(mediaLikeOptional.get().getId());
            return new ResponseEntity<>("Media like deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No existing media like to delete!", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<List<Rating>> getAllLikes() {
        return new ResponseEntity<>(mediaLikeRepository.findAll(), HttpStatus.OK);
    }
}