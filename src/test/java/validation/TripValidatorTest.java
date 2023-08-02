package validation;

import com.client.validation.TripValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripValidatorTest {
    /**
     * Testuje walidację kraju lub miasta
     */
    @Test
    public void testIsCountryOrCityValid() {
        assertTrue(TripValidator.isCountryOrCityValid("USA"));
        assertFalse(TripValidator.isCountryOrCityValid("123"));
    }
    /**
     * Testuje walidację nazwy hotelu
     */
    @Test
    public void testIsHotelNameValid() {
        assertTrue(TripValidator.isHotelNameValid("Hilton"));
        assertFalse(TripValidator.isHotelNameValid("_1"));
    }
    /**
     * Testuje walidację limitu osób
     */
    @Test
    public void testIsPeopleLimitValid() {
        assertTrue(TripValidator.isPeopleLimitValid("5"));
        assertFalse(TripValidator.isPeopleLimitValid("invalid"));
        assertFalse(TripValidator.isPeopleLimitValid("0"));
    }
    /**
     * Testuje walidację ceny
     */
    @Test
    public void testIsPriceValid() {
        assertTrue(TripValidator.isPriceValid("99.99"));
        assertFalse(TripValidator.isPriceValid("invalid"));
        assertFalse(TripValidator.isPriceValid("-10.50"));
    }
}
