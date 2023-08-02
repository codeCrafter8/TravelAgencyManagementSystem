package validation;

import com.client.validation.ClientValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Klasa zawierająca pola i metody testujące klasę Validation
 */
public class ClientValidatorTest {
    /**
     * Testuje walidację emaila
     */
    @Test
    public void testIsEmailValid() {
        assertTrue(ClientValidator.isEmailValid("test@example.com"));
        assertFalse(ClientValidator.isEmailValid("invalidemail"));
        assertFalse(ClientValidator.isEmailValid("test@.com"));
    }
    /**
     * Testuje walidację hasła
     */
    @Test
    public void testIsPasswordValid() {
        assertTrue(ClientValidator.isPasswordValid("P@ssw0rd"));
        assertFalse(ClientValidator.isPasswordValid("weakpassword"));
        assertFalse(ClientValidator.isPasswordValid("123456789"));
    }
    /**
     * Testuje walidację numeru telefonu
     */
    @Test
    public void testIsPhoneNumberValid() {
        assertTrue(ClientValidator.isPhoneNumberValid("1234567890"));
        assertFalse(ClientValidator.isPhoneNumberValid("123"));
        assertFalse(ClientValidator.isPhoneNumberValid("invalidphone"));
    }
    /**
     * Testuje walidację imienia
     */
    @Test
    public void testIsFirstNameValid() {
        assertTrue(ClientValidator.isFirstNameValid("John"));
        assertFalse(ClientValidator.isFirstNameValid("123"));
    }
    /**
     * Testuje walidację nazwiska
     */
    @Test
    public void testIsLastNameValid() {
        assertTrue(ClientValidator.isLastNameValid("Doe"));
        assertFalse(ClientValidator.isLastNameValid("456"));
    }
}
