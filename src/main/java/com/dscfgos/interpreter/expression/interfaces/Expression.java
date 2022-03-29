package com.dscfgos.interpreter.expression.interfaces;

public interface Expression {
    public Object interpret();

    public boolean isOperator();
}
