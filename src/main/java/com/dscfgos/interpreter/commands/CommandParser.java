package com.dscfgos.interpreter.commands;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;

import java.util.List;
import java.util.Locale;

public class CommandParser {

    public String interprete(String context) {
        return this.interprete(context, null, new Locale("es"));
    }

    public String interprete(String context, List<Property> params, Locale locale) {
        return CommandsUtils.processCommandLines(context, params, locale);
    }
}
