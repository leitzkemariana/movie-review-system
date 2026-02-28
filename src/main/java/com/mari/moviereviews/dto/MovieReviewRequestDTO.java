package com.mari.moviereviews.dto;

import com.mari.moviereviews.domain.Movie;

import java.io.Serializable;

public class MovieReviewRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;

    public MovieReviewRequestDTO() {}

    public MovieReviewRequestDTO(Movie obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
