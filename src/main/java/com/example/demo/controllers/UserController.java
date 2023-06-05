package com.example.demo.controllers;

import com.example.demo.JwtService;

import com.example.demo.config.UserInfoUserDetailsService;
import com.example.demo.extra.User;
import com.example.demo.extra.UserResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoUserDetailsService userInfoUserDetailsService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }

    @PostMapping("/getUser")
    public UserResponse login(@RequestBody User user){

        try {
            System.out.println("1");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
//            System.out.println(SecurityContextHolder.getContext().getAuthentication());

            // Generate Jwt Token
            UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername(user.getUsername());
            String token = jwtService.generateToken(userDetails.getUsername());

            return new UserResponse(true, token);
        } catch (AuthenticationException e) {
            return new UserResponse(false, "Sorry, authentication failed.");
        }
    }


    @GetMapping("/private")
    public String privateUrl() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "you are authorized so you got this";
    }


    @GetMapping("/method1")
    public String method1(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "private method1";
    }
}
