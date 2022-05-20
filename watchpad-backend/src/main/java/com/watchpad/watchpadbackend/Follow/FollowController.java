package com.watchpad.watchpadbackend.Follow;

import com.watchpad.watchpadbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/follower")
public class FollowController {

    @Autowired
    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("/get-all-followers")
    public ResponseEntity<List<Follow>> getAllFollowerInformation(){
        return followService.getAllFollowerInformation();
    }

    @PostMapping("/get-following-list")
    public ResponseEntity<List<User>> getFollowingList(@RequestBody Follow followData){
        return followService.getFollowingList(followData.getFollowerUsername());
    }

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestBody Follow followData){
        return followService.followUser(followData);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestBody Follow followData){
        return followService.unfollowUser(followData);
    }

    @PostMapping("/is-following-user")
    public ResponseEntity<Object> isFollowingUser(@RequestBody Follow followData){
        return followService.isFollowingUser(followData);
    }
}
