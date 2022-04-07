package com.dscfgos.interpreter.commands;

import com.dscfgos.interpreter.commands.formatters.PropertyFormatter;
import com.dscfgos.interpreter.commands.formatters.PropertyType;
import com.dscfgos.interpreter.commands.interfaces.CommandBase;
import com.dscfgos.interpreter.utils.CommandsUtils;
import com.dscfgos.interpreter.utils.InterpreteProperty;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Locale;

public class FormatCommand implements CommandBase {
    private final Locale locale;
    private PropertyType propertyType;
    private Object parameter;
    private String format;
    private PropertyFormatter formater;

    public FormatCommand(String command, List<InterpreteProperty> params, Locale locale) {
        List<String> lstParameters = CommandsUtils.getParameters(command);
        if (lstParameters != null && lstParameters.size() == 3) {
            PropertyType dataType = CommandsUtils.getPropertyType(lstParameters.get(0));
            if (dataType != null) {
                Object value = CommandsUtils.getPropertyValue(lstParameters.get(1), params);
                if (value != null) {
                    this.propertyType = dataType;
                    this.parameter = value;
                    this.format = lstParameters.get(2);
                    this.locale = locale;
                    this.formater = CommandsUtils.getFormatterByPropertyType(this.propertyType, this.format, this.parameter, this.locale);
                } else {
                    throw new InvalidParameterException("Property not found.");
                }
            } else {
                throw new InvalidParameterException("Invalid data type.");
            }
        } else {
            throw new InvalidParameterException("Invalid parameters, 3 were expected for Format Command");
        }
    }

    @Override
    public String execute() {
        return formater.format();
    }
}
