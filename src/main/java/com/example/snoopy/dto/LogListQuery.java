package com.example.snoopy.dto;

import lombok.Data;

@Data
public class LogListQuery {
    private Long logId;
    private String appName;
    private String owner;
    private String createTime;
    private Integer status;
}
