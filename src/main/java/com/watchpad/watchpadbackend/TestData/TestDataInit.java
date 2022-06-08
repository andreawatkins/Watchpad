package com.watchpad.watchpadbackend.TestData;

import com.watchpad.watchpadbackend.Comment.Comment;
import com.watchpad.watchpadbackend.Comment.CommentRepository;
import com.watchpad.watchpadbackend.CommentLike.CommentLike;
import com.watchpad.watchpadbackend.CommentLike.CommentLikeRepository;
import com.watchpad.watchpadbackend.Follow.Follow;
import com.watchpad.watchpadbackend.Follow.FollowRepository;
import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Media.MediaRepository;
import com.watchpad.watchpadbackend.MediaLike.MediaLike;
import com.watchpad.watchpadbackend.MediaLike.MediaLikeRepository;
import com.watchpad.watchpadbackend.MediaRating.MediaRating;
import com.watchpad.watchpadbackend.MediaRating.MediaRatingRepository;
import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import com.watchpad.watchpadbackend.WatchList.WatchListEntry;
import com.watchpad.watchpadbackend.WatchList.WatchListRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.watchpad.watchpadbackend.TestData.TestDataConstants.*;


@Component
public class TestDataInit {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaRatingRepository mediaRatingRepository;

    @Autowired
    private MediaLikeRepository mediaLikeRepository;

    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @PostConstruct
    @Transactional

    public void init() {
        if(userRepository.findAll().isEmpty()) {

            //Users
            Long userId = 1L;
            for (String username:testUsernames){
                userRepository.save(new User(userId, "test"+ userId +"@test.com", username, new BCryptPasswordEncoder().encode("test"), "default-photo"));
                userId++;
            }


            //Followers
            for (String username:testUsernames){
                String[] following = new String[testUsernames.length];
                System.arraycopy(testUsernames, 0, following,0,testUsernames.length);

                for(String follow:following){
                    if(!follow.equals(username)){
                        followRepository.save(new Follow(username, follow));
                    }
                }
            }


            //Media
            for (Long mediaId:testMediaIds){
                mediaRepository.save(new Media(mediaId));
            }


            //Watchlist
            List<Long> shuffledTestMedia = Arrays.asList(testMediaIds);
            Collections.shuffle(shuffledTestMedia);

            userId = 1L;
            for (int i = 1; i < testMediaIds.length; i++){
                User user = userRepository.getById(userId);
                Hibernate.initialize(user);

                Media media = mediaRepository.getById(testMediaIds[i-1]);
                Hibernate.initialize(media);

                WatchListEntry watchListEntry = new WatchListEntry(1L, user, media);
                Hibernate.initialize(watchListEntry);

                watchListRepository.save(watchListEntry);

                if(userId < testUsernames.length && i % Math.floor(testMediaIds.length / testUsernames.length) == 0){
                    userId++;
                }
            }



            //MediaRating
            userId = 1L;
            for (int i = 0; i < testUsernames.length; i++){
                for (int j = 0; j < testMediaIds.length; j++) {
                    User user = userRepository.getById(userId);
                    Hibernate.initialize(user);

                    Media media = mediaRepository.getById(testMediaIds[j]);
                    Hibernate.initialize(media);

                    MediaRating mediaRating = new MediaRating(user, testMediaRatings[(int) Math.floor(Math.random() * testMediaRatings.length)]);
                    mediaRating.setRatableEntity(media);
                    Hibernate.initialize(mediaRating);

                    mediaRatingRepository.save(mediaRating);
                }
                userId++;
            }



            //MediaLike
            userId = 1L;
            for (int i = 0; i < testUsernames.length; i++){
                for (int j = 0; j < testMediaIds.length; j++) {
                    User user = userRepository.getById(userId);
                    Hibernate.initialize(user);

                    Media media = mediaRepository.getById(testMediaIds[j]);
                    Hibernate.initialize(media);

                    MediaLike mediaLike = new MediaLike(user, Math.random() < likesToDislikesRatio);
                    mediaLike.setRatableEntity(media);
                    Hibernate.initialize(mediaLike);

                    mediaLikeRepository.save(mediaLike);
                }
                userId++;
            }


            //Comments and CommentLikes
            userId = 1L;
            List<String> shuffledComments = Arrays.asList(testComments);
            Collections.shuffle(shuffledComments);

            for (int i = 1; i < shuffledComments.size(); i++) {
                User user = userRepository.getById(userId);
                Hibernate.initialize(user);

                Media media = mediaRepository.getById(testMediaIds[(int) Math.floor(Math.random() * testMediaIds.length)]);
                Hibernate.initialize(media);

                int durationTimestamp = testDurationTimestamps[(int) Math.floor(Math.random() * testDurationTimestamps.length)];

                Comment comment = new Comment(1L,
                        media,
                        user,
                        testPostTimestamps[(int) Math.floor(Math.random() * testPostTimestamps.length)],
                        durationTimestamp,
                        shuffledComments.get(i-1),
                        (Math.random() < spoilersRatio),
                        "",
                        0,
                        0,
                        (Math.random() < durationCommentsToReviewCommentsRatio)
                );

                Comment newComment = commentRepository.saveAndFlush(comment);
                Hibernate.initialize(newComment);

                //All Comments get at least one Like/Dislike
                User likesUser = userRepository.getById((long) Math.floor(Math.random() * testUsernames.length) + 1);
                Hibernate.initialize(likesUser);

                CommentLike commentLike = new CommentLike(likesUser, newComment,Math.random() < likesToDislikesRatio);
                Hibernate.initialize(commentLike);
                commentLikeRepository.save(commentLike);

                //Generate more Likes/Dislikes for some comments
                String[] highEngagementUsers = new String[testUsernames.length];
                System.arraycopy(testUsernames, 0, highEngagementUsers,0,testUsernames.length);
                if(Math.random() < highCommentEngagementRatio){
                    for(String otherUser:highEngagementUsers){
                        if(!otherUser.equals(likesUser.getUsername())){
                            Optional<User> engagementUser = userRepository.findByUsername(otherUser);
                            if(!engagementUser.isEmpty()){
                                Hibernate.initialize(engagementUser.get());
                                CommentLike commentEngagement = new CommentLike(engagementUser.get(), newComment,Math.random() < likesToDislikesRatio);
                                Hibernate.initialize(commentEngagement);
                                commentLikeRepository.save(commentEngagement);
                            }
                        }
                    }
                }


                if(userId < testUsernames.length && i % Math.floor(shuffledComments.size() / testUsernames.length) == 0) {
                    userId++;
                }
            }

        }
    }
}
