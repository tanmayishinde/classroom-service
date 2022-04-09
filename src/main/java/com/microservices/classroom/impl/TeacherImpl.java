package com.microservices.classroom.impl;

import com.microservices.classroom.entity.Classroom;
import com.microservices.classroom.entity.DBClassroomInterface;

import com.microservices.classroom.exception.ValidationException;
import com.microservices.classroom.repo.ClassroomRepo;
import com.microservices.classroom.service.TeacherService;
import com.microservices.classroom.vo.GenericResponse;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class TeacherImpl implements TeacherService
{

    @Autowired
    private ClassroomRepo classroomRepo;

    public GenericResponse createClassroom(Classroom classroom)
    {
        int rowsUpdated;
        rowsUpdated = classroomRepo.insertClassroom(classroom.getClassroomName(), classroom.getClassroomDescription(),
                classroom.getClassroomSubject(), classroom.getDateCreated(), classroom.getCreatedBy());

        if(rowsUpdated > 0){
            return new GenericResponse("SUCCESS");
        }
        throw new ValidationException("USER_NOT_CREATED");
    }

    @Override
    public GenericResponse updateClassroom(Classroom classroom) {
        int rowsUpdated;

//        Long checkClassroomId = classroom.getClassroomId(); //

        DBClassroomInterface tempClassroom = classroomRepo.getClassroomByClassroomId(classroom.getClassroomId());

        if (tempClassroom != null)
        {

            rowsUpdated = classroomRepo.updateClassroom(classroom.getClassroomName(), classroom.getClassroomDescription(),
                    classroom.getClassroomSubject(),classroom.getCreatedBy(),classroom.getClassroomId());

            if(rowsUpdated > 0)
            {
                return new GenericResponse("SUCCESS");

            }

        throw new ValidationException("Classroom Details_UPDATION_FAILED");

        }

        throw new ValidationException("");
    }

    @Override
    public List<DBClassroomInterface> getClassroomByTeacherId(long createdBy)
    {
//        List<DBUserInterface> listOfUsers = userRepo.getUserByStatus(status);
//         return listOfUsers;

        List<DBClassroomInterface> listOfClassroom = classroomRepo.getClassroomByTeacherId(createdBy);
        return  listOfClassroom.stream().sorted(Comparator.comparing(DBClassroomInterface::getCreatedBy)).collect(Collectors.toList());

    }
}
