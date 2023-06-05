package com.client;

import java.util.regex.Pattern;
/**
 * Klasa zawierająca pola i metody zapewniające możliwość przeprowadzenia walidacji
 */
public class Validation{
    /**
     * Sprawdza, czy podany email jest poprawny.
     *
     * @param email adres email do sprawdzenia
     * @return true, jeśli email jest poprawny, false w przeciwnym razie
     */
    public boolean emailIsValid(String email){
        String emailPattern = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern compiledEmailPattern = Pattern.compile(emailPattern);
        return compiledEmailPattern.matcher(email).matches();
    }
    /**
     * Sprawdza, czy podane hasło jest poprawne.
     *
     * @param password hasło do sprawdzenia
     * @return true, jeśli hasło jest poprawne, false w przeciwnym razie
     */
    public boolean passwordIsValid(String password){
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern compiledPasswordPattern = Pattern.compile(passwordPattern);
        return compiledPasswordPattern.matcher(password).matches();
    }
    /**
     * Sprawdza, czy podany numer telefonu jest poprawny.
     *
     * @param phoneNumber numer telefonu do sprawdzenia
     * @return true, jeśli numer telefonu jest poprawny, false w przeciwnym razie
     */
    public boolean phoneNumberIsValid(String phoneNumber){
        String phoneNumberPattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        Pattern compiledPhoneNumberPattern = Pattern.compile(phoneNumberPattern);
        return compiledPhoneNumberPattern.matcher(phoneNumber).matches();
    }
    /**
     * Sprawdza, czy podane imię jest poprawne.
     *
     * @param firstName imię do sprawdzenia
     * @return true, jeśli imię jest poprawne, false w przeciwnym razie
     */
    public boolean firstNameIsValid(String firstName){
        String firstNamePattern = "[a-zA-Z-]{2,}";
        Pattern compiledFirstNamePattern = Pattern.compile(firstNamePattern);
        return compiledFirstNamePattern.matcher(firstName).matches();
    }
    /**
     * Sprawdza, czy podane nazwisko jest poprawne.
     *
     * @param lastName nazwisko do sprawdzenia
     * @return true, jeśli nazwisko jest poprawne, false w przeciwnym razie
     */
    public boolean lastNameIsValid(String lastName){
        String lastNamePattern = "[a-zA-Z-']{2,}";
        Pattern compiledLastNamePattern = Pattern.compile(lastNamePattern);
        return compiledLastNamePattern.matcher(lastName).matches();
    }
    /**
     * Sprawdza, czy podane miasto lub kraj jest poprawne.
     *
     * @param text miasto lub kraj do sprawdzenia
     * @return true, jeśli miasto lub kraj jest poprawne, false w przeciwnym razie
     */
    public boolean countryOrCityIsValid(String text){
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
    public boolean hotelNameIsValid(String hotelName){
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
    public boolean peopleLimitIsValid(String peopleLimit){
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
    public boolean priceIsValid(String price){
        String pricePattern = "^[1-9]\\d*(\\.\\d{1,2})?$";
        Pattern compiledPricePattern = Pattern.compile(pricePattern);
        return compiledPricePattern.matcher(price).matches();
    }
    /**
     * Sprawdza, czy podane imię i nazwisko jest poprawne.
     *
     * @param firstNameAndLastName imię i nazwisko do sprawdzenia
     * @return true, jeśli imię i nazwisko jest poprawne, false w przeciwnym razie
     */
    public boolean firstNameAndLastNameIsValid(String firstNameAndLastName){
        String firstNameAndLastNamePattern = "^[\\p{L}\\s'-]+$";
        Pattern compiledFirstNameAndLastNamePattern = Pattern.compile(firstNameAndLastNamePattern);
        return compiledFirstNameAndLastNamePattern.matcher(firstNameAndLastName).matches();
    }
    /**
     * Sprawdza, czy podany numer karty kredytowej jest poprawny.
     *
     * @param creditCardNumber numer karty kredytowej do sprawdzenia
     * @return true, jeśli numer karty kredytowej jest poprawny, false w przeciwnym razie
     */
    public boolean creditCardNumberIsValid(String creditCardNumber){
        String creditCardNumberPattern = "^\\d{4}[ -]?\\d{4}[ -]?\\d{4}[ -]?\\d{4}$";
        Pattern compiledCreditCardNumberPattern = Pattern.compile(creditCardNumberPattern);
        return compiledCreditCardNumberPattern.matcher(creditCardNumber).matches();
    }
    /**
     * Sprawdza, czy podany numer cvv jest poprawny.
     *
     * @param cvv numer cvv do sprawdzenia
     * @return true, jeśli numer cvv jest poprawny, false w przeciwnym razie
     */
    public boolean cvvIsValid(String cvv){
        String cvvPattern = "^\\d{3,4}$";
        Pattern compiledCvvPattern = Pattern.compile(cvvPattern);
        return compiledCvvPattern.matcher(cvv).matches();
    }
    /**
     * Sprawdza, czy podany miesiąc jest poprawny.
     *
     * @param month miesiąc do sprawdzenia
     * @return true, jeśli miesiąc jest poprawny, false w przeciwnym razie
     */
    public boolean monthIsValid(String month){
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
    public boolean yearIsValid(String year){
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
    public boolean dateIsValid(String date){
        String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern compiledDatePattern = Pattern.compile(datePattern);
        return compiledDatePattern.matcher(date).matches();
    }
}
