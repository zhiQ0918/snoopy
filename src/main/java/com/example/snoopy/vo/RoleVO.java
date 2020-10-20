package com.example.snoopy.vo;

import com.example.snoopy.model.Permission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoleVO {

    private Long id;
    private String roleId;
    private String name;
    private String describe;
    private Integer status;
    private String creatorId;
    private Long createTime;
    private Integer deleted;
    private List<PermissionNoPager> permissions;

    public RoleVO(Long id, String roleId, String name, String describe, List<PermissionNoPager> permissions) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.describe = describe;
        this.permissions = permissions;
        this.status = 1;
        this.creatorId = "system";
        this.createTime = System.currentTimeMillis();
        this.deleted = 0;
    }
}
