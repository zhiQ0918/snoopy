package com.example.snoopy.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class DataSourceUtils {



    public static DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();


        return dataSource;
    }
}
