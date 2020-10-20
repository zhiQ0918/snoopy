package com.example.snoopy.dao;

import com.example.snoopy.model.Permission;
import com.example.snoopy.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDAO {

    void insert(Permission permission);

    Permission getById(Long id);

    List<Permission> getByRoleId(String roleId);

    List<Permission> queryPermissions();


}
