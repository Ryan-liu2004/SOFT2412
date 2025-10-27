// src/test/java/DataValidatorTest.java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for DataValidator class - Student C
 */
public class DataValidatorTest {

    private DataValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new DataValidator();
    }

    @Test
    public void testValidCurrencies() {
        // TODO: Test that USD, EUR, GBP, AUD are valid
        assertTrue(validator.isValidCurrency("USD"));
        assertTrue(validator.isValidCurrency("EUR"));
        assertTrue(validator.isValidCurrency("GBP"));
        assertTrue(validator.isValidCurrency("AUD"));
        assertFalse(validator.isValidCurrency("XYZ"));
    }

    @Test
    public void testInvalidCurrencies() {
        // TODO: Test that invalid currencies are rejected
        assertFalse(validator.isValidCurrency("XYZ"));
        assertFalse(validator.isValidCurrency(""));
        assertFalse(validator.isValidCurrency(null));
        assertFalse(validator.isValidCurrency("INVALID"));
    }

    @Test
    public void testValidAmounts() {
        // TODO: Test that valid amount strings are accepted
        assertTrue(validator.isValidAmount("0"));
        assertTrue(validator.isValidAmount("0.0"));
        assertTrue(validator.isValidAmount(" 100 "));
        assertTrue(validator.isValidAmount("0.01"));
        assertTrue(validator.isValidAmount("1000.50"));
        assertTrue(validator.isValidAmount(".5"));
        assertTrue(validator.isValidAmount("5."));
        assertTrue(validator.isValidAmount("1e3"));
    }

    @Test
    public void testInvalidAmounts() {
        // TODO: Test that invalid amounts are rejected
        assertFalse(validator.isValidAmount("-100"));
        assertFalse(validator.isValidAmount("-0.01"));
        assertFalse(validator.isValidAmount("abc"));
        assertFalse(validator.isValidAmount(""));
        assertFalse(validator.isValidAmount("   "));
        assertFalse(validator.isValidAmount(null));
        assertFalse(validator.isValidAmount("NaN"));
        assertFalse(validator.isValidAmount("Infinity"));
        assertFalse(validator.isValidAmount("-Infinity"));
        assertFalse(validator.isValidAmount("1e309"));
        assertFalse(validator.isValidAmount("1_000"));
    }

    @Test
    public void testParseAmount() {
        // positive cases
        assertEquals(100.0, validator.parseAmount("100"), 0.01);
        assertEquals(50.75, validator.parseAmount("50.75"), 0.01);

        // negative cases
        assertEquals(0.0, validator.parseAmount("invalid"));
        assertEquals(0.0, validator.parseAmount("$50.00"));
        assertEquals(0.0, validator.parseAmount("12.3.4"));
        assertEquals(0.0, validator.parseAmount("   "));
        assertEquals(0.0, validator.parseAmount("-5"));
        

        // edge cases
        assertEquals(1000.0, validator.parseAmount("1e3"), 0.01); // Double.parseDouble supports it
        assertEquals(200.0, validator.parseAmount("\t\n  200  "), 0.01); // Test Whitespace trimming
        assertEquals(42.0, validator.parseAmount("+42"), 0.01);   // leading '+'

    }

    @Test
    public void testNormalizeCurrency() {
        // TODO: Test currency normalization
        assertEquals("USD", validator.normalizeCurrency("usd"));
        assertEquals("EUR", validator.normalizeCurrency(" eur "));
        assertEquals("GBP", validator.normalizeCurrency("Gbp"));
        assertEquals(null, validator.normalizeCurrency(null));
    }

    @Test
    public void testCaseInsensitiveCurrency() {
        // TODO: Test that currency validation is case-insensitive
        assertTrue(validator.isValidCurrency("usd"));
        assertTrue(validator.isValidCurrency("EUR"));
        assertTrue(validator.isValidCurrency("Gbp"));
    }
}
