package com.watchpad.watchpadbackend.Follow;

import com.watchpad.watchpadbackend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT followee_id FROM Follow WHERE follower_id = ?1")
    List<Long> getFollowingList(Long follower_id);
}
