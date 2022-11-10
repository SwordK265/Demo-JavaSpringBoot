package com.example.practicewithspring.service.impl;

import com.example.practicewithspring.model.CustomUserDetails;
import com.example.practicewithspring.model.entity.User;
import com.example.practicewithspring.model.request.LoginRequest;
import com.example.practicewithspring.model.response.Data;
import com.example.practicewithspring.model.response.JwtResponse;
import com.example.practicewithspring.repository.UserRepository;
import com.example.practicewithspring.security.jwt.JwtTokenProvider;
import com.example.practicewithspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Override
    public Data findAllUsers(){
        List<User> users = userRepository.findAll();

        return new Data(true,"success",users);
    }
    @Override
    public Data findUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return new Data(true,"success",user);
    }
    @Override
    public Data editUser(Long id,User user){
        Optional<User> findUser = userRepository.findById(id);
        User editUser = findUser.get();
        editUser.setUser(user);
        userRepository.save(editUser);

        return new Data(true,"success",editUser);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public Data signup(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            return new Data(false,"username already exsists",user);
        }
        if(userRepository.existsByEmail(user.getEmail())){
            return new Data(false,"email already exsists",user);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);

        return new Data(true,"success",newUser);
    }

    @Override
    public Data login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = tokenProvider.generateToken(customUserDetails);

        return new Data(true,"token",new JwtResponse(token));
    }
}
