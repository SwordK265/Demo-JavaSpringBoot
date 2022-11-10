package com.example.practicewithspring.service;

import com.example.practicewithspring.model.entity.Tour;
import com.example.practicewithspring.model.response.Data;

public interface TourService {
    Data findAllTours();

    Data findTour(Long id);

    Data createTour(Tour tour);

    Data updateTour(Long id,Tour tour);

    void deleteTour(Long id);
}
