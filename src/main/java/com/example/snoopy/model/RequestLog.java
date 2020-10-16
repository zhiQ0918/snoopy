package com.example.snoopy.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * appName | className | methodName | owner | exception | desc | retryCount | alarmType | alarmStatus | status | http_method | url | param
 *
 * @author maike
 */
@Data
public class RequestLog {

    private Long id;
    private String appName;
    private String className;
    private String methodName;
    private String owner;
    private String exception;
    private String desc;
    private Integer retryCount;
    private Integer alarmType;
    private Integer alarmStatus;
    private Integer status;
    private String httpMethod;
    private String httpUrl;
    private String httpParam;
    private String httpResult;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
