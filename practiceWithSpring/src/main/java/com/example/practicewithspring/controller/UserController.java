package com.example.practicewithspring.controller;

import com.example.practicewithspring.repository.UserRepository;
import com.example.practicewithspring.model.response.Data;
import com.example.practicewithspring.model.request.LoginRequest;
import com.example.practicewithspring.model.entity.Review;
import com.example.practicewithspring.model.entity.User;
import com.example.practicewithspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        Data data = userService.findAllUsers();

        return ResponseEntity.ok(data);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable(name = "id") Long id) {
        Data data = userService.findUser(id);

        /* ---- link hateoas
        EntityModel<User> resource =EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users")); */
        return ResponseEntity.ok(data);
    }
    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        Data data = new Data(false,"Please use route '/api/v1/signup' to create user!!!",user);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
    }

    @PostMapping("/signup")
    public ResponseEntity signUpUser(@Valid @RequestBody User user){
        Data data = userService.signup(user);
        return ResponseEntity.ok(data);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid @RequestBody LoginRequest loginRequest){
        Data data = userService.login(loginRequest);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable(name = "id") Long id,
                                   @RequestBody User user){
        Data data = userService.editUser(id,user);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/users/{id}/reviews")
    public void createReviewByUser(@PathVariable(name = "id") Long id,
                                 @RequestBody Review post){

    }
}
