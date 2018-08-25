package com.juanromodev.popularmovies.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieUtils {

    public static String formatDate(String movieDate, String desiredFormat) {
        Locale locale = Locale.US;

        DateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd", locale);

        try {
            Date date = fromFormat.parse(movieDate);

            DateFormat toFormat = new SimpleDateFormat(desiredFormat, locale);

            return toFormat.format(date);

        } catch (ParseException e) {
            return movieDate;
        }
    }
}
