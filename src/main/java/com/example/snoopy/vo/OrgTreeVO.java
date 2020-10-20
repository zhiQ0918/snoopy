package com.example.snoopy.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrgTreeVO {

    private String key;
    private String title;
    private String icon;
    private Boolean group;
    private List<OrgTreeVO> children;

    public OrgTreeVO(String key, String title, Boolean group) {
        this.key = key;
        this.title = title;
        this.group = group;
    }
}
