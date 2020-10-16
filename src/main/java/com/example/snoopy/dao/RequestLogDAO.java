package com.example.snoopy.dao;

import com.example.snoopy.model.RequestLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bouncycastle.cert.ocsp.Req;

import java.util.List;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Mapper
public interface RequestLogDAO {

    /**
     * 新增记录
     * @param log
     */
    void insert(RequestLog log);

    /**
     *
     * @return
     */
    List<RequestLog> query();

    /**
     *
     * @param id
     * @param httpResult
     */
    void updateResult(@Param("id") Long id,@Param("httpResult") String httpResult);

    /**
     *
     * @param id
     * @param alarmStatus
     */
    void updateAlarmResult(@Param("id") Long id,@Param("alarmStatus") Integer alarmStatus);

    /**
     *
     * @param log
     */
    void updateLog(RequestLog log);
}
