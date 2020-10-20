package com.example.snoopy.controller;

import com.alibaba.fastjson.JSON;
import com.example.snoopy.BizResult;
import com.example.snoopy.LoginParam;
import com.example.snoopy.model.Action;
import com.example.snoopy.model.Permission;
import com.example.snoopy.model.RoleObj;
import com.example.snoopy.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maike
 * @date 2020/10/14
 */
@RestController
public class LoginController {

    @PostMapping("auth/login")
    public BizResult<User> login(@RequestBody LoginParam param){
        if (param.getUsername()=="admin"){

        }
        return BizResult.create(getUser());
    }

    @PostMapping("auth/2step-code")
    public BizResult<User> twoStepCode(){
        System.out.println("twoStepCode");
        return BizResult.create(getUser());
    }

    @PostMapping("auth/logout")
    public BizResult<User> loginOut(){
        System.out.println("logout");
        return BizResult.create(getUser());
    }






    User getUser(){
        User user = new User();
        user.setId(1L);
        user.setName("麦可");
        user.setUsername("admin");
        user.setPassword("");
        user.setAvatar("/avatar2.jpg");
        user.setStatus(1);
        user.setTelephone("");
        user.setLastLoginIp("27.154.74.117");
        user.setLastLoginTime(1534837621348L);
        user.setCreatorId("admin");
        user.setCreatTime(1497160610259L);
        user.setMerchantCode("TLif2btpzg079h15bk");
        user.setDeleted(0);
        user.setRoleId("admin");


        List<Action> actionList = new ArrayList<>();
        actionList.add(new Action("add","新增",false));
        actionList.add(new Action("query","查询",false));
        actionList.add(new Action("get","详情",false));
        actionList.add(new Action("update","修改",false));
        actionList.add(new Action("delete","删除",false));

        List<Permission> permissionList = new ArrayList<>();
        Permission permission1 = new Permission();
        permission1.setRoleId("admin");
        permission1.setPermissionId("dashboard");
        permission1.setPermissionName("仪表盘");
        permission1.setActions(JSON.toJSONString(actionList));
        permission1.setActionEntitySet(actionList);
        permission1.setActionList(null);
        permission1.setDataAccess(null);
        permissionList.add(permission1);
        Permission permission2 = new Permission();
        permission2.setRoleId("admin");
        permission2.setPermissionId("exception");
        permission2.setPermissionName("异常页面权限");
        permission2.setActions(JSON.toJSONString(actionList));
        permission2.setActionEntitySet(actionList);
        permission2.setActionList(null);
        permission2.setDataAccess(null);
        permissionList.add(permission2);
        Permission permission3 = new Permission();
        permission3.setRoleId("admin");
        permission3.setPermissionId("result");
        permission3.setPermissionName("结果权限");
        permission3.setActions(JSON.toJSONString(actionList));
        permission3.setActionEntitySet(actionList);
        permission3.setActionList(null);
        permission3.setDataAccess(null);
        permissionList.add(permission3);
        Permission permission4 = new Permission();
        permission4.setRoleId("admin");
        permission4.setPermissionId("profile");
        permission4.setPermissionName("详细页权限");
        permission4.setActions(JSON.toJSONString(actionList));
        permission4.setActionEntitySet(actionList);
        permission4.setActionList(null);
        permission4.setDataAccess(null);
        permissionList.add(permission4);

        Permission permission5 = new Permission();
        permission5.setRoleId("admin");
        permission5.setPermissionId("table");
        permission5.setPermissionName("表格权限");
        permission5.setActions(JSON.toJSONString(actionList));
        permission5.setActionEntitySet(actionList);
        permission5.setActionList(null);
        permission5.setDataAccess(null);
        permissionList.add(permission5);

        Permission permission6 = new Permission();
        permission6.setRoleId("admin");
        permission6.setPermissionId("form");
        permission6.setPermissionName("表单权限");
        permission6.setActions(JSON.toJSONString(actionList));
        permission6.setActionEntitySet(actionList);
        permission6.setActionList(null);
        permission6.setDataAccess(null);
        permissionList.add(permission6);

        Permission permission7 = new Permission();
        permission7.setRoleId("admin");
        permission7.setPermissionId("order");
        permission7.setPermissionName("订单管理");
        permission7.setActions(JSON.toJSONString(actionList));
        permission7.setActionEntitySet(actionList);
        permission7.setActionList(null);
        permission7.setDataAccess(null);
        permissionList.add(permission7);

        Permission permission8 = new Permission();
        permission8.setRoleId("admin");
        permission8.setPermissionId("permission");
        permission8.setPermissionName("权限管理");
        permission8.setActions(JSON.toJSONString(actionList));
        permission8.setActionEntitySet(actionList);
        permission8.setActionList(null);
        permission8.setDataAccess(null);
        permissionList.add(permission8);

        Permission permission9 = new Permission();
        permission9.setRoleId("admin");
        permission9.setPermissionId("role");
        permission9.setPermissionName("角色管理");
        permission9.setActions(JSON.toJSONString(actionList));
        permission9.setActionEntitySet(actionList);
        permission9.setActionList(null);
        permission9.setDataAccess(null);
        permissionList.add(permission9);

        Permission permission10 = new Permission();
        permission10.setRoleId("admin");
        permission10.setPermissionId("user");
        permission10.setPermissionName("用户管理");
        permission10.setActions(JSON.toJSONString(actionList));
        permission10.setActionEntitySet(actionList);
        permission10.setActionList(null);
        permission10.setDataAccess(null);
        permissionList.add(permission10);

        RoleObj roleObj = new RoleObj();
        roleObj.setRoleId("admin");
        roleObj.setName("管理员");
        roleObj.setDescribe("拥有所有权限");
        roleObj.setStatus(1);
        roleObj.setCreatorId("system");
        roleObj.setCreateTime(1497160610259L);
        roleObj.setDeleted(0);
        roleObj.setPermissions(permissionList);


        user.setRole(roleObj);
        return user;
    }
}
