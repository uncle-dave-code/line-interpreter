package com.dscfgos.interpreter.classes;

import com.dscfgos.interpreter.expression.GenericExpression;
import com.dscfgos.interpreter.expression.logical.AndExpression;
import com.dscfgos.interpreter.expression.logical.OrExpression;
import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.arithmetic.*;
import com.dscfgos.interpreter.expression.interfaces.TerminalExpression;
import com.dscfgos.interpreter.expression.relational.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperatorsUtils {

    private static final String operators = " |\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\||<=|>=|<(?!=)|>(?!=)|==|\\!=";
    private static final String operatorsRegEx = "((?=" + operators + ")|(?<=" + operators + "))";

    public static List<String> splitDirective(String context) {
        var result = (context != null && !context.isBlank()) ? Arrays.stream(context.split(operatorsRegEx))
                .filter(token -> token.trim().length() > 0)
                .map(token -> token.trim())
                .collect(Collectors.toList()) : null;
        return result;
    }

    public static List<Expression> splitPostfixExpression(String postfix) {
        var result = (postfix != null && !postfix.isBlank()) ?
                Arrays.stream(postfix.split(operatorsRegEx))
                        .filter(token -> token.trim().length() > 0)
                        .map(token -> {
                            if (isOperator(token)) {
                                return getExpression(token);
                            } else {
                                return new GenericExpression(token);
                            }
                        })
                        .collect(Collectors.toList()) : null;
        return result;
    }

    public static boolean isOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^") || s.equals("MOD")
                || s.equals("&&") || s.equals("||")
                || s.equals("==") || s.equals("!=") || s.equals(">") || s.equals(">=") || s.equals("<") || s.equals("<="));
    }

    public static Expression getExpression(String operator) {
        Expression result = null;
        switch (operator) {
            case "+":
                result = new AdditionExpression();
                break;
            case "-":
                result = new SubtractionExpression();
                break;
            case "/":
                result = new DivisionExpression();
                break;
            case "*":
                result = new MultiplicationExpression();
                break;
            case "^":
                result = new PowExpression();
                break;
            case "&&":
                result = new AndExpression();
                break;
            case "||":
                result = new OrExpression();
                break;
            case "==":
                result = new EqualExpression();
                break;
            case "!=":
                result = new NotEqualExpression();
                break;
            case ">":
                result = new GreaterThanExpression();
                break;
            case ">=":
                result = new GreaterThanEqualExpression();
                break;
            case "<":
                result = new LessThanExpression();
                break;
            case "<=":
                result = new LessThanEqualExpression();
                break;
        }

        return result;
    }

    public static int Precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "MOD":
                return 2;
            case "^":
                return 3;
            case "==":
            case "!=":
                return 10;
            case "<":
            case "<=":
            case ">":
            case ">=":
                return 11;
            case "&&":
                return 20;
            case "||":
                return 21;
        }
        return -1;
    }
}
