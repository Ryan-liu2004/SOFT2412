// src/test/java/AppTest.java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests for App class - Student D
 */
public class AppTest {

    @Test
    public void testAppExists() {
        // TODO: Test that App class can be instantiated
        App app = new App(); // even though main() is static, this checks constructor
        assertNotNull(app);
    }

    @Test
    public void testMainMethod() {
        // TODO: Test that main method exists and runs without crashing
        // You might test this by calling App.main(new String[]{})
         // Provide minimal input so start() can exit immediately: choose "3" (Exit)
         byte[] input = "3\n".getBytes();
         ByteArrayInputStream in = new ByteArrayInputStream(input);
 
         // Capture stdout
         ByteArrayOutputStream outContent = new ByteArrayOutputStream();
         PrintStream originalOut = System.out;
         InputStream originalIn = System.in;
 
         System.setIn(in);
         System.setOut(new PrintStream(outContent));
 
         try {
             assertDoesNotThrow(() -> App.main(new String[]{}),
                     "main() should not throw when given minimal exit input");
 
             String output = outContent.toString();
 
             // Basic sanity checks
             assertTrue(output.contains("Currency Converter Starting"),
                     "Expected startup message from App.main()");
             // Optional (if your UI prints this on exit)
             assertTrue(output.toLowerCase().contains("goodbye"),
                     "Expected exit message when selecting option 3");
         } finally {
             // Always restore original streams
             System.setIn(originalIn);
             System.setOut(originalOut);
         }
     }
}

