package com.example.practicewithspring.controller;

import com.example.practicewithspring.model.response.Data;
import com.example.practicewithspring.repository.TourRepository;
import com.example.practicewithspring.model.entity.Tour;
import com.example.practicewithspring.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TourController {
    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public ResponseEntity getAllTours(){
        Data data = tourService.findAllTours();
        return ResponseEntity.ok(data);
    }
    @GetMapping("/tours/{tourId}")
    public ResponseEntity getTour(@PathVariable(name = "tourId") Long id){
        Data data = tourService.findTour(id);
        if(data.getAccess()) return ResponseEntity.ok(data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }
    @PostMapping("/tours")
    public ResponseEntity postTour(@Valid @RequestBody Tour tour){
        Data data = tourService.createTour(tour);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }
    @PutMapping("/tours/{tourId}")
    public ResponseEntity putTour(@Valid @RequestBody Tour tour,
                                  @PathVariable(name = "tourId") Long id){
        Data data = tourService.updateTour(id,tour);
        if(data.getAccess()) return ResponseEntity.ok(data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }
    @DeleteMapping("/tours/{tourId}")
    public ResponseEntity deleteTour(@PathVariable(name = "tourId") Long id){
        tourService.deleteTour(id);
        return ResponseEntity.noContent().build();
    }

}