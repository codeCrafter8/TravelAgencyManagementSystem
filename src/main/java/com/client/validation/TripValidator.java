package com.client.validation;

import java.util.regex.Pattern;

public class TripValidator {
    /**
     * Sprawdza, czy podane miasto lub kraj jest poprawne.
     *
     * @param text miasto lub kraj do sprawdzenia
     * @return true, jeśli miasto lub kraj jest poprawne, false w przeciwnym razie
     */
    public static boolean isCountryOrCityValid(String text){
        String countryOrCityPattern = "^[A-Za-z\\s]+$";
        Pattern compiledCountryOrCityPattern = Pattern.compile(countryOrCityPattern);
        return compiledCountryOrCityPattern.matcher(text).matches();
    }
    /**
     * Sprawdza, czy podana nazwa hotelu jest poprawna.
     *
     * @param hotelName nazwa hotelu do sprawdzenia
     * @return true, jeśli nazwa hotelu jest poprawna, false w przeciwnym razie
     */
    public static boolean isHotelNameValid(String hotelName){
        String hotelNamePattern = "^[a-zA-Z0-9\\s]{1,50}$";
        Pattern compiledHotelNamePattern = Pattern.compile(hotelNamePattern);
        return compiledHotelNamePattern.matcher(hotelName).matches();
    }
    /**
     * Sprawdza, czy podany limit osób jest poprawny.
     *
     * @param peopleLimit limit osób do sprawdzenia
     * @return true, jeśli limit osób jest poprawny, false w przeciwnym razie
     */
    public static boolean isPeopleLimitValid(String peopleLimit){
        String peopleLimitPattern = "^[1-6]$";
        Pattern compiledPeopleLimitPattern = Pattern.compile(peopleLimitPattern);
        return compiledPeopleLimitPattern.matcher(peopleLimit).matches();
    }
    /**
     * Sprawdza, czy podana cena jest poprawna.
     *
     * @param price cena do sprawdzenia
     * @return true, jeśli cena jest poprawna, false w przeciwnym razie
     */
    public static boolean isPriceValid(String price){
        String pricePattern = "^[1-9]\\d*(\\.\\d{1,2})?$";
        Pattern compiledPricePattern = Pattern.compile(pricePattern);
        return compiledPricePattern.matcher(price).matches();
    }
}
