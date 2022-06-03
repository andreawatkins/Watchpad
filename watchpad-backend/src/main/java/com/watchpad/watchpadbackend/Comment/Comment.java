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
@Table(name = "comments")
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long comment_id;
   @ManyToOne
   @JoinColumn(name = "media_id")
   private Media media;
   @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonManagedReference(value = "comment-commentLikes")
   
   Set<CommentLike> likesDislikes; 
   @ManyToOne
   private User user;
   private String comment_timestamp;
   @Column(nullable = true)
   private String duration_timestamp;
   // @OneToOne
   @Column(columnDefinition = "TEXT")
   private String content;
   private boolean spoiler;

   public Comment(Long comment_id, Media media, User user, String comment_timestamp, String duration_timestamp,
         String content, boolean spoiler, int likes, int dislikes) {

      this.comment_id = comment_id;
      this.media = media;
      this.user = user;
      this.comment_timestamp = comment_timestamp;
      this.duration_timestamp = duration_timestamp;
      this.content = content;
      this.spoiler = spoiler;

   }

   public Comment() {
   };

   @Override
   public String toString() {
      return "Comment{" + "id=" + this.comment_id + ", media_id='" + this.media + '\'' + ", user='" + this.user + '\'' +
            ", timestamp='" + this.comment_timestamp + '\'' + ", content='" + this.content + '\'' + ", spoiler='"
            + this.spoiler + '\'' +
            +'}';
   }

}
