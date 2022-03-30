package com.dscfgos.interpreter.expression;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.TerminalExpression;

public class RelationalExpression implements TerminalExpression {

    private Object value;

    public RelationalExpression(Double value) {
        this.value = value;
    }

    public RelationalExpression(String value) {
        this.value = Double.valueOf(value);
    }

    public RelationalExpression(Object value) {
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
        this.value = (Double) expression.interpret();
    }
}
