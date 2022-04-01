package com.dscfgos.interpreter.commands.formatters;

import com.dscfgos.interpreter.classes.Property;
import lombok.Data;

import java.util.Locale;

@Data
public abstract class PropertyFormatter {

    private final String format;
    private final Object propertyValue;
    private final Locale locale;

    public abstract String format();

    public abstract PropertyType getType();

    public PropertyFormatter(String format, Object propertyValue, Locale locale){
        this.format = format;
        this.propertyValue = propertyValue;
        this.locale = locale;
    }
}
