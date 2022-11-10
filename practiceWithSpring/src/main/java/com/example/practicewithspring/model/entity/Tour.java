package com.example.practicewithspring.model.entity;

import com.example.practicewithspring.model.entity.Review;
import lombok.*;

import javax.validation.constraints.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tour")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    @NotNull(message = "A tour must have a name")
    @Size(min = 10,max = 40, message = "Quantity of characters in (10,40)")
    private String name;

    @Column(name = "soluong")
    @Min(value = 1,message = "A tour must have a duration")
    private int duration;

    @Column(name = "difficulty")
    @NotNull(message = "A tour must have a difficulty")
    private String difficulty;

    @Column(name = "danhgiatrungbinh")
    @Min(value = 1,message = "Rating must be above 1.0")
    @Max(value = 5,message = "Rating must be below 5.0")
    private double ratingsAverage = 4.5;

    @Column(name = "gia")
    @Min(value = 1,message = "A tour must have a price")
    private int price;

    public void setTour(Tour tour){
        if(tour.name != null) this.name = tour.name;
        if(tour.duration != 0) this.duration = tour.duration;
        if(tour.difficulty != null) this.difficulty = tour.difficulty;
        if(tour.ratingsAverage != 0) this.ratingsAverage = tour.ratingsAverage;
        if(tour.price != 0) this.price = tour.price;
    }
}
