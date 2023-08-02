package validation;

import com.client.validation.CreditCardValidator;
import com.client.validation.DateValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardValidatorTest {
    /**
     * Testuje walidację imienia i nazwiska
     */
    @Test
    public void testIsFirstNameAndLastNameValid() {
        assertTrue(CreditCardValidator.isFirstNameAndLastNameValid("John Doe"));
        assertFalse(CreditCardValidator.isFirstNameAndLastNameValid("123"));
        assertFalse(CreditCardValidator.isFirstNameAndLastNameValid("_invalid name"));
    }
    /**
     * Testuje walidację numeru karty
     */
    @Test
    public void testIsCreditCardNumberValid() {
        assertTrue(CreditCardValidator.isCreditCardNumberValid("1234567890123456"));
        assertFalse(CreditCardValidator.isCreditCardNumberValid("invalid"));
        assertFalse(CreditCardValidator.isCreditCardNumberValid("1234"));
    }
    /**
     * Testuje walidację cvv
     */
    @Test
    public void testIsCvvValid() {
        assertTrue(CreditCardValidator.isCvvValid("123"));
        assertFalse(CreditCardValidator.isCvvValid("invalid"));
        assertFalse(CreditCardValidator.isCvvValid("12"));
    }
}
