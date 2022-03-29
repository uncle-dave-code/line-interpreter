package com.dscfgos.interpreter.formatters;

import com.dscfgos.interpreter.classes.Property;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PropertyDateFormatter extends PropertyFormatter{

    public PropertyDateFormatter(String format, Property property, Locale locale) {
        super(format, property, locale);
    }

    @Override
    public String format() {
        var formatter = DateTimeFormatter.ofPattern(this.getFormat());

        var localDate = (LocalDate) this.getProperty().getValue();

        return formatter.format(localDate);
    }

    @Override
    public FormatterType getType() {
        return FormatterType.DATE;
    }
}
