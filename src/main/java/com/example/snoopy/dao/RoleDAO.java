package com.example.snoopy.dao;

import com.example.snoopy.model.RoleObj;
import com.example.snoopy.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDAO {

    void insert(RoleObj roleObj);

    RoleObj getById(Long id);

    RoleObj getByRoleId(String id);

    List<RoleObj> queryRoles();


}
