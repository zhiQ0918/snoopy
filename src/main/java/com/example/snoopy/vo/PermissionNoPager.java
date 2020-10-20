package com.example.snoopy.vo;

import com.example.snoopy.model.Action;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PermissionNoPager {

    private String id;
    private String name;
    private String describe;
    private Integer status;
    private String actionData;
    private String sptDaTypes;
    private String optionalFields;
    private String parents;
    private String type;
    private Integer deleted;
    private List<String> actions;
    private List<Action> actionEntitySet;

    public PermissionNoPager(String id, String name, String actionData, List<String> actions,List<Action> actionEntitySet) {
        this.id = id;
        this.name = name;
        this.actionData = actionData;
        this.actions = actions;
        this.describe = null;
        this.status = 1;
        this.sptDaTypes = null;
        this.optionalFields = null;
        this.parents = null;
        this.type = null;
        this.deleted = 0;
        this.actionEntitySet = actionEntitySet;
    }
}
