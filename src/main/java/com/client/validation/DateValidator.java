package com.client.validation;

import java.util.regex.Pattern;

/**
 * A class that contains methods for validating dates and date components.
 */
public class DateValidator {

    /**
     * Checks if the given month is valid.
     *
     * @param month The month to be validated.
     * @return true if the month is valid, false otherwise.
     */
    public static boolean isMonthValid(String month){
        String monthPattern = "^(0?[1-9]|1[0-2])$";
        Pattern compiledMonthPattern = Pattern.compile(monthPattern);
        return compiledMonthPattern.matcher(month).matches();
    }

    /**
     * Checks if the given year is valid.
     *
     * @param year The year to be validated.
     * @return true if the year is valid, false otherwise.
     */
    public static boolean isYearValid(String year){
        String yearPattern = "^((20)\\d{2}|\\d{2})$";
        Pattern compiledYearPattern = Pattern.compile(yearPattern);
        return compiledYearPattern.matcher(year).matches();
    }

    /**
     * Checks if the given date is valid.
     *
     * @param date The date to be validated.
     * @return true if the date is valid, false otherwise.
     */
    public static boolean isDateValid(String date){
        String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern compiledDatePattern = Pattern.compile(datePattern);
        return compiledDatePattern.matcher(date).matches();
    }
}
