package com.microservices.classroom.util;

public final class DBQuery {

    public static final String insertUserQuery
            = "insert into [User] (FirstName, LastName, Email, Password, Mobile, Role, Status) " +
            "values (?, ?, ?, ?, ?, ?, ?)";
}
