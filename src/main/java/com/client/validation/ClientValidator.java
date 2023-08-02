package com.client.validation;

import java.util.regex.Pattern;
/**
 * Klasa zawierająca pola i metody zapewniające możliwość przeprowadzenia walidacji
 */
public class ClientValidator {
    /**
     * Sprawdza, czy podany email jest poprawny.
     *
     * @param email adres email do sprawdzenia
     * @return true, jeśli email jest poprawny, false w przeciwnym razie
     */
    public static boolean isEmailValid(String email){
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
    public static boolean isPasswordValid(String password){
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
    public static boolean isPhoneNumberValid(String phoneNumber){
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
    public static boolean isFirstNameValid(String firstName){
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
    public static boolean isLastNameValid(String lastName){
        String lastNamePattern = "[a-zA-Z-']{2,}";
        Pattern compiledLastNamePattern = Pattern.compile(lastNamePattern);
        return compiledLastNamePattern.matcher(lastName).matches();
    }
}
