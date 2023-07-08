import org.junit.jupiter.api.Test;
import com.client.Validation;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * Klasa zawierająca pola i metody testujące klasę Validation
 */
public class ValidationTest {
    /**
     * Testuje walidację emaila
     */
    @Test
    public void testEmailIsValid() {
        assertTrue(Validation.isEmailValid("test@example.com"));
        assertFalse(Validation.isEmailValid("invalidemail"));
        assertFalse(Validation.isEmailValid("test@.com"));
    }
    /**
     * Testuje walidację hasła
     */
    @Test
    public void testPasswordIsValid() {
        assertTrue(Validation.isPasswordValid("P@ssw0rd"));
        assertFalse(Validation.isPasswordValid("weakpassword"));
        assertFalse(Validation.isPasswordValid("123456789"));
    }
    /**
     * Testuje walidację numeru telefonu
     */
    @Test
    public void testPhoneNumberIsValid() {
        assertTrue(Validation.isPhoneNumberValid("1234567890"));
        assertFalse(Validation.isPhoneNumberValid("123"));
        assertFalse(Validation.isPhoneNumberValid("invalidphone"));
    }
    /**
     * Testuje walidację imienia
     */
    @Test
    public void testFirstNameIsValid() {
        assertTrue(Validation.isFirstNameValid("John"));
        assertFalse(Validation.isFirstNameValid("123"));
    }
    /**
     * Testuje walidację nazwiska
     */
    @Test
    public void testLastNameIsValid() {
        assertTrue(Validation.isLastNameValid("Doe"));
        assertFalse(Validation.isLastNameValid("456"));
    }
    /**
     * Testuje walidację kraju lub miasta
     */
    @Test
    public void testCountryOrCityIsValid() {
        assertTrue(Validation.isCountryOrCityValid("USA"));
        assertFalse(Validation.isCountryOrCityValid("123"));
    }
    /**
     * Testuje walidację nazwy hotelu
     */
    @Test
    public void testHotelNameIsValid() {
        assertTrue(Validation.isHotelNameValid("Hilton"));
        assertFalse(Validation.isHotelNameValid("_1"));
    }
    /**
     * Testuje walidację limitu osób
     */
    @Test
    public void testPeopleLimitIsValid() {
        assertTrue(Validation.isPeopleLimitValid("5"));
        assertFalse(Validation.isPeopleLimitValid("invalid"));
        assertFalse(Validation.isPeopleLimitValid("0"));
    }
    /**
     * Testuje walidację ceny
     */
    @Test
    public void testPriceIsValid() {
        assertTrue(Validation.isPriceValid("99.99"));
        assertFalse(Validation.isPriceValid("invalid"));
        assertFalse(Validation.isPriceValid("-10.50"));
    }
    /**
     * Testuje walidację imienia i nazwiska
     */
    @Test
    public void testFirstNameAndLastNameIsValid() {
        assertTrue(Validation.isFirstNameAndLastNameValid("John Doe"));
        assertFalse(Validation.isFirstNameAndLastNameValid("123"));
        assertFalse(Validation.isFirstNameAndLastNameValid("_invalid name"));
    }
    /**
     * Testuje walidację numeru karty
     */
    @Test
    public void testCreditCardNumberIsValid() {
        assertTrue(Validation.isCreditCardNumberValid("1234567890123456"));
        assertFalse(Validation.isCreditCardNumberValid("invalid"));
        assertFalse(Validation.isCreditCardNumberValid("1234"));
    }
    /**
     * Testuje walidację cvv
     */
    @Test
    public void testCvvIsValid() {
        assertTrue(Validation.isCvvValid("123"));
        assertFalse(Validation.isCvvValid("invalid"));
        assertFalse(Validation.isCvvValid("12"));
    }
    /**
     * Testuje walidację miesiąca
     */
    @Test
    public void testMonthIsValid() {
        assertTrue(Validation.isMonthValid("12"));
        assertFalse(Validation.isMonthValid("invalid"));
        assertFalse(Validation.isMonthValid("0"));
    }
    /**
     * Testuje walidację roku
     */
    @Test
    public void testYearIsValid() {
        assertTrue(Validation.isYearValid("2023"));
        assertFalse(Validation.isYearValid("invalid"));
        assertFalse(Validation.isYearValid("1000"));
    }
    /**
     * Testuje walidację daty
     */
    @Test
    public void testDateIsValid() {
        assertTrue(Validation.isDateValid("03/06/2023"));
        assertFalse(Validation.isDateValid("invalid"));
        assertFalse(Validation.isDateValid("2023/06/03"));
    }
}
