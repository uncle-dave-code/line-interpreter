package com.dscfgos.interpreter.expression.interfaces;

public interface Expression {
    Object interpret();

    boolean isOperator();
}
