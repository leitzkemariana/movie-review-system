package com.mari.moviereviews.resources;

import com.mari.moviereviews.domain.Review;
import com.mari.moviereviews.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> listReviews = reviewService.findAll();
        return ResponseEntity.ok().body(listReviews);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Review> getReview(@PathVariable String id){
        Review obj = reviewService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Review> insert(@RequestBody Review review){
        reviewService.insert(review);
        return ResponseEntity.ok().body(review);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
