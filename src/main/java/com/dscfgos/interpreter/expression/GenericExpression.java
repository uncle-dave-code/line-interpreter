package com.dscfgos.interpreter.expression;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.TerminalExpression;

public class GenericExpression implements TerminalExpression {

    private Object value;

    public GenericExpression(Object value) {
        this.value = value;
    }

    public GenericExpression(String value) {
        this.value = value;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public Object interpret() {
        return value;
    }

    @Override
    public void setValue(Expression expression) {
        this.value = expression.interpret();
    }
}
