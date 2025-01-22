package com.example.picpaysimplificado.controllers;

import com.example.picpaysimplificado.DTO.UserDTO;
import com.example.picpaysimplificado.domain.user.User;
import com.example.picpaysimplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
       List<User> listUser = this.userService.getAllUser();
       return new ResponseEntity<>(listUser, HttpStatus.OK);
    }
}
