package com.example.snoopy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author maike
 * @date 2020/10/15
 */
@Getter
@AllArgsConstructor
public enum LogStatusEnum {

    init(0, "初始状态"),
    success(1, "处理成功");

    private int status;
    private String desc;

    public static String getDesc(int status) {
        for (LogStatusEnum value : values()) {
            if (value.status == status) {
                return value.desc;
            }
        }
        return "未定义状态";
    }

}
