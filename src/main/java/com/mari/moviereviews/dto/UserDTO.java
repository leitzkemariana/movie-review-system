package com.mari.moviereviews.dto;

import com.mari.moviereviews.domain.Review;
import com.mari.moviereviews.domain.User;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private ArrayList<Review> reviews = new ArrayList<>();

    public UserDTO(){}

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.username = obj.getUsername();
        this.password = obj.getPassword();
        this.reviews = (ArrayList<Review>) obj.getReviews();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
