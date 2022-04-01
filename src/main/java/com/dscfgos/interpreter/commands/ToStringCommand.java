package com.dscfgos.interpreter.commands;

import com.dscfgos.interpreter.classes.CommandsUtils;
import com.dscfgos.interpreter.classes.Property;
import com.dscfgos.interpreter.commands.formatters.PropertyFormatter;
import com.dscfgos.interpreter.commands.formatters.PropertyType;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Locale;

public class ToStringCommand implements CommandBase {
    private Object parameter;

    public ToStringCommand(String command, List<Property> params) {
        List<String> lstParameters = CommandsUtils.getParameters(command);
        if (lstParameters != null && lstParameters.size() == 1) {
            Object value = CommandsUtils.getPropertyValue(lstParameters.get(0), params);
            if (value != null) {
                this.parameter = value;
            } else {
                throw new InvalidParameterException("Property not found.");
            }
        } else {
            throw new InvalidParameterException("Invalid parameters, 1 expected for ToString Command");
        }
    }

    @Override
    public String execute() {
        return this.parameter.toString();
    }
}
