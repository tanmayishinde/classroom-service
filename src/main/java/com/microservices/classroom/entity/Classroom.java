package com.microservices.classroom.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[Classroom]")
@Data
public class Classroom implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "ClassroomId")
    @Id
    private Long classroomId;

    @Column(name = "ClassroomName")
    private String classroomName;

    @Column(name = "ClassroomDescription")
    private String classroomDescription;

    @Column(name = "ClassroomSubject")
    private String classroomSubject;

    @Column(name = "DateCreated")
    private Timestamp dateCreated;


    @Column(name = "CreatedBy")
    private long createdBy;
}
