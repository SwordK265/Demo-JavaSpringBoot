package com.example.practicewithspring.repository;

import com.example.practicewithspring.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Review findByTourId(Long id);
    List<Review> findAllByTourId(Long id);
}
