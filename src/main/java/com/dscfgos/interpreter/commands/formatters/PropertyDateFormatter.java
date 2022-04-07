package com.dscfgos.interpreter.commands.formatters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyDateFormatter extends PropertyFormatter {

    public PropertyDateFormatter(String format, Object propertyValue, Locale locale) {
        super(format, propertyValue, locale);
    }

    @Override
    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.getFormat());

        LocalDate localDate = (LocalDate) getPropertyValue();

        return formatter.format(localDate);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.DATE;
    }
}
