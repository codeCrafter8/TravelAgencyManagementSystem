import org.junit.jupiter.api.Test;
import com.client.Validation;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {
    @Test
    public void testEmailIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.emailIsValid("test@example.com"));
        assertFalse(validation.emailIsValid("invalidemail"));
        assertFalse(validation.emailIsValid("test@.com"));
    }

    @Test
    public void testPasswordIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.passwordIsValid("P@ssw0rd"));
        assertFalse(validation.passwordIsValid("weakpassword"));
        assertFalse(validation.passwordIsValid("123456789"));
    }

    @Test
    public void testPhoneNumberIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.phoneNumberIsValid("1234567890"));
        assertFalse(validation.phoneNumberIsValid("123"));
        assertFalse(validation.phoneNumberIsValid("invalidphone"));
    }

    @Test
    public void testFirstNameIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.firstNameIsValid("John"));
        assertFalse(validation.firstNameIsValid("123"));
    }

    @Test
    public void testLastNameIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.lastNameIsValid("Doe"));
        assertFalse(validation.lastNameIsValid("456"));
    }

    @Test
    public void testCountryOrCityIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.countryOrCityIsValid("USA"));
        assertFalse(validation.countryOrCityIsValid("123"));
    }

    @Test
    public void testHotelNameIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.hotelNameIsValid("Hilton"));
        assertFalse(validation.hotelNameIsValid("_1"));
    }

    @Test
    public void testPeopleLimitIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.peopleLimitIsValid("5"));
        assertFalse(validation.peopleLimitIsValid("invalid"));
        assertFalse(validation.peopleLimitIsValid("0"));
    }

    @Test
    public void testPriceIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.priceIsValid("99.99"));
        assertFalse(validation.priceIsValid("invalid"));
        assertFalse(validation.priceIsValid("-10.50"));
    }

    @Test
    public void testFirstNameAndLastNameIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.firstNameAndLastNameIsValid("John Doe"));
        assertFalse(validation.firstNameAndLastNameIsValid("123"));
        assertFalse(validation.firstNameAndLastNameIsValid("_invalid name"));
    }

    @Test
    public void testCreditCardNumberIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.creditCardNumberIsValid("1234567890123456"));
        assertFalse(validation.creditCardNumberIsValid("invalid"));
        assertFalse(validation.creditCardNumberIsValid("1234"));
    }

    @Test
    public void testCvvIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.cvvIsValid("123"));
        assertFalse(validation.cvvIsValid("invalid"));
        assertFalse(validation.cvvIsValid("12"));
    }

    @Test
    public void testMonthIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.monthIsValid("12"));
        assertFalse(validation.monthIsValid("invalid"));
        assertFalse(validation.monthIsValid("0"));
    }

    @Test
    public void testYearIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.yearIsValid("2023"));
        assertFalse(validation.yearIsValid("invalid"));
        assertFalse(validation.yearIsValid("1000"));
    }

    @Test
    public void testDateIsValid() {
        Validation validation = new Validation();

        assertTrue(validation.dateIsValid("03/06/2023"));
        assertFalse(validation.dateIsValid("invalid"));
        assertFalse(validation.dateIsValid("2023/06/03"));
    }
}
