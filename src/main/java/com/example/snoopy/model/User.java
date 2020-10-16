package com.example.snoopy.model;

import lombok.Data;

/**
 *
 * @author maike
 * @date 2020/10/14
 */
@Data
public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private String avatar;
    private Integer status;
    private String telephone;
    private String lastLoginIp;
    private Long lastLoginTime;
    private String creatorId;
    private Long creatTime;
    private String merchantCode;
    private Integer deleted;
    private String roleId;
    private RoleObj role;
}
