package com.example.snoopy.dao;

import com.example.snoopy.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {

    void insert(User user);

    User getById(Long id);

    List<User> queryUsers();


}
