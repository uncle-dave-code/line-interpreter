package com.dscfgos.interpreter.expression;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.TerminalExpression;

public class BooleanExpression implements TerminalExpression {

    private Boolean value;

    public BooleanExpression(Boolean value) {
        this.value = value;
    }

    public BooleanExpression(String value) {
        this.value = Boolean.valueOf(value);
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
