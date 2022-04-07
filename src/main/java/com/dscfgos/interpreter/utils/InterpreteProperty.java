package com.dscfgos.interpreter.utils;

import lombok.Data;

@Data
public class InterpreteProperty {

    public InterpreteProperty(String code, Object value) {
        this.code = code;
        this.value = value;
    }

    private String code;
    private Object value;
}
