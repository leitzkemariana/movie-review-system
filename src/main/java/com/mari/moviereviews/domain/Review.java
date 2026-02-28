package com.mari.moviereviews.domain;

import com.mari.moviereviews.dto.AuthorDTO;
import com.mari.moviereviews.dto.MovieReviewRequestDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Document(collection = "review")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private MovieReviewRequestDTO movie;
    private Double rating;
    private String text;
    private Date date;
    private AuthorDTO author;

    public Review() {}

    public Review(String id, MovieReviewRequestDTO movie, Double rating, String text, Date date, AuthorDTO author) {
        this.id = id;
        this.movie = movie;
        this.rating = rating;
        this.text = text;
        this.date = date;
        this.author = author;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MovieReviewRequestDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieReviewRequestDTO movie) {
        this.movie = movie;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(getId(), review.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
