package com.microservices.classroom.entity;

import org.springframework.stereotype.Component;

@Component
public interface DBClassroomInterface {
    Long getClassroomId();

    String getClassroomName();


    String getClassroomDescription();

    String getClassroomSubject();

    String getDateCreated();

    Long getCreatedBy();

}
