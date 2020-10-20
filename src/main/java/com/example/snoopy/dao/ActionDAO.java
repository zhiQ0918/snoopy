package com.example.snoopy.dao;

import com.example.snoopy.model.Action;
import com.example.snoopy.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActionDAO {

    void insert(Action action);

    Action getById(Long id);

    List<Action> queryActions();

    List<Action> queryByIds(@Param("list")List<Integer> list);


}
