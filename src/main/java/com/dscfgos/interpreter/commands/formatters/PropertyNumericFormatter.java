package com.dscfgos.interpreter.commands.formatters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PropertyNumericFormatter extends PropertyFormatter{

    public PropertyNumericFormatter(String format, Object propertyValue, Locale locale) {
        super(format, propertyValue, locale);
    }

    @Override
    public String format() {
        var formatter = DecimalFormat.getInstance(getLocale());

        if(this.getFormat() != null && !this.getFormat().isBlank()){
            formatter = new DecimalFormat(this.getFormat(), new DecimalFormatSymbols(getLocale()));
        }

        return formatter.format(this.getPropertyValue());
    }

    @Override
    public PropertyType getType() {
        return PropertyType.NUMERIC;
    }
}
