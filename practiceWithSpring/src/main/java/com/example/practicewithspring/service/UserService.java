package com.example.practicewithspring.service;

import com.example.practicewithspring.model.entity.User;
import com.example.practicewithspring.model.request.LoginRequest;
import com.example.practicewithspring.model.response.Data;
import org.springframework.stereotype.Service;

public interface UserService  {
    Data login(LoginRequest loginRequest);
    Data signup(User user);
    Data findAllUsers();
    Data findUser(Long id);
    Data editUser(Long id,User user);
    void deleteUser(Long id);
}
