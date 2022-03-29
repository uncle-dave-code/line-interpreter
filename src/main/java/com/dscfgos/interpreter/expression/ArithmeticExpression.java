package com.dscfgos.interpreter.expression;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.TerminalExpression;

public class ArithmeticExpression implements TerminalExpression {

    private Double value;

    public ArithmeticExpression(Double value) {
        this.value = value;
    }

    public ArithmeticExpression(String value) {
        this.value = Double.valueOf(value);
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
