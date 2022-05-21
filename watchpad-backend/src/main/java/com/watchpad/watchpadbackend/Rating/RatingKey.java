package com.watchpad.watchpadbackend.Rating;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public
class RatingKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "ratableEntity_id")
    Long ratableEntityId;

}
