package com.microservices.classroom.controllers;

import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.entity.User;
import com.microservices.classroom.service.AuthRegisterService;
import com.microservices.classroom.vo.GenericResponse;
import com.microservices.classroom.vo.OtpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public OtpResponse forgetPassword(@RequestBody User user){
        return service.forgetPassword(user);
    }
//
//    @GetMapping("/otpVerify")
//    public GenericResponse otpVerification(@RequestParam String otp){
//        return service.otpVerification(otp);
//    }

    @PutMapping("/updatePassword")
    public GenericResponse updatePassword(@RequestBody User user) {
        return service.updatePassword(user);
    }

    @GetMapping("/getUserByStatus")
    public List<DBUserInterface> getUserByStatus(@RequestParam int status) {
        return service.getUserByStatus(status);

    }

}
