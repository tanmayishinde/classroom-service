package com.microservices.classroom.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
    private String errorCode;
    private String errorMessage;
}
