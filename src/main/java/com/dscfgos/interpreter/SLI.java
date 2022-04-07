package com.dscfgos.interpreter;

import com.dscfgos.interpreter.commands.CommandParser;
import com.dscfgos.interpreter.flow.ControlFlowParser;
import com.dscfgos.interpreter.utils.InterpreteProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class SLI {

    public static String interprete(String context, List<InterpreteProperty> params, Locale locale) {
        return processSLI(context, params, locale);
    }

    public static String interprete(String context, Function<List<Object>, List<InterpreteProperty>> convertFuncion, List<Object> params, Locale locale) {
        return processSLI(context, convertFuncion.apply(params), locale);
    }

    public static String interprete(String context, Function<Object[], List<InterpreteProperty>> convertFuncion, Object[] params, Locale locale) {
        return processSLI(context, convertFuncion.apply(params), locale);
    }

    public static String interprete(String context, Map<String, Object> params, Locale locale) {
        List<InterpreteProperty> properties = new ArrayList<>();
        for (Entry<String, Object> entry : params.entrySet()) {
            properties.add(new InterpreteProperty(entry.getKey(), entry.getValue()));
        }
        return processSLI(context, properties, locale);
    }

    private static String processSLI(String context, List<InterpreteProperty> params, Locale locale) {

        String result = "";
        if (context != null) {
            var commandParser = new CommandParser();
            var controlFlowParser = new ControlFlowParser();

            var commandResult = commandParser.interprete(context, params, locale);
            result = controlFlowParser.interprete(commandResult, params, locale);
        }

        return result;
    }
}
