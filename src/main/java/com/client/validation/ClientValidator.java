package com.client.validation;

import java.util.regex.Pattern;

/**
 * A class that contains methods for validating clients information.
 */
public class ClientValidator {

    /**
     * Checks if the given email is valid.
     *
     * @param email The email address to be validated.
     * @return true if the email is valid, false otherwise.
     */
    public static boolean isEmailValid(String email){
        String emailPattern = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern compiledEmailPattern = Pattern.compile(emailPattern);
        return compiledEmailPattern.matcher(email).matches();
    }

    /**
     * Checks if the given password is valid.
     *
     * @param password The password to be validated.
     * @return true if the password is valid, false otherwise.
     */
    public static boolean isPasswordValid(String password){
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern compiledPasswordPattern = Pattern.compile(passwordPattern);
        return compiledPasswordPattern.matcher(password).matches();
    }

    /**
     * Checks if the given phone number is valid.
     *
     * @param phoneNumber The phone number to be validated.
     * @return true if the phone number is valid, false otherwise.
     */
    public static boolean isPhoneNumberValid(String phoneNumber){
        String phoneNumberPattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
        Pattern compiledPhoneNumberPattern = Pattern.compile(phoneNumberPattern);
        return compiledPhoneNumberPattern.matcher(phoneNumber).matches();
    }

    /**
     * Checks if the given first name is valid.
     *
     * @param firstName The first name to be validated.
     * @return true if the first name is valid, false otherwise.
     */
    public static boolean isFirstNameValid(String firstName){
        String firstNamePattern = "[a-zA-Z-]{2,}";
        Pattern compiledFirstNamePattern = Pattern.compile(firstNamePattern);
        return compiledFirstNamePattern.matcher(firstName).matches();
    }

    /**
     * Checks if the given last name is valid.
     *
     * @param lastName The last name to be validated.
     * @return true if the last name is valid, false otherwise.
     */
    public static boolean isLastNameValid(String lastName){
        String lastNamePattern = "[a-zA-Z-']{2,}";
        Pattern compiledLastNamePattern = Pattern.compile(lastNamePattern);
        return compiledLastNamePattern.matcher(lastName).matches();
    }
}
