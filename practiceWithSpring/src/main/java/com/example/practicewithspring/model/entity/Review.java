package com.example.practicewithspring.model.entity;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String review;

    @Column(name = "danhgia")
    @Min(value = 1)
    @Max(value = 5)
    private int rating;

    @Column(name = "timeCreated")
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();

    @Column(name = "tourId")
    @NotNull
    private Long tourId;

    @Column(name = "userId")
    @NotNull
    private Long userId;

    public void updateReview(Review setReview){
        if(setReview.review != null) this.review = setReview.review;
        if(setReview.rating != 0) this.rating = setReview.rating;
    }
}