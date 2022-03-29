package com.dscfgos.interpreter.expression;

import com.dscfgos.interpreter.classes.OperatorsUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.NonTerminalExpression;

import java.util.List;
import java.util.Stack;

public class ExpressionParser {

    public String interprete(String context) {
        return this.interprete(context, null);
    }

    public String interprete(String context, List<Property> params) {
        return context;
    }


    /**
     * Rules:
     * 1: Scan from Left to Right
     * 2: If the scanned item is an ‘(‘, push it to the stack.
     * 3: If the scanned item is an ‘)’, pop the stack and output it until a ‘(‘ is encountered, and ignore both parenthesis.
     * 4: If the scanned item is an operator
     * a) If the precedence of operator is greater than the precedence of the operator in the stack or the stack is empty or the stack contains a ‘(‘ , push it.
     * b) Pop all the operators from the stack which are greater than or equal to in precedence than that of the scanned operator. After doing that Push the scanned operator to the stack. (If you encounter parenthesis while popping then stop there and push the scanned operator in the stack.
     * 5: If the scanned item is an operand, output it.
     * 6: Pop and output from the stack until it is not empty
     *
     * @param context Infix expression
     * @return Postfix expression
     */
    public String convertToPostFixExpression(String context) {
        String output = "";

        List<String> expression = OperatorsUtils.splitDirective(context);
        if (expression != null && !expression.isEmpty()) {
            Stack<String> stack = new Stack<>();
            for (var item : expression) {
                // If current item is an '(' then push it to the stack
                if (item.equals("(")) {
                    stack.push(item);
                }
                // If current item is an ')' then pop an add to output until '(' is encountered.
                else if (item.equals(")")) {
                    while (!stack.isEmpty()) {
                        String data = stack.pop();
                        if (data.equals("(")) {
                            break;
                        }
                        output += " " + data;
                    }
                }
                // If current item is an operator
                else if (OperatorsUtils.isOperator(item)) {
                    while (!stack.isEmpty() && OperatorsUtils.Precedence(item) <= OperatorsUtils.Precedence(stack.peek())) {
                        output += " " + stack.pop();
                    }
                    stack.push(item);
                }
                // If the current item is an operand, output it.
                else {
                    output += " " + item;
                }
            }

            while (!stack.isEmpty()) {
                if (stack.peek() == "(")
                    return "Invalid Expression";
                output += " " + stack.pop();
            }
        }
        return output;
    }

    public Expression evaluatePostFixArithmeticExpression(String postfix) {
        Expression output = null;
        if (postfix != null && !postfix.isBlank()) {
            List<Expression> items = OperatorsUtils.splitPostfixArithmeticExpression(postfix);
            Stack<Expression> stack = new Stack<>();

            for (var item : items) {
                if (item instanceof NonTerminalExpression) {
                    Expression val1 = stack.pop();
                    Expression val2 = stack.isEmpty() ? new ArithmeticExpression("0") : stack.pop();
                    ((NonTerminalExpression) item).setValues(val2, val1);
                    stack.push(new ArithmeticExpression((Double) item.interpret()));
                } else {
                    stack.push(item);
                }
            }
            output = stack.pop();

        }
        return output;
    }

    public Expression evaluatePostFixConditionalExpression(String postfix) {
        Expression output = null;
        if (postfix != null && !postfix.isBlank()) {
            List<Expression> items = OperatorsUtils.splitPostfixConditionalExpression(postfix);
            Stack<Expression> stack = new Stack<>();

            for (var item : items) {
                if (item instanceof NonTerminalExpression) {
                    Expression val1 = stack.pop();
                    Expression val2 = stack.isEmpty() ? new BooleanExpression(true) : stack.pop();
                    ((NonTerminalExpression) item).setValues(val2, val1);
                    stack.push(new BooleanExpression((Boolean) item.interpret()));
                } else {
                    stack.push(item);
                }
            }
            output = stack.pop();

        }
        return output;
    }

    public String evaluateInfixArithmeticExpression(String expression) {
        String postFixExpression = convertToPostFixExpression(expression);
        Expression output = evaluatePostFixArithmeticExpression(postFixExpression);

        return output.interpret().toString();
    }

    public String evaluateInfixConditionalExpression(String expression) {
        String postFixExpression = convertToPostFixExpression(expression);
        Expression output = evaluatePostFixConditionalExpression(postFixExpression);

        return output.interpret().toString();
    }

    public String formatCommand(String type, Property property, String format) {
        String output = "";

        return output;
    }


}
