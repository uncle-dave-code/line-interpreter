package com.dscfgos.interpreter.commands.formatters;

import com.dscfgos.interpreter.classes.Property;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyDateFormatter extends PropertyFormatter{

    public PropertyDateFormatter(String format, Object propertyValue, Locale locale) {
        super(format, propertyValue, locale);
    }

    @Override
    public String format() {
        var formatter = DateTimeFormatter.ofPattern(this.getFormat());

        var localDate = (LocalDate) getPropertyValue();

        return formatter.format(localDate);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.DATE;
    }
}
