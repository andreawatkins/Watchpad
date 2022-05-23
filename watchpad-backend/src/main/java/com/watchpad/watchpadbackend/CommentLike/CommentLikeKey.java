package com.watchpad.watchpadbackend.CommentLike;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CommentLikeKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "comment_id")
    Long commentId;

}
