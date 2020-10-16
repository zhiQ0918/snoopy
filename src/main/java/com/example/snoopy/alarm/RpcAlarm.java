package com.example.snoopy.alarm;

import com.example.snoopy.model.RequestLog;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
public interface RpcAlarm {

    boolean doAlarm(RequestLog log);
}
