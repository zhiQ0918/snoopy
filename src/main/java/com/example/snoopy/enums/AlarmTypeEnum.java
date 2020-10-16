package com.example.snoopy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mac
 */
@Getter
@AllArgsConstructor
public enum AlarmTypeEnum {
    Ding(1,"钉钉消息"),
    Email(2,"邮件通知"),
    Message(3,"短信通知");

    private int type;
    private String desc;

    public static String getDesc(int type) {
        for (AlarmTypeEnum value : values()) {
            if (value.type == type) {
                return value.desc;
            }
        }
        return "未定义类型";
    }

}
