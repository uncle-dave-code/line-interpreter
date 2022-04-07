package com.dscfgos.interpreter.utils;

import lombok.Data;

@Data
public class GroupPosition {

    public GroupPosition(int start, int end, String group) {
        this.start = start;
        this.end = end;
        this.group = group;
    }

    private int start;
    private int end;
    private String group;
}
