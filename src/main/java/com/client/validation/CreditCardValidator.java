package com.client.validation;

import java.util.regex.Pattern;

/**
 * A class that contains methods for validating credit card information.
 */
public class CreditCardValidator {

    /**
     * Checks if the given first name and last name are valid.
     *
     * @param firstNameAndLastName The first name and last name to be validated.
     * @return true if the first name and last name are valid, false otherwise.
     */
    public static boolean isFirstNameAndLastNameValid(String firstNameAndLastName){
        String firstNameAndLastNamePattern = "^[\\p{L}\\s'-]+$";
        Pattern compiledFirstNameAndLastNamePattern = Pattern.compile(firstNameAndLastNamePattern);
        return compiledFirstNameAndLastNamePattern.matcher(firstNameAndLastName).matches();
    }

    /**
     * Checks if the given credit card number is valid.
     *
     * @param creditCardNumber The credit card number to be validated.
     * @return true if the credit card number is valid, false otherwise.
     */
    public static boolean isCreditCardNumberValid(String creditCardNumber){
        String creditCardNumberPattern = "^\\d{4}[ -]?\\d{4}[ -]?\\d{4}[ -]?\\d{4}$";
        Pattern compiledCreditCardNumberPattern = Pattern.compile(creditCardNumberPattern);
        return compiledCreditCardNumberPattern.matcher(creditCardNumber).matches();
    }

    /**
     * Checks if the given CVV number is valid.
     *
     * @param cvv The CVV number to be validated.
     * @return true if the CVV number is valid, false otherwise.
     */
    public static boolean isCvvValid(String cvv){
        String cvvPattern = "^\\d{3,4}$";
        Pattern compiledCvvPattern = Pattern.compile(cvvPattern);
        return compiledCvvPattern.matcher(cvv).matches();
    }
}
