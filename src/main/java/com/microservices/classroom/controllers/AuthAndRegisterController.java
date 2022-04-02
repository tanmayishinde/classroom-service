package com.microservices.classroom.controllers;

import com.microservices.classroom.entity.User;
import com.microservices.classroom.service.AuthRegisterService;
import com.microservices.classroom.vo.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/forgetPassword")
    public GenericResponse forgetPassword(@RequestBody User user){
        return service.forgetPassword(user);
    }

    @GetMapping("/otpVerify")
    public GenericResponse otpVerification(@RequestParam String otp){
        return service.otpVerification(otp);
    }

    @PutMapping("/updatePassword")
    public GenericResponse updatePassword(@RequestBody User user) {
        return service.updatePassword(user);
    }

}
