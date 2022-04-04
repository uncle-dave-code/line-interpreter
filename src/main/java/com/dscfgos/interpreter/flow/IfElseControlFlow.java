package com.dscfgos.interpreter.flow;

import com.dscfgos.interpreter.SLI;
import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.ControlFlowUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.formatters.PropertyFormatter;
import com.dscfgos.interpreter.commands.formatters.PropertyType;
import com.dscfgos.interpreter.expression.ExpressionParser;
import com.dscfgos.interpreter.flow.interfaces.ControlFlowBase;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfElseControlFlow implements ControlFlowBase {
    private String expression;
    private String resultTrue;
    private String resultFalse;
    private ExpressionParser expressionParser = new ExpressionParser();

    public IfElseControlFlow(String command, List<Property> params, Locale locale) {
        List<String> lstParameters = this.getIfElseParameters(command);
        if (lstParameters != null && lstParameters.size() == 3) {
            this.expression = expressionParser.interprete(lstParameters.get(0), params);
            if (this.expression != null && (this.expression.equals("true") || this.expression.equals("false"))) {
                this.resultTrue = lstParameters.get(1);
                this.resultFalse = lstParameters.get(2);
            } else {
                throw new InvalidParameterException("Invalid boolean expression");
            }
        } else {
            throw new InvalidParameterException("Invalid If Else Statement");
        }
    }

    private List<String> getIfElseParameters(String command) {
        List<String> result = null;
        if (command != null) {
            Pattern regex = Pattern.compile(ControlFlowUtils.IFELSERegEx);
            Matcher regexMatcher = regex.matcher(command);
            if (regexMatcher.find()) {
                result = new ArrayList<>();
                result.add(regexMatcher.group(1));
                result.add(regexMatcher.group(3));
                result.add(regexMatcher.group(5));
            }
        }
        return result;
    }

    @Override
    public String evaluate() {
        String result = "";
        if (expression != null) {
            if (expression.equals("true")) {
                result = this.resultTrue;
            } else if (expression.equals("false")) {
                result = this.resultFalse;
            }
        }
        return result;
    }
}
