package com.dscfgos.interpreter.classes;

import com.dscfgos.interpreter.flow.IfControlFlow;
import com.dscfgos.interpreter.flow.IfElseControlFlow;
import com.dscfgos.interpreter.flow.interfaces.ControlFlowBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ControlFlowUtils {

    //private static final String formatRegEx = "#FORMAT\\s*\\((.*?)\\)";
    private static final String commandsRegEx = "#FORMAT\\s*\\((.*?)\\)|#IF\\s*\\((.*?)\\)|#STR\\s*\\((.*?)\\)";
    private static final String parametersRegEx = "\\s*\\((.*?)\\)";
    private static final String splitParamsRegEx = ",(?=(?:[^\\']*\\'[^\\']*\\')*[^\\']*$)";
    private static final String removeQuotesParamsRegEx = "'(.*?)'";

    public static final String IFELSERegEx = "#IFELSE\\s*\\((.*?)\\)\\s*(\\{(.*?)\\})\\s*:\\s*(\\{(.*?)\\})";
    public static final String IFRegEx = "#IF\\s*\\((.*?)\\)\\s*(\\{(.*?)\\})";
    public static final String ifElseRegEx = IFELSERegEx+ "|" + IFRegEx ;


    private static final String CMD_IF = "#IF";
    private static final String CMD_IFELSE = "#IFELSE";

    public static List<String> getIfElseParameters(String command) {
        List<String> result = null;
        if (command != null) {
            if (command.startsWith(CMD_IFELSE)) {
                Pattern regex = Pattern.compile(IFELSERegEx);
                Matcher regexMatcher = regex.matcher(command);
                if(regexMatcher.find()){
                    result.add(regexMatcher.group(1));
                    result.add(regexMatcher.group(3));
                    result.add(regexMatcher.group(5));
                }
            }
            else if (command.startsWith(CMD_IF)) {
                Pattern regex = Pattern.compile(IFRegEx);
                Matcher regexMatcher = regex.matcher(command);
                if(regexMatcher.find()){
                    result.add(regexMatcher.group(1));
                    result.add(regexMatcher.group(3));
                }
            }
        }
        return result;
    }

    public static List<String> getSplitIfElse(String command) {
        List<String> result = null;
        if (command != null) {
            Pattern regex = Pattern.compile(parametersRegEx);
            Matcher regexMatcher = regex.matcher(command);

            while (regexMatcher.find()) {
                String parameters = regexMatcher.group(1);
                if ((parameters != null) && !parameters.isBlank()){
                    Pattern rg = Pattern.compile(removeQuotesParamsRegEx);
                    result = Arrays.stream(parameters.split(splitParamsRegEx))
                            .map(token -> {
                                Matcher regexM = rg.matcher(token.trim());
                                return (regexM.find()) ? regexM.group(1) : token.trim();
                            })
                            .collect(Collectors.toList());
                }
            }
        }
        return result;
    }

    public static Object getPropertyValue(String propertyName, List<Property> params) {
        Object result = null;
        if (params != null && !params.isEmpty()) {
            for (var item : params) {
                if (item.getCode().equals(propertyName)) {
                    result = item.getValue();
                    break;
                }
            }
        }

        return result;
    }

    public static List<String> getControlFlowLines(String context) {
        List<String> result = new ArrayList<>();
        if (context != null) {
            Pattern regex = Pattern.compile(ifElseRegEx);
            Matcher regexMatcher = regex.matcher(context);

            while (regexMatcher.find()) {
                String command = regexMatcher.group(0);
                result.add(command);
            }
        }
        return result.isEmpty() ? null : result;
    }

    public static String processControlFlowLines(String context, List<Property> params,Locale locale) {
        String result = context;
        if (context != null) {
            Pattern regex = Pattern.compile(ifElseRegEx);
            Matcher regexMatcher = regex.matcher(context);
            if(regexMatcher.find()){
                result = regexMatcher.replaceAll(matchResult -> {
                    var command = ControlFlowUtils.getControlFlow(regexMatcher.group(0),params, locale);
                    return command.evaluate();
                });
            }
        }

        return result;
    }

    public static GroupPosition getCommandLine(String context) {
        GroupPosition result = null;
        if (context != null) {
            Pattern regex = Pattern.compile(commandsRegEx);
            Matcher regexMatcher = regex.matcher(context);

            if (regexMatcher.find()) {
                result = new GroupPosition(regexMatcher.start(),regexMatcher.end(), regexMatcher.group(0));
             }
        }

        return result;
    }


    public static ControlFlowBase getControlFlow(String command, List<Property> params, Locale locale) {
        ControlFlowBase result = null;
        if (command != null) {
            if (command.startsWith(CMD_IFELSE)) {
                result = new IfElseControlFlow(command, params, locale);
            }
            else if (command.startsWith(CMD_IF)) {
                result = new IfControlFlow(command, params, locale);
            }
        }
        return result;
    }

}
