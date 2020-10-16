package com.example.snoopy.vo;

import lombok.Data;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Data
public class LogVO {

    private Long logId;
    private String appName;
    private String exceptionInfo;
    private String alarmInfo;
    private String httpInfo;
    private String owner;
    private String desc;
    private Integer retryCount;
    private String status;
    private String createTime;
    private String updateTime;
}
