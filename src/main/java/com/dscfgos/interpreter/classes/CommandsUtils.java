package com.dscfgos.interpreter.classes;

import com.dscfgos.interpreter.commands.FormatCommand;
import com.dscfgos.interpreter.commands.ToStringCommand;
import com.dscfgos.interpreter.commands.formatters.*;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommandsUtils {

    //private static final String formatRegEx = "#FORMAT\\s*\\((.*?)\\)";
    private static final String commandsRegEx = "#FORMAT\\s*\\((.*?)\\)|#IF\\s*\\((.*?)\\)|#STR\\s*\\((.*?)\\)";
    private static final String parametersRegEx = "\\s*\\((.*?)\\)";
    private static final String splitParamsRegEx = ",(?=(?:[^\\']*\\'[^\\']*\\')*[^\\']*$)";
    private static final String removeQuotesParamsRegEx = "'(.*?)'";

    private static final String CMD_FORMAT = "#FORMAT";
    private static final String CMD_STR = "#STR";


    public static PropertyType getPropertyType(String type) {
        PropertyType result = null;
        switch (type) {
            case "$S":
                result = PropertyType.STRING;
                break;
            case "$NI":
                result = PropertyType.NUMERIC;
                break;
            case "$NF":
                result = PropertyType.NUMERIC;
                break;
            case "$D":
                result = PropertyType.DATE;
                break;
            case "$DT":
                result = PropertyType.DATETIME;
                break;
            case "$T":
                result = PropertyType.TIME;
                break;

        }

        return result;
    }

    public static PropertyFormatter getFormatterByPropertyType(PropertyType type, String format, Object property, Locale locale) {
        PropertyFormatter result = null;
        switch (type) {
            case DATETIME:
                result = new PropertyDateTimeFormatter(format, property, locale);
                break;
            case DATE:
                result = new PropertyDateFormatter(format, property, locale);
                break;
            case TIME:
                result = new PropertyTimeFormatter(format, property, locale);
                break;
            case NUMERIC:
                result = new PropertyNumericFormatter(format, property, locale);
                break;
        }

        return result;
    }

    public static List<String> getParameters(String command) {
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

    public static List<String> getCommandsLines(String context) {
        List<String> result = new ArrayList<>();
        if (context != null) {
            Pattern regex = Pattern.compile(commandsRegEx);
            Matcher regexMatcher = regex.matcher(context);

            while (regexMatcher.find()) {
                String command = regexMatcher.group(0);
                result.add(command);
            }
        }

        return result.isEmpty() ? null : result;
    }

    public static String processCommandLines(String context, List<Property> params,Locale locale) {
        String result = "";
        if (context != null) {
            Pattern regex = Pattern.compile(commandsRegEx);
            Matcher regexMatcher = regex.matcher(context);

            result = regexMatcher.replaceAll(matchResult -> {
                var command = CommandsUtils.getCommand(regexMatcher.group(0),params, locale);
                return command.execute();
            });
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


    public static CommandBase getCommand(String command, List<Property> params, Locale locale) {
        CommandBase result = null;
        if (command != null) {
            if (command.startsWith(CMD_FORMAT)) {
                result = new FormatCommand(command, params, locale);
            }
            else if (command.startsWith(CMD_STR)) {
                result = new ToStringCommand(command, params);
            }
        }

        return result;
    }

}
