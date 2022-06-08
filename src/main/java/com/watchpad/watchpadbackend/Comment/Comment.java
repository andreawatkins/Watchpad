package com.watchpad.watchpadbackend.Comment;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watchpad.watchpadbackend.CommentLike.CommentLike;
import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;

import com.watchpad.watchpadbackend.Content.Content;
import com.watchpad.watchpadbackend.Media.Media;
import com.watchpad.watchpadbackend.Rating.RatableEntity;
import com.watchpad.watchpadbackend.User.User;

@Data
@Entity
@Table(name = "comment")
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long comment_id;
   @ManyToOne
   @JoinColumn(name = "media_id")
   private Media media;
   @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
   @JsonManagedReference(value = "comment-commentLikes")
   Set<CommentLike> likesDislikes;
   @ManyToOne
   private User user;
   private String comment_timestamp;
   private int duration_timestamp;
   // @OneToOne
   @Column(columnDefinition = "TEXT")
   private String content;
   private boolean spoiler;
   private boolean review;

   private String gifURL;

   public Comment(Long comment_id, Media media, User user, String comment_timestamp, int duration_timestamp,
         String content, boolean spoiler, String gifURL, int likes, int dislikes, boolean review) {

      this.comment_id = comment_id;
      this.media = media;
      this.user = user;
      this.comment_timestamp = comment_timestamp;
      this.duration_timestamp = duration_timestamp;
      this.content = content;
      this.spoiler = spoiler;
      this.review = review;
      this.gifURL = gifURL;

   }

   public Comment() {
   };

   Long score;
}
