package com.example.practicewithspring.controller;

import com.example.practicewithspring.model.entity.Review;
import com.example.practicewithspring.model.response.Data;
import com.example.practicewithspring.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity getAllReviews(){
        Data data = reviewService.findAllReviews();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity getReview(@PathVariable(name = "id") Long id){
        Data data = reviewService.findReview(id);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/reviews")
    public ResponseEntity createReview(@Valid @RequestBody Review review){
        Data data = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity updateReview(@PathVariable(name = "id") Long id,
                             @RequestBody Review review){
        Data data = reviewService.editReview(id,review);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity deleteReview(@PathVariable(name = "id") Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/tours/{id}/reviews")
    public ResponseEntity getAllReviewsByTour(@PathVariable(name = "id") Long id){
        Data data = reviewService.findAllReviewByTour(id);
        return ResponseEntity.ok(data);
    }
}
