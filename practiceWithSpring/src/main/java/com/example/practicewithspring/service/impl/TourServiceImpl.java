package com.example.practicewithspring.service.impl;

import com.example.practicewithspring.model.entity.Tour;
import com.example.practicewithspring.model.response.Data;
import com.example.practicewithspring.repository.TourRepository;
import com.example.practicewithspring.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;

    @Override
    public Data findAllTours() {
        List<Tour> tours = tourRepository.findAll();
        return new Data(true,"success",tours);
    }
    @Override
    public Data findTour(Long id) {
        Optional<Tour> tour = tourRepository.findById(id);
        if(!tour.isPresent()){
            return new Data(false,"tourId not found",null);
        }
        return new Data(true,"success",tour);
    }
    @Override
    public Data createTour(Tour tour) {
        Tour createTour = tourRepository.save(tour);
        return new Data(true,"success",createTour);
    }
    @Override
    public Data updateTour(Long id, Tour tour) {
        Optional<Tour> findTour = tourRepository.findById(id);
        if(!findTour.isPresent()){
            return new Data(false,"tourId not found",null);
        }
        Tour editTour = findTour.get();
        editTour.setTour(tour);
        tourRepository.save(editTour);
        return new Data(true,"success",editTour);
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }
}
