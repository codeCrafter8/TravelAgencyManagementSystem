package com.client.validation;

import java.util.regex.Pattern;

public class DateValidator {
    /**
     * Sprawdza, czy podany miesiąc jest poprawny.
     *
     * @param month miesiąc do sprawdzenia
     * @return true, jeśli miesiąc jest poprawny, false w przeciwnym razie
     */
    public static boolean isMonthValid(String month){
        String monthPattern = "^(0?[1-9]|1[0-2])$";
        Pattern compiledMonthPattern = Pattern.compile(monthPattern);
        return compiledMonthPattern.matcher(month).matches();
    }
    /**
     * Sprawdza, czy podany rok jest poprawny.
     *
     * @param year rok do sprawdzenia
     * @return true, jeśli rok jest poprawny, false w przeciwnym razie
     */
    public static boolean isYearValid(String year){
        String yearPattern = "^((20)\\d{2}|\\d{2})$";
        Pattern compiledYearPattern = Pattern.compile(yearPattern);
        return compiledYearPattern.matcher(year).matches();
    }
    /**
     * Sprawdza, czy podana data jest poprawna.
     *
     * @param date data do sprawdzenia
     * @return true, jeśli data jest poprawna, false w przeciwnym razie
     */
    public static boolean isDateValid(String date){
        String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern compiledDatePattern = Pattern.compile(datePattern);
        return compiledDatePattern.matcher(date).matches();
    }
}
