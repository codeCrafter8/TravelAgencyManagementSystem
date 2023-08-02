package com.client.validation;

import java.util.regex.Pattern;

public class CreditCardValidator {
    /**
     * Sprawdza, czy podane imię i nazwisko jest poprawne.
     *
     * @param firstNameAndLastName imię i nazwisko do sprawdzenia
     * @return true, jeśli imię i nazwisko jest poprawne, false w przeciwnym razie
     */
    public static boolean isFirstNameAndLastNameValid(String firstNameAndLastName){
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
    public static boolean isCreditCardNumberValid(String creditCardNumber){
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
    public static boolean isCvvValid(String cvv){
        String cvvPattern = "^\\d{3,4}$";
        Pattern compiledCvvPattern = Pattern.compile(cvvPattern);
        return compiledCvvPattern.matcher(cvv).matches();
    }
}
