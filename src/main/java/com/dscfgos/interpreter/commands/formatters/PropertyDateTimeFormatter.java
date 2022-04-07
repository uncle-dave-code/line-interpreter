package com.dscfgos.interpreter.commands.formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyDateTimeFormatter extends PropertyFormatter {

    public PropertyDateTimeFormatter(String format, Object propertyValue, Locale locale) {
        super(format, propertyValue, locale);
    }

    @Override
    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.getFormat());

        LocalDateTime localDateTime = (LocalDateTime) this.getPropertyValue();

        return formatter.format(localDateTime);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.DATETIME;
    }
}
