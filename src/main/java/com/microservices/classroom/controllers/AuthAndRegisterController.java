package com.microservices.classroom.controllers;

import com.microservices.classroom.entity.User;
import com.microservices.classroom.service.AuthRegisterService;
import com.microservices.classroom.vo.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthAndRegisterController {

    @Autowired
    private AuthRegisterService service;

    @PostMapping("/createUser")
    public GenericResponse createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @PostMapping("/loginUser")
    public GenericResponse loginUser(@RequestBody User user){
        return service.loginUser(user);
    }

}
