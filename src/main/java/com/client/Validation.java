package com.client;

import java.util.regex.Pattern;

public class Validation{
    private final String emailPattern = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final Pattern compiledEmailPattern = Pattern.compile(emailPattern);
    private final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    private final Pattern compiledPasswordPattern = Pattern.compile(passwordPattern);
    private final String phoneNumberPattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
    private final Pattern compiledPhoneNumberPattern = Pattern.compile(phoneNumberPattern);

    private final String firstNamePattern = "[a-zA-Z-]{2,}";
    private final Pattern compiledFirstNamePattern = Pattern.compile(firstNamePattern);

    private final String lastNamePattern = "[a-zA-Z-']{2,}";
    public final Pattern compiledLastNamePattern = Pattern.compile(lastNamePattern);
    private final String countryOrCityPattern = "^[A-Za-z\\s]+$";
    private final Pattern compiledCountryOrCityPattern = Pattern.compile(countryOrCityPattern);
    private final String peopleLimitPattern = "^[1-6]$";
    private final Pattern compiledPeopleLimitPattern = Pattern.compile(peopleLimitPattern);
    private final String pricePattern = "^[1-9]\\d*(\\.\\d{1,2})?$";
    private final Pattern compiledPricePattern = Pattern.compile(pricePattern);
    private final String hotelNamePattern = "^[a-zA-Z0-9\\s]{1,50}$";
    private final Pattern compiledHotelNamePattern = Pattern.compile(hotelNamePattern);
    private final String firstNameAndLastNamePattern = "^[\\p{L}\\s'-]+$";
    private final Pattern compiledFirstNameAndLastNamePattern = Pattern.compile(firstNameAndLastNamePattern);
    private final String creditCardNumberPattern = "^\\d{4}[ -]?\\d{4}[ -]?\\d{4}[ -]?\\d{4}$";
    private final Pattern compiledCreditCardNumberPattern = Pattern.compile(creditCardNumberPattern);
    private final String cvvPattern = "^\\d{3,4}$";
    private final Pattern compiledCvvPattern = Pattern.compile(cvvPattern);
    private final String monthPattern = "^(0?[1-9]|1[0-2])$";
    private final Pattern compiledMonthPattern = Pattern.compile(monthPattern);
    private final String yearPattern = "^((20)\\d{2}|\\d{2})$";
    private final Pattern compiledYearPattern = Pattern.compile(yearPattern);
    private final String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
    private final Pattern compiledDatePattern = Pattern.compile(datePattern);
    public boolean emailIsValid(String email){
        return compiledEmailPattern.matcher(email).matches();
    }

    public boolean passwordIsValid(String password){
        return compiledPasswordPattern.matcher(password).matches();
    }
    public boolean phoneNumberIsValid(String phoneNumber){
        return compiledPhoneNumberPattern.matcher(phoneNumber).matches();
    }
    public boolean firstNameIsValid(String firstName){
        return compiledFirstNamePattern.matcher(firstName).matches();
    }
    public boolean lastNameIsValid(String lastName){
        return compiledLastNamePattern.matcher(lastName).matches();
    }
    public boolean countryOrCityIsValid(String text){
        return compiledCountryOrCityPattern.matcher(text).matches();
    }
    public boolean hotelNameIsValid(String hotelName){
        return compiledHotelNamePattern.matcher(hotelName).matches();
    }
    public boolean peopleLimitIsValid(String peopleLimit){
        return compiledPeopleLimitPattern.matcher(peopleLimit).matches();
    }
    public boolean priceIsValid(String price){
        return compiledPricePattern.matcher(price).matches();
    }
    public boolean firstNameAndLastNameIsValid(String firstNameAndLastName){
        return compiledFirstNameAndLastNamePattern.matcher(firstNameAndLastName).matches();
    }
    public boolean creditCardNumberIsValid(String creditCardNumber){
        return compiledCreditCardNumberPattern.matcher(creditCardNumber).matches();
    }
    public boolean cvvIsValid(String cvv){
        return compiledCvvPattern.matcher(cvv).matches();
    }
    public boolean monthIsValid(String month){
        return compiledMonthPattern.matcher(month).matches();
    }
    public boolean yearIsValid(String year){
        return compiledYearPattern.matcher(year).matches();
    }
    public boolean dateIsValid(String date){
        return compiledDatePattern.matcher(date).matches();
    }
}
