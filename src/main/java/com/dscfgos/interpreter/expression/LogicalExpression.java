package com.dscfgos.interpreter.expression;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.TerminalExpression;

public class LogicalExpression implements TerminalExpression {

    private Object value;

    public LogicalExpression(Boolean value) {
        this.value = value;
    }

    public LogicalExpression(String value) {
        this.value = Boolean.valueOf(value);
    }

    public LogicalExpression(Object value) {
        this.value = value;
    }

    @Override
    public boolean isOperator(){ return false;}

    @Override
    public Object interpret() {
        return value;
    }

    @Override
    public void setValue(Expression expression) {
        this.value = (Boolean) expression.interpret();
    }
}
