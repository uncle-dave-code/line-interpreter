package com.dscfgos.interpreter.expression.arithmetic;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.NonTerminalExpression;

public class PowExpression implements NonTerminalExpression {

    private Expression firstExpression, secondExpression;

    public PowExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    public PowExpression() {
        super();
    }


    @Override
    public Object interpret() {
        return Math.pow(Double.valueOf(this.firstExpression.interpret().toString()) , Double.valueOf(this.secondExpression.interpret().toString()));
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public String toString() { return "^"; }

    @Override
    public void setValues(Expression value1, Expression value2) {
        this.firstExpression = value1;
        this.secondExpression = value2;
    }
}
