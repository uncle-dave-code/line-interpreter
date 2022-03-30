package com.dscfgos.interpreter.expression.interfaces;

public interface NonTerminalExpression extends Expression {
   public void setValues(Expression firstExpression, Expression secondExpression);
}
