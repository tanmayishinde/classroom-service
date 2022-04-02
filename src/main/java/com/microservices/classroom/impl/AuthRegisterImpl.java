package com.microservices.classroom.impl;

import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.exception.ValidationException;
import com.microservices.classroom.repo.UserRepo;
import com.microservices.classroom.service.AuthRegisterService;
import com.microservices.classroom.util.DBQuery;
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
   // private String generatedOtp;


    private String forEmail;
    private DBQuery dbQuery;




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


    @Override
    public GenericResponse forgetPassword(User user){
        DBUserInterface tempUser = userRepo.loginUser(user.getEmail());
        if (tempUser != null){
              generatedOtp = DBQuery.generateOTP(); // Random OTP GENERATED
            return new GenericResponse(generatedOtp);
        }
        throw new ValidationException("USER_DOESN'T_EXISTS");
    }
//    @Override
//    public GenericResponse otpVerification(String otp){
//        if (otp.equals(generatedOtp)) //Compare with UI wala
//        {
//            return new GenericResponse("OTP_VERIFICATION_SUCCESSFUL.");
//        }
//        throw new ValidationException("VERIFICATION_UNSUCCESSFUL.");
//    }

    @Override
    public GenericResponse updatePassword(User user){

            int rowsUpdated;
            rowsUpdated = userRepo.updatePassword(user.getPassword(), user.getEmail());

            if(rowsUpdated > 0){
                return new GenericResponse("SUCCESS");
            }

        throw new ValidationException("PASSWORD_UPDATION_FAILED");
    }

}
