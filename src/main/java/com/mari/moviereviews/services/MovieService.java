package com.mari.moviereviews.services;

import com.mari.moviereviews.domain.Movie;
import com.mari.moviereviews.domain.User;
import com.mari.moviereviews.dto.MovieDTO;
import com.mari.moviereviews.dto.UserDTO;
import com.mari.moviereviews.repository.MovieRepository;
import com.mari.moviereviews.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(String id) {
        Optional<Movie> obj = movieRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Movie with id: " + id + " not found"));
    }

    public List<Movie> findByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    public Movie insert(Movie obj) {return movieRepository.save(obj);}

    public void deleteById(String id) {
        movieRepository.deleteById(id);
    }

    public Movie fromDTO(MovieDTO objDTO) {
        Movie movie = movieRepository.findById(objDTO.getId()).orElseThrow(() -> new ObjectNotFoundException("Movie with id: " + objDTO.getId()));
        return movie;
    }
}
