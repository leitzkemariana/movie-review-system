package com.mari.moviereviews.dto;

import com.mari.moviereviews.domain.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;

    public AuthorDTO() {}

    public AuthorDTO(User obj) {
        this.id = obj.getId();
        this.username = obj.getUsername();
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
}
