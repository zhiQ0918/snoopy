package com.example.snoopy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author maike
 * @date 2020/10/14
 */
@Getter
@AllArgsConstructor
public enum AlarmStatusEnum {
    init(1, "初始状态"),
    success(2, "告警成功"),
    fail(3, "告警失败");

    private int status;
    private String desc;

    public static String getDesc(int status) {
        for (AlarmStatusEnum value : values()) {
            if (value.status == status) {
                return value.desc;
            }
        }
        return "未定义状态";
    }
}
