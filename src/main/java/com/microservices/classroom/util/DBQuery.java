package com.microservices.classroom.util;

import java.util.Random;

public final class DBQuery {

    public static final String insertUserQuery
            = "insert into [User] (FirstName, LastName, Email, Password, Mobile, Role, Status) " +
            "values (?, ?, ?, ?, ?, ?, ?)";

    public static final String loginUserQuery
            = "Select * from [User] " +
            "WHERE Email = ?";


    public static final String generateOTP(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static final String updatePasswordQuery
            = "UPDATE [User] " +
            "SET Password = ? " +
            "WHERE Email = ?";

    public static final String getUserByStatus
            = "SELECT * from [User] " +
            "WHERE Status = ?";

    public static final String insertClassroomQuery
            = "insert into [Classroom] (ClassroomName, ClassroomDescription, ClassroomSubject, DateCreated, CreatedBy) " +
            "values (?, ?, ?, ?, ?)";

    public static final String updateClassroomQuery
            = "UPDATE Classroom " +
            "SET ClassroomName = ? , ClassroomDescription =? , ClassroomSubject =? , CreatedBy =? " +
            "WHERE ClassroomId = ?";

    public static final String getClassroomByTeacherIdQuery
            = "SELECT * from [Classroom] " +
            "WHERE createdBy = ?";

    public static final String getClassroomByClassroomIdQuery
            = "SELECT * from [Classroom] " +
            "WHERE classroomId = ?";


    public static final String deleteClassroomByClassroomIdQuery
            = "Delete  from [Classroom] " +
            "WHERE classroomId = ?";

}
