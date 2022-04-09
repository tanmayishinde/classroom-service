package com.microservices.classroom.service;


import com.microservices.classroom.entity.Classroom;
import com.microservices.classroom.entity.DBClassroomInterface;
import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.vo.GenericResponse;

import java.util.List;

public interface TeacherService
{

     GenericResponse createClassroom(Classroom classroom);

     GenericResponse updateClassroom(Classroom classroom);

     List<DBClassroomInterface> getClassroomByTeacherId(long  createdBy);
}
