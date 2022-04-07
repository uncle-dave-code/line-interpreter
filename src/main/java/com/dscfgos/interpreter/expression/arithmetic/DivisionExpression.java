package com.dscfgos.interpreter.expression.arithmetic;

import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.NonTerminalExpression;

public class DivisionExpression implements NonTerminalExpression {

    private Expression firstExpression, secondExpression;

    public DivisionExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    public DivisionExpression() {
        super();
    }

    @Override
    public Object interpret() {
        return Double.valueOf(this.firstExpression.interpret().toString()) / Double.valueOf(this.secondExpression.interpret().toString());
    }

    @Override
    public String toString() {
        return "/";
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public void setValues(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }
}
