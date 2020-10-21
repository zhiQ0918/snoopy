package com.example.snoopy.mock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.snoopy.model.Action;
import com.example.snoopy.model.RoleObj;
import com.example.snoopy.vo.OrgTreeVO;
import com.example.snoopy.vo.PermissionNoPager;
import com.example.snoopy.vo.RoleVO;
import com.example.snoopy.vo.ServerVO;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDataMockUtils {

    private static List<String> actions = Lists.newArrayList("add", "query", "get", "edit", "delete");

    public static List<Action> actionDatas() {
        List<Action> actions = new ArrayList<>();
        actions.add(new Action("query", "查询", false));
        actions.add(new Action("add", "新增", false));
        actions.add(new Action("get", "详情", false));
        actions.add(new Action("edit", "修改", false));
        actions.add(new Action("delete", "删除", false));

        return actions;
    }

    public static List<PermissionNoPager> permissionNoPagers() {

        List<PermissionNoPager> permissionNoPagers = new ArrayList<>();
        permissionNoPagers.add(new PermissionNoPager("marketing", "营销管理", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        permissionNoPagers.add(new PermissionNoPager("member", "会员管理", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        permissionNoPagers.add(new PermissionNoPager("menu", "菜单管理", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        permissionNoPagers.add(new PermissionNoPager("order", "订单管理", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        permissionNoPagers.add(new PermissionNoPager("permission", "权限管理", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        permissionNoPagers.add(new PermissionNoPager("role", "角色管理", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        permissionNoPagers.add(new PermissionNoPager("test", "测试权限", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        permissionNoPagers.add(new PermissionNoPager("user", "用户管理", JSON.toJSONString(actionDatas()), actions,actionDatas()));
        return permissionNoPagers;
    }

    public static JSONObject permissionWithPage() {
        JSONObject object = new JSONObject();
        object.put("data", permissionNoPagers());
        object.put("pageSize", 10);
        object.put("pageNo", 0);
        object.put("totalPage", 1);
        object.put("totalCount", 8);
        return object;
    }

    public static List<RoleVO> roleObjsNoPagers() {
        List<RoleVO> roleObjs = new ArrayList<>();
        roleObjs.add(new RoleVO(1L, "admin", "管理员", "拥有所有权限", permissionNoPagers()));
        roleObjs.add(new RoleVO(2L, "svip", "SVIP", "超级会员", permissionNoPagers()));
        roleObjs.add(new RoleVO(3L, "user", "普通会员", "普通会员，只读权限", permissionNoPagers()));
        return roleObjs;
    }

    public static JSONObject rolesWithPage() {
        JSONObject object = new JSONObject();
        object.put("data", roleObjsNoPagers());
        object.put("pageSize", 10);
        object.put("pageNo", 0);
        object.put("totalPage", 1);
        object.put("totalCount", 3);
        return object;
    }

    public static List<OrgTreeVO> orgTreeVOS() {

        OrgTreeVO t1 = new OrgTreeVO("key-02-03-01", "财务制度建设", null);
        OrgTreeVO t2 = new OrgTreeVO("key-02-03-02", "会计核算", null);
        OrgTreeVO t3 = new OrgTreeVO("key-02-03", "内部控制", null);
        t3.setChildren(Lists.newArrayList(t1, t2));

        OrgTreeVO t4 = new OrgTreeVO("key-02-01", "会计核算", null);
        OrgTreeVO t5 = new OrgTreeVO("key-02-02", "成本控制", null);
        OrgTreeVO t6 = new OrgTreeVO("key-02", "财务部", null);
        t6.setIcon("dollar");
        t6.setChildren(Lists.newArrayList(t4, t5, t3));

        OrgTreeVO t7 = new OrgTreeVO("key-01", "研发中心", null);
        t7.setIcon("mail");
        OrgTreeVO t8 = new OrgTreeVO("key-01-01", "后端组", true);
        OrgTreeVO t10 = new OrgTreeVO("key-01-01-01", "JAVA", null);
        OrgTreeVO t11 = new OrgTreeVO("key-01-01-02", "GROOVY", null);
        OrgTreeVO t12 = new OrgTreeVO("key-01-01-03", "Golang", null);
        t8.setChildren(Lists.newArrayList(t10, t11, t12));

        OrgTreeVO t9 = new OrgTreeVO("key-01-02", "前端组", true);
        OrgTreeVO t13 = new OrgTreeVO("key-01-02-01", "React", null);
        OrgTreeVO t14 = new OrgTreeVO("key-01-02-02", "Vue", null);
        OrgTreeVO t15 = new OrgTreeVO("key-01-02-03", "Angular", null);
        t9.setChildren(Lists.newArrayList(t13, t14, t15));
        t7.setChildren(Lists.newArrayList(t8, t9));
        return Lists.newArrayList(t7,t6);
    }

    public static List<ServerVO> serverVOS(){
        List<ServerVO> vos = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ServerVO vo = new ServerVO();
            vo.setKey(i);
            vo.setId(i);
            vo.setNo("no"+i);
            vo.setDescription("这是一段描述");
            vo.setCallNo(RandomUtils.nextInt(1,999));
            vo.setStatus(RandomUtils.nextInt(0,3));
            vo.setUpdatedAt(LocalDateTime.now());
            vo.setEditable(true);
            vos.add(vo);
        }
        return vos;
    }

    public static JSONObject getServerVOSWithPager(){
        JSONObject object = new JSONObject();
        object.put("data", serverVOS());
        object.put("pageSize", 10);
        object.put("pageNo", 0);
        object.put("totalPage", 2);
        object.put("totalCount", 15);
        return object;
    }



}
