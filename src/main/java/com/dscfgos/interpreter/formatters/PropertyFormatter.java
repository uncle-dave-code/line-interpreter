package com.dscfgos.interpreter.formatters;

import com.dscfgos.interpreter.classes.Property;
import lombok.Data;

import java.util.Locale;

@Data
public abstract class PropertyFormatter {

    private final String format;
    private final Property property;
    private final Locale locale;

    public abstract String format();

    public abstract FormatterType getType();

    public PropertyFormatter(String format, Property property, Locale locale){
        this.format = format;
        this.property = property;
        this.locale = locale;
    }
}
