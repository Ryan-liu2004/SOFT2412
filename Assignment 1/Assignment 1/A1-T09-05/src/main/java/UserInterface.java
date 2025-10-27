import java.util.Scanner;

/**
 * Console user interface
 *
 * TODO: Implement menu system and user interactions
 * You may divide the following four methods among your team:
 * - start
 * - showMenu
 * - handleConversion
 * - showExchangeRates
 */
public class UserInterface {

    private Scanner scanner;
    private CurrencyConverter converter;
    private DataValidator validator;
    private RateDisplay display;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.converter = new CurrencyConverter();
        this.validator = new DataValidator();
        this.display = new RateDisplay();
    }

    /**
     * Start the main application loop
     */
    public void start() {
        // TODO: Implement main menu loop
        // 1. Show menu
        // 2. Get user choice
        // 3. Handle choice
        // 4. Repeat until exit

        // TODO: Implement menu loop
        while (true) {
            showMenu();
            System.out.print("Enter choice: ");
            String choice = readTrimmed();

            switch (choice) {
                case "1":
                    handleConversion();
                    break;
                case "2":
                    showExchangeRates();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Display main menu options
     */
    public void showMenu() {
        // TODO: Display menu:
        // 1. Convert Currency
        // 2. View Exchange Rates
        // 3. Exit
        System.out.println("=== Currency Converter ===");
        System.out.println("1. Convert Currency");
        System.out.println("2. View Exchange Rates");
        System.out.println("3. Exit");
    }

    /**
     * Handle currency conversion process
     */
    // Helper function to read trimmed input
    private String readTrimmed() {
        String line = scanner.nextLine();
        return line == null ? "" : line.trim();
    }

    public void handleConversion() {
        // 1. Get amount
        System.out.print("\nEnter amount: ");
        String amountStr = readTrimmed();
        if(!validator.isValidAmount(amountStr)) 
        {
            System.out.println("Invalid amount. Please enter a positive number. (e.g., 50.50)");
            return;
        }

        double amount = validator.parseAmount(amountStr); // safe because validated

        // 2. Get source currency
        System.out.print("From currency (USD/EUR/GBP/AUD): ");
        String fromRaw = readTrimmed();
        String from = validator.normalizeCurrency(fromRaw);
        if (!validator.isValidCurrency(from)) 
        {
            System.out.println("Invalid source currency. Allowed: USD, EUR, GBP, AUD.\n");
            return;
        }
        // 3. Get target currency
        System.out.print("To currency (USD/EUR/GBP/AUD): ");
        String toRaw = readTrimmed();
        String to = validator.normalizeCurrency(toRaw);
        if (!validator.isValidCurrency(to)) 
        {
            System.out.println("Invalid target currency. Allowed: USD, EUR, GBP, AUD.\n");
            return;
        }

        // 4. Show result
        try {
            double result = converter.convert(amount, from, to);
            System.out.printf("Result: %.2f %s = %.2f %s%n%n", amount, from, result, to);
            System.out.println("Returning to main menu...\n");
        } 
        catch (IllegalArgumentException e) {
            // Covers unexpected unsupported pairs
            System.out.println("Error: " + e.getMessage() + "\n");
        } catch (Exception e) {
            // Catch-all for any other unexpected errors
            System.out.println("An unexpected error occurred: " + e.getMessage() + "\n");
        }
    }

    /**
     * Display exchange rates table
     */
    public void showExchangeRates() {
        // TODO: Display formatted table of exchange rates
        display.showAllRatesTable();
    }
}
