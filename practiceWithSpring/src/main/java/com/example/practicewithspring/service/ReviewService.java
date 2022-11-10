package com.example.practicewithspring.service;

import com.example.practicewithspring.model.entity.Review;
import com.example.practicewithspring.model.response.Data;

public interface ReviewService {
    Data findAllReviews();
    Data findReview(Long id);
    Data createReview(Review review);
    Data editReview(Long id,Review review);
    void deleteReview(Long id);

    Data findAllReviewByTour(Long id);

    Data createReviewByTour(Long id,Review review);
}
