package com.microservices.classroom.entity;

import org.springframework.stereotype.Component;


@Component
public interface DBUserInterface {

    Long getuserId();

    String getfirstName();

    String getlastName();

    String getemail();

    String getpassword();

    String getmobile();

    String getrole();

    Integer getstatus();

}
