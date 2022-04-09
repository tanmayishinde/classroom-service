package com.microservices.classroom.repo;

import com.microservices.classroom.entity.DBUserInterface;
import com.microservices.classroom.util.DBQuery;
import com.microservices.classroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = DBQuery.insertUserQuery, nativeQuery = true)
    int insertUser(String firstName,  String lastName, String email, String password,
                   long mobile, String role, Integer status);


    @Query(value = DBQuery.loginUserQuery, nativeQuery = true)
    DBUserInterface loginUser(String email);

    @Transactional
    @Modifying
    @Query(value = DBQuery.updatePasswordQuery, nativeQuery = true)
    int updatePassword(String password,String email);

    @Query(value = DBQuery.getUserByStatus, nativeQuery = true)
    List<DBUserInterface> getUserByStatus(Integer status);



}
