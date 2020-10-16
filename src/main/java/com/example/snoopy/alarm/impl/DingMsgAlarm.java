package com.example.snoopy.alarm.impl;

import com.example.snoopy.alarm.RpcAlarm;
import com.example.snoopy.model.RequestLog;

public class DingMsgAlarm implements RpcAlarm {

    @Override
    public boolean doAlarm(RequestLog log) {
        return false;
    }
}
