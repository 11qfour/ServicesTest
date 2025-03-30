package com.example.service_test_task.Exception;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ErrorMessage {
    private ZonedDateTime timeStamp;
    private int status;
    private String error;
    private String path;
    public ErrorMessage(int status, String error, String path){
        this.status=status;
        this.error=error;
        this.path=path;
        this.timeStamp=ZonedDateTime.now();
    }
}
