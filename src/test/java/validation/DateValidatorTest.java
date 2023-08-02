package validation;

import com.client.validation.DateValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidatorTest {
    /**
     * Testuje walidację miesiąca
     */
    @Test
    public void testIsMonthValid() {
        assertTrue(DateValidator.isMonthValid("12"));
        assertFalse(DateValidator.isMonthValid("invalid"));
        assertFalse(DateValidator.isMonthValid("0"));
    }
    /**
     * Testuje walidację roku
     */
    @Test
    public void testIsYearValid() {
        assertTrue(DateValidator.isYearValid("2023"));
        assertFalse(DateValidator.isYearValid("invalid"));
        assertFalse(DateValidator.isYearValid("1000"));
    }
    /**
     * Testuje walidację daty
     */
    @Test
    public void testIsDateValid() {
        assertTrue(DateValidator.isDateValid("03/06/2023"));
        assertFalse(DateValidator.isDateValid("invalid"));
        assertFalse(DateValidator.isDateValid("2023/06/03"));
    }
}
