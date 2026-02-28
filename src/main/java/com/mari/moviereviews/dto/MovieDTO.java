package com.mari.moviereviews.dto;

import com.mari.moviereviews.domain.Movie;

import java.io.Serializable;

public class MovieDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer year;
    private String genre;
    private String director;
    private Double rating;

    public MovieDTO() {}

    public MovieDTO(Movie obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.year = obj.getYear();
        this.genre = obj.getGenre();
        this.director = obj.getDirector();
        this.rating = obj.getRating();
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }
}
