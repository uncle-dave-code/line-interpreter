package com.dscfgos.interpreter.formatters;

import com.dscfgos.interpreter.classes.Property;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyTimeFormatter extends PropertyFormatter{

    public PropertyTimeFormatter(String format, Property property, Locale locale) {
        super(format, property, locale);
    }

    @Override
    public String format() {
        var formatter = DateTimeFormatter.ofPattern(this.getFormat());

        var localTime = (LocalTime) this.getProperty().getValue();

        return formatter.format(localTime);
    }

    @Override
    public FormatterType getType() {
        return FormatterType.TIME;
    }
}
