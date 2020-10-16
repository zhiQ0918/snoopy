package com.example.snoopy;

import lombok.Data;

/**
 *
 * @author maike
 * @date 2020/10/14
 */
@Data
public class LoginParam {

    private String username;
    private String password;
    private Boolean remember_me;
    private String captcha;
}
