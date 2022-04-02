package com.microservices.classroom.impl;

import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.exception.ValidationException;
import com.microservices.classroom.repo.UserRepo;
import com.microservices.classroom.service.AuthRegisterService;
import com.microservices.classroom.vo.GenericResponse;
import com.microservices.classroom.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor

public class AuthRegisterImpl implements AuthRegisterService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public GenericResponse createUser(User user) {

        int rowsUpdated;
        rowsUpdated = userRepo.insertUser(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), Long.parseLong(user.getMobile()), user.getRole(), user.getStatus());
        if(rowsUpdated > 0){
            return new GenericResponse("SUCCESS");
        }
        throw new ValidationException("USER_NOT_CREATED");
    }

    @Override
    public GenericResponse loginUser(User user) {
        int rowsUpdated;

        String checkEmail = user.getEmail();

        DBUserInterface tempUser = userRepo.loginUser(checkEmail); //UI ka User

        if (tempUser != null)
        {
            if(tempUser.getpassword().equals(user.getPassword()))
            {
                return new GenericResponse("SUCCESS");
            }
            else
            {
                throw new ValidationException("INVALID_USERNAME_PASSWORD");
            }
        }

        throw new ValidationException("Please Signup!");
    }
}
