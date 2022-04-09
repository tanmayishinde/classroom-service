package com.microservices.classroom.controllers;

import com.microservices.classroom.entity.Classroom;

import com.microservices.classroom.entity.DBClassroomInterface;
import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.entity.User;
import com.microservices.classroom.service.TeacherService;
import com.microservices.classroom.vo.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studyRoom")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @PostMapping("/createClassroom")
    public GenericResponse createClassroom(@RequestBody Classroom classroom) {
        return teacherService.createClassroom(classroom);
    }

    @PutMapping("/updateClassroom")
    public GenericResponse updateClassroom(@RequestBody Classroom classroom) {
        return teacherService.updateClassroom(classroom);
    }

    @GetMapping("/getClassroomByTeacherId")
    public List<DBClassroomInterface> getClassroomByTeacherId(@RequestParam long createdBy) {
        return teacherService.getClassroomByTeacherId(createdBy);
    }
}