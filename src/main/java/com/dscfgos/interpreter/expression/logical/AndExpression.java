package com.dscfgos.interpreter.expression.logical;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.NonTerminalExpression;

public class AndExpression implements NonTerminalExpression {

    private Expression firstExpression, secondExpression;

    public AndExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    public AndExpression() {
        super();
    }

    @Override
    public Object interpret() {
        return Boolean.valueOf(this.firstExpression.interpret().toString()) && Boolean.valueOf(this.secondExpression.interpret().toString());
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public String toString() {
        return "&&";
    }

    @Override
    public void setValues(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
}
