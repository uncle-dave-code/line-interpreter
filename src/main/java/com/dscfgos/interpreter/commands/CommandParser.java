package com.dscfgos.interpreter.commands;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.ExpressionType;
import com.dscfgos.interpreter.classes.OperatorsUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;
import com.dscfgos.interpreter.expression.interfaces.Expression;
import com.dscfgos.interpreter.expression.interfaces.NonTerminalExpression;

import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class CommandParser {

    public String interprete(String context) {
        return this.interprete(context, null);
    }

    public String interprete(String context, List<Property> params) {
        return context;
    }

    public CommandBase getCommand(String commandLine,  List<Property> params, Locale locale){
        CommandBase result = null;
        if(commandLine != null){
            result = CommandsUtils.getCommand(commandLine, params, locale);
        }
        return result;
    }
}
