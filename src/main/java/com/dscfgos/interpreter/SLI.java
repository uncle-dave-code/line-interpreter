package com.dscfgos.interpreter;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.Property;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class SLI {

    public static String interprete(String context, List<Property> params, Locale locale) {
        return processSLI(context, params, locale);
    }

    public static String interprete(String context, Function<List<Object>, List<Property>> convertFuncion, List<Object> params, Locale locale) {
        return processSLI(context, convertFuncion.apply(params), locale);
    }

    public static String interprete(String context, Function<Object[], List<Property>> convertFuncion, Object[] params, Locale locale) {
        return processSLI(context, convertFuncion.apply(params), locale);
    }

    private static String processSLI(String context, List<Property> params, Locale locale) {

        String result = "";
        if (context != null) {
            result = CommandsUtils.processCommandLines(context, params, locale);
        }

        return result;
    }
}
