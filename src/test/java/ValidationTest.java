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
        assertTrue(Validation.emailIsValid("test@example.com"));
        assertFalse(Validation.emailIsValid("invalidemail"));
        assertFalse(Validation.emailIsValid("test@.com"));
    }
    /**
     * Testuje walidację hasła
     */
    @Test
    public void testPasswordIsValid() {
        assertTrue(Validation.passwordIsValid("P@ssw0rd"));
        assertFalse(Validation.passwordIsValid("weakpassword"));
        assertFalse(Validation.passwordIsValid("123456789"));
    }
    /**
     * Testuje walidację numeru telefonu
     */
    @Test
    public void testPhoneNumberIsValid() {
        assertTrue(Validation.phoneNumberIsValid("1234567890"));
        assertFalse(Validation.phoneNumberIsValid("123"));
        assertFalse(Validation.phoneNumberIsValid("invalidphone"));
    }
    /**
     * Testuje walidację imienia
     */
    @Test
    public void testFirstNameIsValid() {
        assertTrue(Validation.firstNameIsValid("John"));
        assertFalse(Validation.firstNameIsValid("123"));
    }
    /**
     * Testuje walidację nazwiska
     */
    @Test
    public void testLastNameIsValid() {
        assertTrue(Validation.lastNameIsValid("Doe"));
        assertFalse(Validation.lastNameIsValid("456"));
    }
    /**
     * Testuje walidację kraju lub miasta
     */
    @Test
    public void testCountryOrCityIsValid() {
        assertTrue(Validation.countryOrCityIsValid("USA"));
        assertFalse(Validation.countryOrCityIsValid("123"));
    }
    /**
     * Testuje walidację nazwy hotelu
     */
    @Test
    public void testHotelNameIsValid() {
        assertTrue(Validation.hotelNameIsValid("Hilton"));
        assertFalse(Validation.hotelNameIsValid("_1"));
    }
    /**
     * Testuje walidację limitu osób
     */
    @Test
    public void testPeopleLimitIsValid() {
        assertTrue(Validation.peopleLimitIsValid("5"));
        assertFalse(Validation.peopleLimitIsValid("invalid"));
        assertFalse(Validation.peopleLimitIsValid("0"));
    }
    /**
     * Testuje walidację ceny
     */
    @Test
    public void testPriceIsValid() {
        assertTrue(Validation.priceIsValid("99.99"));
        assertFalse(Validation.priceIsValid("invalid"));
        assertFalse(Validation.priceIsValid("-10.50"));
    }
    /**
     * Testuje walidację imienia i nazwiska
     */
    @Test
    public void testFirstNameAndLastNameIsValid() {
        assertTrue(Validation.firstNameAndLastNameIsValid("John Doe"));
        assertFalse(Validation.firstNameAndLastNameIsValid("123"));
        assertFalse(Validation.firstNameAndLastNameIsValid("_invalid name"));
    }
    /**
     * Testuje walidację numeru karty
     */
    @Test
    public void testCreditCardNumberIsValid() {
        assertTrue(Validation.creditCardNumberIsValid("1234567890123456"));
        assertFalse(Validation.creditCardNumberIsValid("invalid"));
        assertFalse(Validation.creditCardNumberIsValid("1234"));
    }
    /**
     * Testuje walidację cvv
     */
    @Test
    public void testCvvIsValid() {
        assertTrue(Validation.cvvIsValid("123"));
        assertFalse(Validation.cvvIsValid("invalid"));
        assertFalse(Validation.cvvIsValid("12"));
    }
    /**
     * Testuje walidację miesiąca
     */
    @Test
    public void testMonthIsValid() {
        assertTrue(Validation.monthIsValid("12"));
        assertFalse(Validation.monthIsValid("invalid"));
        assertFalse(Validation.monthIsValid("0"));
    }
    /**
     * Testuje walidację roku
     */
    @Test
    public void testYearIsValid() {
        assertTrue(Validation.yearIsValid("2023"));
        assertFalse(Validation.yearIsValid("invalid"));
        assertFalse(Validation.yearIsValid("1000"));
    }
    /**
     * Testuje walidację daty
     */
    @Test
    public void testDateIsValid() {
        assertTrue(Validation.dateIsValid("03/06/2023"));
        assertFalse(Validation.dateIsValid("invalid"));
        assertFalse(Validation.dateIsValid("2023/06/03"));
    }
}
