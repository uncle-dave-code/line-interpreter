package com.dscfgos.interpreter.flow;

import com.dscfgos.interpreter.expression.ExpressionParser;
import com.dscfgos.interpreter.flow.interfaces.ControlFlowBase;
import com.dscfgos.interpreter.utils.InterpreteProperty;
import com.dscfgos.interpreter.utils.RegExPatterns;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

public class IfControlFlow implements ControlFlowBase {
    private String expression;
    private String result;
    private ExpressionParser expressionParser = new ExpressionParser();

    public IfControlFlow(String command, List<InterpreteProperty> params, Locale locale) {
        List<String> lstParameters = this.getIfParameters(command);
        if (lstParameters != null && lstParameters.size() == 2) {
            this.expression = expressionParser.interprete(lstParameters.get(0), params);
            if (this.expression != null && (this.expression.equals("true") || this.expression.equals("false"))) {
                this.result = lstParameters.get(1);
            } else {
                throw new InvalidParameterException("Invalid boolean expression");
            }
        } else {
            throw new InvalidParameterException("Invalid If Statement");
        }
    }

    private List<String> getIfParameters(String command) {
        List<String> result = null;
        if (command != null) {
            Matcher regexMatcher = RegExPatterns.regexIfPattern.matcher(command);
            if (regexMatcher.find()) {
                result = new ArrayList<>();
                result.add(regexMatcher.group(1));
                result.add(regexMatcher.group(3));
            }
        }
        return result;
    }

    @Override
    public String evaluate() {
        String result = "";
        if (expression != null) {
            if (expression.equals("true")) {
                result = this.result;
            }
        }
        return result;
    }
}
