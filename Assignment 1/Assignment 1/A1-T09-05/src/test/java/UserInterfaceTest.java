// src/test/java/UserInterfaceTest.java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for UserInterface class - Student B
 */
public class UserInterfaceTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        originalIn = System.in;
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    
    }

    @AfterEach
    public void resetIO() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

     /** Build a UI whose Scanner is bound to the provided input script. */
     private UserInterface uiWithInput(String script) {
        System.setIn(new ByteArrayInputStream(script.getBytes()));
        return new UserInterface(); // Scanner binds to current System.in
    }

    @Test
    public void testUserInterfaceCreation() {
        // TODO: Test that UserInterface can be created
        UserInterface ui = uiWithInput("");
        assertNotNull(ui);
    }

    @Test
    public void testShowMenu() {
        // TODO: Test that showMenu method exists and runs
        // You might capture System.out to test menu display
        UserInterface ui = uiWithInput("");

        assertDoesNotThrow(ui::showMenu, "showMenu should run without exception");

        String out = outContent.toString();
        assertTrue(out.contains("=== Currency Converter ==="));
        assertTrue(out.contains("1. Convert Currency"));
        assertTrue(out.contains("2. View Exchange Rates"));
        assertTrue(out.contains("3. Exit"));

        // Ensure menu test doesn't include conversion prompts/results
        assertFalse(out.contains("Enter amount:"), "Menu should not include conversion prompts");
        assertFalse(out.contains("Result:"), "Menu should not include conversion results");
    }

    @Test
    public void testShowExchangeRates() {
        // TODO: Test that showExchangeRates displays rates
        UserInterface ui = uiWithInput("");
        ui.showExchangeRates();
        assertFalse(outContent.toString().isEmpty());
    }

    @Test
    public void testHandleConversion() {
        // TODO: Test conversion handling
        // This is tricky - you might need to mock user input
        UserInterface ui = uiWithInput("30\nUSD\nAUD\n");
        ui.handleConversion();

        String output = outContent.toString();
        System.out.println(output);

        assertTrue(output.contains("Enter amount:"));
        assertTrue(output.contains("From currency (USD/EUR/GBP/AUD):"));
        assertTrue(output.contains("To currency (USD/EUR/GBP/AUD):"));
        assertTrue(output.contains("Result: 30.00 USD = 39.00 AUD"));

        // Test invalid amount
        outContent.reset();
        ui = uiWithInput("-10\n");
        ui.handleConversion();
        output = outContent.toString();
        assertTrue(output.contains("Invalid amount. Please enter a positive number. (e.g., 50.50)"));

        // Test invalid source currency
        outContent.reset();
        ui = uiWithInput("50\nXYZ\n");
        ui.handleConversion();
        output = outContent.toString();
        assertTrue(output.contains("Invalid source currency. Allowed: USD, EUR, GBP, AUD."));

        // Test invalid target currency
        outContent.reset();
        ui = uiWithInput("50\nUSD\nXYZ\n");
        ui.handleConversion();
        output = outContent.toString();
        assertTrue(output.contains("Invalid target currency. Allowed: USD, EUR, GBP, AUD."));

        //
    }   

    @Test
    public void testStartMethod() {
        // TODO: Test that start method exists

        // Be careful - this might run indefinitely, so test carefully
        UserInterface ui = uiWithInput("2\n1\n20\nUSD\nAUD\nhello\n3\n");
        // 2 show rates
        // 1\n20\nUSD\nAUD calculate amount
        // hello test nagitive case
        // 3 exit
        ui.start();
        assertTrue(outContent.toString().length() > 0);
    }
}
