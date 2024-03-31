package com.rowel.bookstore.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@ReadingConverter //is used to mark a class as a converter that's specifically for converting data when it is read from the database.
public class StringToDateConverter implements Converter<String, Date> {

    private static final Logger logger = Logger.getLogger(StringToDateConverter.class.getName());
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    private final Date defaultDate;

    public StringToDateConverter() {
        this.dateFormat.setLenient(false); // Strict parsing
        this.defaultDate = createDefaultDate();
    }

    private Date createDefaultDate() {
        try {
            return dateFormat.parse("1900-01-01T00:00:00.000Z");
        } catch (ParseException e) {
            throw new IllegalStateException("Failed to parse default date", e);
        }
    }

    @Override
    public Date convert(String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            logger.warning("Invalid date format: " + source + ". Using default date instead.");
            return defaultDate;
        }
    }
}
