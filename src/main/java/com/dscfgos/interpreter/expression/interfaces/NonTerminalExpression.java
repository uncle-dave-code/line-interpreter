package com.dscfgos.interpreter.expression.interfaces;

public interface NonTerminalExpression extends Expression {
   void setValues(Expression firstExpression, Expression secondExpression);
}
