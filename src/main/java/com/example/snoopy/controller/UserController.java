package com.example.snoopy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.snoopy.BizResult;
import com.example.snoopy.dao.ActionDAO;
import com.example.snoopy.dao.PermissionDAO;
import com.example.snoopy.dao.RoleDAO;
import com.example.snoopy.dao.UserDAO;
import com.example.snoopy.mock.UserDataMockUtils;
import com.example.snoopy.model.Action;
import com.example.snoopy.model.Permission;
import com.example.snoopy.model.RoleObj;
import com.example.snoopy.model.User;
import com.example.snoopy.vo.OrgTreeVO;
import com.example.snoopy.vo.PermissionNoPager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maike
 * @date 2020/10/14
 */
@RestController
public class UserController {

    @Resource
    private UserDAO userDAO;
    @Resource
    private RoleDAO roleDAO;
    @Resource
    private PermissionDAO permissionDAO;
    @Resource
    private ActionDAO actionDAO;

    @GetMapping("/user/info")
    public BizResult<User> info() {
        User user = userDAO.getById(1L);
        String roleId = user.getRoleId();

        List<Permission> permissions = permissionDAO.getByRoleId(roleId);
        for (Permission permission : permissions) {
            permission.setActionEntitySet(UserDataMockUtils.actionDatas());
            permission.setActions(JSON.toJSONString(UserDataMockUtils.actionDatas()));
        }
        RoleObj roleObj = roleDAO.getByRoleId(roleId);
        roleObj.setPermissions(permissions);

        user.setRole(roleObj);

        return BizResult.create(user);
    }

    @GetMapping("org/tree")
    public BizResult<List<OrgTreeVO>> orgTree(){
        return BizResult.create(UserDataMockUtils.orgTreeVOS());
    }

    @GetMapping("role")
    public BizResult<JSONObject> role(){
        return BizResult.create(UserDataMockUtils.rolesWithPage());
    }

    @GetMapping("permission")
    public BizResult<JSONObject> permission(){
        return BizResult.create(UserDataMockUtils.permissionWithPage());
    }

    @GetMapping("permission/no-pager")
    public BizResult<List<PermissionNoPager>> permissionNoPage(){
        return BizResult.create(UserDataMockUtils.permissionNoPagers());
    }

    @GetMapping("/service")
    public BizResult<User> getServiceList() {
        User user = userDAO.getById(1L);
        String roleId = user.getRoleId();

        List<Permission> permissions = permissionDAO.getByRoleId(roleId);
        for (Permission permission : permissions) {
            String actionIds = permission.getActionIds();
            List<Integer> actionIdList = JSON.parseArray(actionIds, Integer.class);
            List<Action> actions = actionDAO.queryByIds(actionIdList);
            permission.setActionEntitySet(actions);
            permission.setActions(JSON.toJSONString(actions));
        }
        RoleObj roleObj = roleDAO.getByRoleId(roleId);
        roleObj.setPermissions(permissions);

        user.setRole(roleObj);

        return BizResult.create(user);
    }

    @GetMapping("/user")
    public BizResult<User> getUserList() {
        User user = userDAO.getById(1L);
        String roleId = user.getRoleId();

        List<Permission> permissions = permissionDAO.getByRoleId(roleId);
        for (Permission permission : permissions) {
            String actionIds = permission.getActionIds();
            List<Integer> actionIdList = JSON.parseArray(actionIds, Integer.class);
            List<Action> actions = actionDAO.queryByIds(actionIdList);
            permission.setActionEntitySet(actions);
            permission.setActions(JSON.toJSONString(actions));
        }
        RoleObj roleObj = roleDAO.getByRoleId(roleId);
        roleObj.setPermissions(permissions);

        user.setRole(roleObj);

        return BizResult.create(user);
    }
}
