package com.example.practicewithspring.service.impl;

import com.example.practicewithspring.model.entity.Review;
import com.example.practicewithspring.model.entity.Tour;
import com.example.practicewithspring.model.entity.User;
import com.example.practicewithspring.model.response.Data;
import com.example.practicewithspring.repository.ReviewRepository;
import com.example.practicewithspring.repository.TourRepository;
import com.example.practicewithspring.repository.UserRepository;
import com.example.practicewithspring.security.jwt.JwtTokenProvider;
import com.example.practicewithspring.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Override
    public Data findAllReviews() {
        List<Review> reviewList = reviewRepository.findAll();
        return new Data(true,"success",reviewList);
    }

    @Override
    public Data findReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return new Data(true,"success",review);
    }

    @Override
    public Data createReview(Review review) {
        Optional<Tour> tour = tourRepository.findById(review.getTourId());
        if (!tour.isPresent()){
            return new Data(false,"tourId not found",null);
        }

        Optional<User> user = userRepository.findById(review.getUserId());
        if (!user.isPresent()){
            return new Data(false,"userId not found",null);
        }

        Review newReview = reviewRepository.save(review);
        return  new Data(true,"success",newReview);
    }

    @Override
    public Data editReview(Long id,Review review) {
        Optional<Review> findReview = reviewRepository.findById(id);
        Review editReview = findReview.get();
        editReview.updateReview(review);
        reviewRepository.save(editReview);
        return new Data(true,"success",editReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Data findAllReviewByTour(Long id) {
        List<Review> reviewList = reviewRepository.findAllByTourId(id);
        return new Data(true,"success",reviewList);
    }

    @Override
    public Data createReviewByTour(Long id,Review review) {
        review.setTourId(id);
        return new Data(true,"success",review);
    }

}
