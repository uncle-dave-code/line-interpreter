package com.dscfgos.interpreter.classes;

import lombok.Data;

@Data
public class Property {

    public Property(String code, Object value) {
        this.code = code;
        this.value = value;
    }

    private String code;
    private Object value;
}
