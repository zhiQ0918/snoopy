package com.example.snoopy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Data
@NoArgsConstructor
public class RoleObj {
    private Long id;
    private String roleId;
    private String name;
    private String describe;
    private Integer status;
    private String creatorId;
    private Long createTime;
    private Integer deleted;
    private List<Permission> permissions;
}
