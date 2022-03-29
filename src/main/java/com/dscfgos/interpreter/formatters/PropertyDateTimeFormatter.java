package com.dscfgos.interpreter.formatters;

import com.dscfgos.interpreter.classes.Property;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyDateTimeFormatter extends PropertyFormatter{

    public PropertyDateTimeFormatter(String format, Property property, Locale locale) {
        super(format, property, locale);
    }

    @Override
    public String format() {
        var formatter = DateTimeFormatter.ofPattern(this.getFormat());

        var localDateTime = (LocalDateTime) this.getProperty().getValue();

        return formatter.format(localDateTime);
    }

    @Override
    public FormatterType getType() {
        return FormatterType.DATETIME;
    }
}
