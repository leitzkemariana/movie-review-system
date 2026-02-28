package com.mari.moviereviews.services;

import com.mari.moviereviews.domain.Movie;
import com.mari.moviereviews.domain.Review;
import com.mari.moviereviews.domain.User;
import com.mari.moviereviews.repository.MovieRepository;
import com.mari.moviereviews.repository.ReviewRepository;
import com.mari.moviereviews.repository.UserRepository;
import com.mari.moviereviews.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(String id) {
        Optional<Review> obj = reviewRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Review with id: " + id + " not found"));
    }

    public Review insert(Review review) {
        User user = userService.findById(review.getAuthor().getId());
        user.getReviews().add(review);
        userRepository.save(user);

        Movie movie = movieService.findById(review.getMovie().getId());
        movie.getReviews().add(review);
        movie.setRating(movie.calculateAverageRating());
        movieRepository.save(movie);

        return reviewRepository.save(review);
    }

    public void deleteById(String id) {
        Review obj = reviewRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Review with id: " + id + " not found"));

        User user = userService.findById(obj.getAuthor().getId());
        user.getReviews().remove(obj);
        userRepository.save(user);

        Movie movie = movieService.findById(obj.getMovie().getId());
        movie.getReviews().remove(obj);
        movie.setRating(movie.calculateAverageRating());
        movieRepository.save(movie);

        reviewRepository.deleteById(id);
    }
}
