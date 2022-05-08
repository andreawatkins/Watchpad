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

    public ResponseEntity<Object> getFollowingList(Long follower_id){
        List<User> userList = new ArrayList<>();
        List<Long> followeeList = followRepository.getFollowingList(follower_id);
        for(Long followee_id : followeeList){
            try {
                Optional<User> user = userRepository.findById(followee_id);
                if(user.isPresent()) userList.add(user.get());
            }
            catch(IllegalArgumentException ex) {
                return new ResponseEntity<>("ID must be valid.", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<String> followUser(Follow followData){
        try {
            followRepository.save(followData);
            return new ResponseEntity(String.format("User with ID (%s) is now following User with ID (%s)", followData.getFollower_id(), followData.getFollowee_id()), HttpStatus.OK);
        }
        catch(IllegalArgumentException ex){
            return new ResponseEntity("Must provide a valid object.", HttpStatus.BAD_REQUEST);
        }
    }
}
