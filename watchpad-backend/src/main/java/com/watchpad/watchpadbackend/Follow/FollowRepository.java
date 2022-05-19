package com.watchpad.watchpadbackend.Follow;

import com.watchpad.watchpadbackend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT u FROM User u WHERE u.username IN (SELECT followeeUsername FROM Follow WHERE followerUsername = ?1)")
    Optional<List<User>> getFollowingList(String followerUsername);

    @Query("SELECT followeeUsername FROM Follow WHERE followerUsername = ?1 AND followeeUsername = ?2")
    Optional<String> getFolloweeUsername(String followerUsername, String followeeUsername);

    @Transactional
    @Modifying
    @Query("DELETE FROM Follow f WHERE followerUsername = ?1 AND followeeUsername = ?2")
    void deleteByFolloweeUsername(String followerUsername, String followeeUsername);
}
