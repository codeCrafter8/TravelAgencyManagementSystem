package com.client.validation;

import java.util.regex.Pattern;

/**
 * A class that contains methods for validating trip-related information.
 */
public class TripValidator {

    /**
     * Checks if the given country or city name is valid.
     *
     * @param text The country or city name to be validated.
     * @return true if the country or city name is valid, false otherwise.
     */
    public static boolean isCountryOrCityValid(String text){
        String countryOrCityPattern = "^[A-Za-z\\s]+$";
        Pattern compiledCountryOrCityPattern = Pattern.compile(countryOrCityPattern);
        return compiledCountryOrCityPattern.matcher(text).matches();
    }

    /**
     * Checks if the given hotel name is valid.
     *
     * @param hotelName The hotel name to be validated.
     * @return true if the hotel name is valid, false otherwise.
     */
    public static boolean isHotelNameValid(String hotelName){
        String hotelNamePattern = "^[a-zA-Z0-9\\s]{1,50}$";
        Pattern compiledHotelNamePattern = Pattern.compile(hotelNamePattern);
        return compiledHotelNamePattern.matcher(hotelName).matches();
    }

    /**
     * Checks if the given people limit is valid.
     *
     * @param peopleLimit The people limit to be validated.
     * @return true if the people limit is valid, false otherwise.
     */
    public static boolean isPeopleLimitValid(String peopleLimit){
        String peopleLimitPattern = "^[1-6]$";
        Pattern compiledPeopleLimitPattern = Pattern.compile(peopleLimitPattern);
        return compiledPeopleLimitPattern.matcher(peopleLimit).matches();
    }

    /**
     * Checks if the given price is valid.
     *
     * @param price The price to be validated.
     * @return true if the price is valid, false otherwise.
     */
    public static boolean isPriceValid(String price){
        String pricePattern = "^[1-9]\\d*(\\.\\d{1,2})?$";
        Pattern compiledPricePattern = Pattern.compile(pricePattern);
        return compiledPricePattern.matcher(price).matches();
    }
}
