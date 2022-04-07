package com.dscfgos.interpreter.utils;

import java.util.regex.Pattern;

public class RegExPatterns {

    private static final String commandsRegEx = "#FORMAT\\s*\\((.*?)\\)|#STR\\s*\\((.*?)\\)";
    private static final String parametersRegEx = "\\s*\\((.*?)\\)";
    public static final String splitParamsRegEx = ",(?=(?:[^\\']*\\'[^\\']*\\')*[^\\']*$)";
    private static final String removeQuotesParamsRegEx = "'(.*?)'";

    private static final String IFELSERegEx = "#IFELSE\\s*\\((.*?)\\)\\s*(\\{(.*?)\\})\\s*:\\s*(\\{(.*?)\\})";
    private static final String IFRegEx = "#IF\\s*\\((.*?)\\)\\s*(\\{(.*?)\\})";
    private static final String ifOrElseRegEx = IFELSERegEx + "|" + IFRegEx;

    private static final String operators = " |\\+|\\*|-|/|\\(|\\)|\\^|&&|\\|\\||<=|>=|<(?!=)|>(?!=)|==|\\!=";
    public static final String operatorsRegEx = "((?=" + operators + ")|(?<=" + operators + "))";

    public static Pattern regexIfPattern = Pattern.compile(IFRegEx);
    public static Pattern regexIfElsePattern = Pattern.compile(IFELSERegEx);
    public static Pattern regexIfOrElsePattern = Pattern.compile(ifOrElseRegEx);

    public static Pattern regexCommandsPattern = Pattern.compile(commandsRegEx);
    public static Pattern regexParametersPattern = Pattern.compile(parametersRegEx);
    public static Pattern regexSplitParametersPattern = Pattern.compile(splitParamsRegEx);
    public static Pattern regexRemoveQuotesParamsPattern = Pattern.compile(removeQuotesParamsRegEx);
}
