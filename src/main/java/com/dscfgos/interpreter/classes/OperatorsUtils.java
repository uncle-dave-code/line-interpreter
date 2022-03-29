package com.dscfgos.interpreter.classes;

import com.dscfgos.interpreter.expression.ArithmeticExpression;
import com.dscfgos.interpreter.expression.BooleanExpression;
import com.dscfgos.interpreter.expression.conditional.AndExpression;
import com.dscfgos.interpreter.expression.conditional.OrExpression;
import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.arithmetic.*;
import com.dscfgos.interpreter.expression.interfaces.NonTerminalExpression;
import com.dscfgos.interpreter.expression.interfaces.TerminalExpression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OperatorsUtils {
    private static final String operatorsRegEx = "((?=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|)|(?<=\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\|))";

    public static List<String> splitDirective(String context) {
        List<String> result = (context != null && !context.isBlank()) ? Arrays.stream(context.split(operatorsRegEx))
                .filter(token -> token.trim().length() > 0)
                .map(token -> token.trim())
                .collect(Collectors.toList()) : null;
        return result;
    }

    public static List<Expression> splitPostfixArithmeticExpression(String postfix) {
        List<Expression> result = (postfix != null && !postfix.isBlank()) ?
                Arrays.stream(postfix.split(operatorsRegEx))
                        .filter(token -> token.trim().length() > 0)
                        .map(token -> {
                            if (isOperator(token)) {
                                return getExpression(token);
                            } else {
                                TerminalExpression expression = new ArithmeticExpression(token);
                                return expression;
                            }
                        })
                        .collect(Collectors.toList()) : null;
        return result;
    }

    public static List<Expression> splitPostfixConditionalExpression(String postfix) {
        List<Expression> result = (postfix != null && !postfix.isBlank()) ?
                Arrays.stream(postfix.split(operatorsRegEx))
                        .filter(token -> token.trim().length() > 0)
                        .map(token -> {
                            if (isOperator(token)) {
                                return getExpression(token);
                            } else {
                                TerminalExpression expression = new BooleanExpression(token);
                                return expression;
                            }
                        })
                        .collect(Collectors.toList()) : null;
        return result;
    }

    public static boolean isOperator(String s) {
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^") || s.equals("MOD")
                || s.equals("&&") || s.equals("||"));
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
            case "MOD":
                result = new ModuleExpression();
                break;
            case "&&":
                result = new AndExpression();
                break;
            case "||":
                result = new OrExpression();
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
            case "&&":
                return 11;
            case "||":
                return 12;
        }
        return -1;
    }
}
