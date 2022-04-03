package com.microservices.classroom.service;

import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.vo.GenericResponse;
import com.microservices.classroom.entity.User;
import com.microservices.classroom.vo.OtpResponse;

import java.util.List;

public interface AuthRegisterService {

    GenericResponse createUser(User user);

    GenericResponse loginUser(User user);

    OtpResponse forgetPassword(User user);

//    GenericResponse otpVerification(String otp);

    GenericResponse updatePassword(User user);

    List<DBUserInterface> getUserByStatus(int status);
}
