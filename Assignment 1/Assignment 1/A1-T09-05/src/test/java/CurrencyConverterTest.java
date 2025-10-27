
// src/test/java/CurrencyConverterTest.java
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for CurrencyConverter class - Student A
 */
public class CurrencyConverterTest {

    private CurrencyConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new CurrencyConverter();
    }

    @Test
    public void testConvertUSDToEUR() {
        // TODO: Test converting 100 USD to EUR
        // Should return approximately 85.0 if rate is 0.85

        //Test normal case
        double result = converter.convert(100.0, "USD", "EUR");
        assertEquals(85.0, result, 0.0001, "100 USD should equal 85.0 EUR at rate 0.85");
        //Test edge case
        result = converter.convert(0.0, "USD", "EUR");
        assertEquals(0.0, result, 0.0001, "0 USD should equal 0.0 EUR");
        //Test null case
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(100.0, null, "EUR");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(100.0, "USD", null);
        });
        //Test invalid currency code
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(100.0, "USD", "XYZ");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(100.0, "ABC", "EUR");
        });
    }

    @Test
    public void testConvertSameCurrency() {
        // TODO: Test converting USD to USD should return same amount
        double amount = 100.0;
        double result = converter.convert(amount, "USD", "USD");
        assertEquals(amount, result, 0.0001, "Converting same currency should return the same amount");
    }

    @Test
    public void testConvertZeroAmount() {
        // TODO: Test converting 0 amount
        double amount = 0.0;
        double result = converter.convert(amount, "USD", "EUR");
        assertEquals(0.0, result, 0.0001, "Converting zero should always return zero");
    }

    @Test
    public void testGetSupportedCurrencies() {
        // TODO: Test that getSupportedCurrencies returns correct array
        // Should contain ["USD", "EUR", "GBP", "AUD"]
        String[] arr = converter.getSupportedCurrencies();
        assertNotNull(arr, "Supported currencies array must not be null");
        assertTrue(arr.length >= 4, "There should be at least 4 supported currencies");
        java.util.Set<String> actual = new java.util.LinkedHashSet<>();
        for (String c : arr) {
            assertNotNull(c, "Currency code must not be null");
            assertFalse(c.trim().isEmpty(), "Currency code must not be blank");
            actual.add(c.trim().toUpperCase());
        }
        assertTrue(
            actual.containsAll(java.util.Arrays.asList("USD", "EUR", "GBP", "AUD")),
            "Supported currencies must include USD, EUR, GBP, AUD"
        );
    }

    @Test
    public void testGetExchangeRate() {
        // TODO: Test getting exchange rate between two currencies
        assertEquals(0.85, converter.getExchangeRate("USD", "EUR"), 1e-9);
        assertEquals(1.18, converter.getExchangeRate("EUR", "USD"), 1e-9);
        assertEquals(1.00, converter.getExchangeRate("GBP", "GBP"), 1e-9);

        // Invalid inputs should throw
        assertThrows(IllegalArgumentException.class, () -> converter.getExchangeRate("USD", "XYZ"));
        assertThrows(IllegalArgumentException.class, () -> converter.getExchangeRate("XYZ", "EUR"));
    }

    @Test
    public void testConvertAllCurrencyPairs() {
        // TODO: Test conversion between all supported currency pairs
        String[] currencies = converter.getSupportedCurrencies();

        for (String from : currencies) 
        {
            for (String to : currencies) 
            {
                double rate = converter.getExchangeRate(from, to);
                double expected = 100.0 * rate;

                // convert() should match 100 * exchange rate, rounded to 2dp
                double actual = converter.convert(100.0, from, to);

                // Round expected to 2 decimals, same as convert()
                double roundedExpected = Math.round(expected * 100.0) / 100.0;

                assertEquals(roundedExpected, actual, 1e-9,
                        () -> "Mismatch for " + from + "->" + to);
            }
        }
    }

    @Test
    public void testRoundToTwoDecimals_RoundDown() {
        // Test round down
        assertEquals(12.34, converter.roundToTwoDecimals(12.344), 0.001);
    }

    @Test
    public void testRoundToTwoDecimals_RoundUp() {
        // Test round up
        assertEquals(12.35, converter.roundToTwoDecimals(12.346), 0.001);
    }

    @Test
    public void testRoundToTwoDecimals_Equal() {
        // Test equal
        assertEquals(123.50, converter.roundToTwoDecimals(123.50), 0.001);
    }

    @Test
    public void testRoundToTwoDecimals_Zero() {
        // Test zero
        assertEquals(0.0, converter.roundToTwoDecimals(0.0), 0.001);
    }
}
