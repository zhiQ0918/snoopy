package com.example.snoopy.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServerVO {

    private Integer key;
    private Integer id;
    private String no;
    private String description;
    private Integer callNo;
    private Integer status;
    private LocalDateTime updatedAt;
    private Boolean editable;
}
