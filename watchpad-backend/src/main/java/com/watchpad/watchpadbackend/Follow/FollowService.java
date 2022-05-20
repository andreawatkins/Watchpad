package com.watchpad.watchpadbackend.Follow;

import com.watchpad.watchpadbackend.User.User;
import com.watchpad.watchpadbackend.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<Follow>> getAllFollowerInformation(){
        return new ResponseEntity(followRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getFollowingList(String follower_username){
        Optional<List<User>> followeeList = followRepository.getFollowingList(follower_username);
        if(followeeList.isEmpty()) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

        return new ResponseEntity<>(followeeList.get(), HttpStatus.OK);
    }

    public ResponseEntity<String> followUser(Follow followData){
        if(followData.getFollowerUsername() == null || followData.getFolloweeUsername() == null) return new ResponseEntity("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        try {
            Optional<String> followeeUsername = followRepository.getFolloweeUsername(followData.getFollowerUsername(), followData.getFolloweeUsername());
            if(followeeUsername.isPresent()){
                return new ResponseEntity(String.format("(%s) is already following (%s)", followData.getFollowerUsername(), followData.getFolloweeUsername()), HttpStatus.CONFLICT);
            }
            followRepository.save(followData);
            return new ResponseEntity(String.format("(%s) is now following (%s)",followData.getFollowerUsername(), followData.getFolloweeUsername()), HttpStatus.OK);
        }
        catch(IllegalArgumentException ex){
            return new ResponseEntity("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> unfollowUser(Follow followData){
        try {
            followRepository.deleteByFolloweeUsername(followData.getFollowerUsername(), followData.getFolloweeUsername());
            return new ResponseEntity(String.format("(%s) is no longer following (%s)", followData.getFollowerUsername(), followData.getFolloweeUsername()), HttpStatus.OK);
        }
        catch(IllegalArgumentException ex){
            return new ResponseEntity("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> isFollowingUser(Follow followData){
        try {
            if(followRepository.getFolloweeUsername(followData.getFollowerUsername(), followData.getFolloweeUsername()).isEmpty()) return new ResponseEntity(false, HttpStatus.OK);
            return new ResponseEntity(true, HttpStatus.OK);
        }
        catch(IllegalArgumentException ex){
            return new ResponseEntity("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        }
    }
}
