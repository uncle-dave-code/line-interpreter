package com.dscfgos.interpreter.commands.formatters;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyTimeFormatter extends PropertyFormatter {

    public PropertyTimeFormatter(String format, Object propertyValue, Locale locale) {
        super(format, propertyValue, locale);
    }

    @Override
    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.getFormat());

        LocalTime localTime = (LocalTime) getPropertyValue();

        return formatter.format(localTime);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.TIME;
    }
}
