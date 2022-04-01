package com.dscfgos.interpreter.commands.formatters;

import com.dscfgos.interpreter.classes.Property;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyDateTimeFormatter extends PropertyFormatter{

    public PropertyDateTimeFormatter(String format, Object propertyValue, Locale locale) {
        super(format, propertyValue, locale);
    }

    @Override
    public String format() {
        var formatter = DateTimeFormatter.ofPattern(this.getFormat());

        var localDateTime = (LocalDateTime) this.getPropertyValue();

        return formatter.format(localDateTime);
    }

    @Override
    public PropertyType getType() {
        return PropertyType.DATETIME;
    }
}
