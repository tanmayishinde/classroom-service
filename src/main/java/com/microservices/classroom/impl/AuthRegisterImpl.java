package com.microservices.classroom.impl;

import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.exception.ValidationException;
import com.microservices.classroom.repo.UserRepo;
import com.microservices.classroom.service.AuthRegisterService;
import com.microservices.classroom.util.DBQuery;
import com.microservices.classroom.vo.GenericResponse;
import com.microservices.classroom.entity.User;
import com.microservices.classroom.vo.OtpResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor

public class AuthRegisterImpl implements AuthRegisterService {

    @Autowired
    private UserRepo userRepo;
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
    public OtpResponse forgetPassword(User user){
        DBUserInterface tempUser = userRepo.loginUser(user.getEmail());
        String generatedOtp;
        if (tempUser != null)
        {

            generatedOtp = DBQuery.generateOTP(); // Random OTP GENERATED

            String uri = "http://localhost:8087/classroomutility/user/sendEmail?userEmail=%s&firstName=%s&generatedOtp=%s";
            uri = String.format(uri,tempUser.getemail(), tempUser.getfirstName(),generatedOtp);
            RestTemplate restTemplate = new RestTemplate();
            GenericResponse result =  restTemplate.getForObject(uri, GenericResponse.class);

            if ((result!= null)&&(result.getStatus().equalsIgnoreCase("SUCCESS")))
            {
                return new OtpResponse(generatedOtp);
            }
        }
        throw new ValidationException("USER_DOESN'T_EXISTS");
    }

    @Override
    public GenericResponse updatePassword(User user){

            int rowsUpdated;
            rowsUpdated = userRepo.updatePassword(user.getPassword(), user.getEmail());

            if(rowsUpdated > 0){
                return new GenericResponse("SUCCESS");
            }

        throw new ValidationException("PASSWORD_UPDATION_FAILED");
    }

    @Override
    public List<DBUserInterface> getUserByStatus(int status)
    {
//        List<DBUserInterface> listOfUsers = userRepo.getUserByStatus(status);
//         return listOfUsers;

        List<DBUserInterface> listOfUsers = userRepo.getUserByStatus(status);
        return  listOfUsers.stream().sorted(Comparator.comparing(DBUserInterface::getemail)).collect(Collectors.toList());

    }

}
