package com.example.snoopy;

import com.example.snoopy.enums.AlarmStatusEnum;
import com.example.snoopy.enums.AlarmTypeEnum;

/**
 *
 * @author maike
 * @date 2020/10/14
 */
public class DefaultSettings {

    public static final int DEFAULT_RETRY_COUNT = 3;
    public static final int DEFAULT_ALARM_TYPE = AlarmTypeEnum.Ding.getType();
    public static final int DEFAULT_ALARM_STATUS = AlarmStatusEnum.init.getStatus();
    public static final int DEFAULT_STATUS = 0;

}
