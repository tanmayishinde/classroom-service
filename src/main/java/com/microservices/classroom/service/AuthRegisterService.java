package com.microservices.classroom.service;

import com.microservices.classroom.vo.GenericResponse;
import com.microservices.classroom.entity.User;

public interface AuthRegisterService {

    GenericResponse createUser(User user);

    GenericResponse loginUser(User user);

    GenericResponse forgetPassword(User user);

    GenericResponse otpVerification(String otp);

    GenericResponse updatePassword(User user);
}
