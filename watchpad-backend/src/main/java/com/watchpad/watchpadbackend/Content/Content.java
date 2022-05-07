package com.watchpad.watchpadbackend.Content;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Content {

    
    @Id
    private String content_id;
    private String text; 

    //images
    


public Content(String text){
    this.text=text; 
}

}