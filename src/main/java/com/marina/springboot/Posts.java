package com.marina.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Posts {	
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private String title;
    
    private String text;
    
    private String image;
    
    private int author_id;
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
    	return text;
    }
    public void setText(String text) {
    	this.text = text;
    }
    public String getImage() {
    	return image;
    }
    public void setImage(String image) {
    	this.image = image;
    }
    public int getAuthor_id() {
    	return author_id;
    }
    public void setAuthor_id(int author_id) {
    	this.author_id = author_id;
    }
    
    
	

}
