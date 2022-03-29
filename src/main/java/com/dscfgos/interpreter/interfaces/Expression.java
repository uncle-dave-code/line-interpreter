package com.dscfgos.interpreter.interfaces;

public interface Expression<T> {
    public T interprete(String context, Object... params);
}
