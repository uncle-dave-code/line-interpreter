package com.dscfgos.interpreter.expression.relational;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.NonTerminalExpression;

public class LessThanEqualExpression implements NonTerminalExpression {

    private Expression firstExpression, secondExpression;

    public LessThanEqualExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    public LessThanEqualExpression() {
        super();
    }

    @Override
    public Object interpret() {
        return Double.valueOf(this.firstExpression.interpret().toString()).compareTo(Double.valueOf(this.secondExpression.interpret().toString())) <= 0;
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public String toString() {
        return "<=";
    }

    @Override
    public void setValues(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
}
