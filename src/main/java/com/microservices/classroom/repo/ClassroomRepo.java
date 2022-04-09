package com.microservices.classroom.repo;

import com.microservices.classroom.entity.Classroom;
import com.microservices.classroom.entity.DBClassroomInterface;
import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.entity.User;
import com.microservices.classroom.util.DBQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;


@Repository
public interface ClassroomRepo extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = DBQuery.insertClassroomQuery, nativeQuery = true)
    int insertClassroom(String classroomName, String classroomDescription, String classroomSubject,
                   Timestamp dateCreated, long createdBy);

    @Transactional
    @Modifying
    @Query(value = DBQuery.updateClassroomQuery, nativeQuery = true)
    int updateClassroom(String classroomName, String classroomDescription, String classroomSubject,
                      long createdBy, long classroomId);

    @Query(value = DBQuery.getClassroomByTeacherIdQuery, nativeQuery = true)
    List<DBClassroomInterface> getClassroomByTeacherId(Long createdBy);

    @Query(value = DBQuery.getClassroomByClassroomIdQuery, nativeQuery = true)
    DBClassroomInterface getClassroomByClassroomId(Long classroomId);

    @Transactional
    @Modifying
    @Query(value = DBQuery.deleteClassroomByClassroomIdQuery, nativeQuery = true)
    int deleteClassroomByClassroomId(long classroomId);
}
