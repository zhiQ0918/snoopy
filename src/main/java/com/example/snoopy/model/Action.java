package com.example.snoopy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author maike
 * @date 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    private Long id;
    private String action;
    private String describe;
    private Boolean defaultCheck;

    public Action(String action, String describe, Boolean defaultCheck) {
        this.action = action;
        this.describe = describe;
        this.defaultCheck = defaultCheck;
    }
}
