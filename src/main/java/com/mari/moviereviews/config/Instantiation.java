package com.mari.moviereviews.config;

import com.mari.moviereviews.domain.Movie;
import com.mari.moviereviews.domain.Review;
import com.mari.moviereviews.domain.User;
import com.mari.moviereviews.dto.AuthorDTO;
import com.mari.moviereviews.dto.MovieDTO;
import com.mari.moviereviews.dto.MovieReviewRequestDTO;
import com.mari.moviereviews.repository.MovieRepository;
import com.mari.moviereviews.repository.ReviewRepository;
import com.mari.moviereviews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        userRepository.deleteAll();
        movieRepository.deleteAll();
        reviewRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "123");
        User alex = new User(null, "Alex Green", "123");
        User bob = new User(null, "Bob Grey", "123");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Movie movie1 = new Movie(null, "O Poderoso Chefão", 1972, "Crime/Drama", "Francis Ford Coppola");
        Movie movie2 = new Movie(null, "Interestelar", 2014, "Ficção Científica", "Christopher Nolan");
        Movie movie3 = new Movie(null, "Parasita", 2019, "Thriller/Drama", "Bong Joon-ho");
        Movie movie4 = new Movie(null, "Cidade de Deus", 2002, "Crime/Drama", "Fernando Meirelles");

        movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3, movie4));

        Review r1 = new Review(null, new MovieReviewRequestDTO(movie2), 9.0, "OK", sdf.parse("11/02/2025"), new AuthorDTO(bob));
        Review r2 = new Review(null, new MovieReviewRequestDTO(movie2), 8.7, "OK", sdf.parse("11/02/2025"), new AuthorDTO(alex));

        reviewRepository.saveAll(Arrays.asList(r1, r2));

        movie2.getReviews().add(r1);
        movie2.getReviews().add(r2);
        movie2.setRating(movie2.calculateAverageRating());
        movieRepository.save(movie2);

        bob.getReviews().add(r1);
        alex.getReviews().add(r2);
        userRepository.saveAll(Arrays.asList(bob, alex));
    }
}