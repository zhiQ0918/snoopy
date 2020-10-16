package com.example.snoopy.model;

import lombok.Data;

import java.util.List;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Data
public class RoleObj {
    private String id;
    private String name;
    private String describe;
    private Integer status;
    private String creatorId;
    private Long createTime;
    private Integer deleted;
    private List<Permission> permissions;
}
