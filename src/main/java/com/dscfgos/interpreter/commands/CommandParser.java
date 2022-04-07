package com.dscfgos.interpreter.commands;

import com.dscfgos.interpreter.utils.CommandsUtils;
import com.dscfgos.interpreter.utils.InterpreteProperty;

import java.util.List;
import java.util.Locale;

public class CommandParser {

    public String interprete(String context) {
        return this.interprete(context, null, new Locale("es"));
    }

    public String interprete(String context, List<InterpreteProperty> params, Locale locale) {
        return CommandsUtils.processCommandLines(context, params, locale);
    }
}
