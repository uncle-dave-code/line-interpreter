package com.dscfgos.interpreter.utils;

import com.dscfgos.interpreter.flow.IfControlFlow;
import com.dscfgos.interpreter.flow.IfElseControlFlow;
import com.dscfgos.interpreter.flow.interfaces.ControlFlowBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ControlFlowUtils {

    private static final String CMD_IF = "#IF";
    private static final String CMD_IFELSE = "#IFELSE";

    public static List<String> getIfElseParameters(String command) {
        List<String> result = null;
        if (command != null) {
            if (command.startsWith(CMD_IFELSE)) {
                Matcher regexMatcher = RegExPatterns.regexIfElsePattern.matcher(command);
                if (regexMatcher.find()) {
                    result = new ArrayList<>();
                    result.add(regexMatcher.group(1));
                    result.add(regexMatcher.group(3));
                    result.add(regexMatcher.group(5));
                }
            } else if (command.startsWith(CMD_IF)) {
                Matcher regexMatcher = RegExPatterns.regexIfPattern.matcher(command);
                if (regexMatcher.find()) {
                    result = new ArrayList<>();
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
            Matcher regexMatcher = RegExPatterns.regexParametersPattern.matcher(command);

            while (regexMatcher.find()) {
                String parameters = regexMatcher.group(1);
                if ((parameters != null) && !parameters.isBlank()) {
                    result = Arrays.stream(parameters.split(RegExPatterns.splitParamsRegEx)).map(token -> {
                        Matcher regexM = RegExPatterns.regexRemoveQuotesParamsPattern.matcher(token.trim());
                        return (regexM.find()) ? regexM.group(1) : token.trim();
                    }).collect(Collectors.toList());
                }
            }
        }
        return result;
    }

    public static Object getPropertyValue(String propertyName, List<InterpreteProperty> params) {
        Object result = null;
        if (params != null && !params.isEmpty()) {
            for (InterpreteProperty item : params) {
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
            Matcher regexMatcher = RegExPatterns.regexIfOrElsePattern.matcher(context);

            while (regexMatcher.find()) {
                String command = regexMatcher.group(0);
                result.add(command);
            }
        }
        return result.isEmpty() ? null : result;
    }

    public static String processControlFlowLines(String context, List<InterpreteProperty> params, Locale locale) {
        String result = context;
        if (context != null) {
            Matcher regexMatcher = RegExPatterns.regexIfOrElsePattern.matcher(context);
            if (regexMatcher.find()) {
                result = regexMatcher.replaceAll(new Function<MatchResult, String>() {

                    @Override
                    public String apply(MatchResult t) {
                        ControlFlowBase command = ControlFlowUtils.getControlFlow(regexMatcher.group(0), params, locale);
                        return command.evaluate();
                    }
                });
            }
        }

        return result;
    }

    public static GroupPosition getCommandLine(String context) {
        GroupPosition result = null;
        if (context != null) {
            Matcher regexMatcher = RegExPatterns.regexCommandsPattern.matcher(context);

            if (regexMatcher.find()) {
                result = new GroupPosition(regexMatcher.start(), regexMatcher.end(), regexMatcher.group(0));
            }
        }

        return result;
    }

    public static ControlFlowBase getControlFlow(String command, List<InterpreteProperty> params, Locale locale) {
        ControlFlowBase result = null;
        if (command != null) {
            if (command.startsWith(CMD_IFELSE)) {
                result = new IfElseControlFlow(command, params, locale);
            } else if (command.startsWith(CMD_IF)) {
                result = new IfControlFlow(command, params, locale);
            }
        }
        return result;
    }

}
