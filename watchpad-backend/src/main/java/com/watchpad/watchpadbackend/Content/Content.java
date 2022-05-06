package com.watchpad.watchpadbackend.Content;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long content_id;
    private String text; 
    // private Image image;
    // private String gif;
    


public Content(String text){
    this.text=text; 
}

}