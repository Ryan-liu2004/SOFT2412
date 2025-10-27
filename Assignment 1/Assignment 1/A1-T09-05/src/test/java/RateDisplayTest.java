import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for RateDisplay class - Student D
 */
public class RateDisplayTest {
    private RateDisplay rateDisplay;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    // Removed unused variable originalOut
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        rateDisplay = new RateDisplay();
        originalOut = System.out;

        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testShowAllRatesTable() {
        // TODO: Test that showAllRatesTable displays all rates

        String table = "Exchange Rates (base = 1 unit)\n" +
            "             USD     EUR     GBP     AUD\n" +
            "     USD    1.00    0.85    0.75    1.30\n" +
            "     EUR    1.18    1.00    0.88    1.53\n" +
            "     GBP    1.33    1.13    1.00    1.73\n" +
            "     AUD    0.77    0.65    0.58    1.00\n";
        rateDisplay.showAllRatesTable();
        String[] expected = table.split("\n"), actual = outContent.toString().split("\n");
        for(int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].trim(), actual[i].trim());
        }
    }

    @Test
    public void testShowRate() {
        // TODO: Test that showRate displays the correct rate
        rateDisplay.showRate(null, "USD");
        rateDisplay.showRate("AUD", null);
        String out = outContent.toString();
        assertTrue(out.contains("Invalid currency."),
                "Should print 'Invalid currency.' when from/to is null");
        outContent.reset();
        rateDisplay.showRate("ZZZ", "USD");
        out = outContent.toString();
        assertTrue(out.contains("Unsupported currency pair: ZZZ -> USD"),
                "Unsupported pair should print the expected message");
        outContent.reset();
        rateDisplay.showRate(" eur ", "gbp");
        out = outContent.toString();
        assertTrue(out.contains("EUR -> GBP :"),
                "Output should normalize codes to upper-case and trim spaces");
        outContent.reset();
        String from = "AUD", to = "USD";
        double rate = new CurrencyConverter().getExchangeRate(from, to);
        String expected = String.format("%s -> %s : %.2f", from, to, rate);
        rateDisplay.showRate(from, to);
        out = outContent.toString();
        assertTrue(out.contains(expected),
                "Printed rate should match converter and use two decimals");
    }

    @Test
    public void testShowAllPairs() {
        // TODO: Test that showAllPairs displays all currency pairs
        // Act
        rateDisplay.showAllPairs();

        // Get printed output
        String output = outContent.toString();

        // Assert: at least check that some expected pairs are shown
        assertTrue(output.contains("USD -> EUR"), "Output should contain USD -> EUR pair");
        assertTrue(output.contains("EUR -> GBP"), "Output should contain EUR -> GBP pair");
        assertTrue(output.contains("AUD -> USD"), "Output should contain AUD -> USD pair");

        // Also assert formatting with ": " if needed
        assertTrue(output.matches("(?s).*USD -> EUR.*:.*\\d+\\.\\d{2}.*"),
                "Should display formatted rate for USD -> EUR");
    }

    @Test
    public void testShowSameCurrencyRates() {
        // TODO: Test that showSameCurrencyRates displays correct rates
        rateDisplay.showSameCurrencyRates();
        String output = outContent.toString();

        assertTrue(output.contains("1.00 USD = 1.00 USD"));
        assertTrue(output.contains("1.00 EUR = 1.00 EUR"));
        assertTrue(output.contains("1.00 GBP = 1.00 GBP"));
        assertTrue(output.contains("1.00 AUD = 1.00 AUD"));
    }
}
