package com.example.snoopy.alarm;

import com.example.snoopy.model.RequestLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Slf4j
@Component
public class RpcAlarmExecutor {

    @Resource
    private Map<String,RpcAlarm> alarmMap;

    public boolean alarm(RequestLog requestLog){
        boolean result = false;
        if (alarmMap!=null&&!alarmMap.isEmpty()){
            result = true;
            for (RpcAlarm alar : alarmMap.values()) {
                boolean resultItem = false;
                try {
                    resultItem = alar.doAlarm(requestLog);
                }catch (Exception e){
                   log.error(e.getMessage(),e);
                }
                if (!resultItem){
                    result = false;
                }
            }
        }
        return result;
    }
}
