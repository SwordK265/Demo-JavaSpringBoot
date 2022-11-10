package com.example.practicewithspring.model.entity;

import com.example.practicewithspring.eroles.ERole;
import com.example.practicewithspring.model.entity.Review;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    @Column(name= "role")
    private ERole role = ERole.USER;

    @Column(name = "ten")
    @NotNull
    private String name;

    @Column(name = "email")
    @NotNull
    private String email;


    public void setUser(User user){
        if(user.email != null) this.email = user.email;
        if(user.name != null) this.name = user.name;
    }
}
