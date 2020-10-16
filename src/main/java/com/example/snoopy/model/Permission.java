package com.example.snoopy.model;

import lombok.Data;

import java.util.List;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Data
public class Permission {

    private String roleId;
    private String permissionId;
    private String permissionName;
    private String actions;
    private List<Action> actionEntitySet;
    private String actionList;
    private String dataAccess;

}
