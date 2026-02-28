package com.mari.moviereviews.resources;

import com.mari.moviereviews.domain.Movie;
import com.mari.moviereviews.domain.Review;
import com.mari.moviereviews.dto.MovieDTO;
import com.mari.moviereviews.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MovieDTO>> getAllMovies(){
        List<Movie> listMovies = movieService.findAll();
        List<MovieDTO> list = listMovies.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MovieDTO> getMovie(@PathVariable String id){
        Movie obj = movieService.findById(id);
        return ResponseEntity.ok().body(new MovieDTO(obj));
    }

    @RequestMapping(value = "/title", method = RequestMethod.GET)
    public ResponseEntity<List<MovieDTO>> getByTitle(@RequestParam(value = "title", defaultValue = " ") String title){
        try {
            title = URLDecoder.decode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        List<Movie> listMovies = movieService.findByTitle(title);
        List<MovieDTO> list = listMovies.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Movie> insert(@RequestBody Movie obj){
        return ResponseEntity.ok().body(movieService.insert(obj));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}/reviews", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> findReviews(@PathVariable String id){
        Movie obj = movieService.findById(id);
        return ResponseEntity.ok().body(obj.getReviews());
    }
}
