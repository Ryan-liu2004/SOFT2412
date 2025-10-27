/**
 * Input validation utilities
 *
 * TODO: Implement validation methods for user input
 */
public class DataValidator {

    /**
     * Check if currency code is valid
     * @param currency currency code to validate
     * @return true if valid (USD/EUR/GBP/AUD)
     */
    public boolean isValidCurrency(String currency) {
        // Check if currency is valid (Only uppercase)
        currency = normalizeCurrency(currency);
        return "USD".equals(currency) || 
            "EUR".equals(currency) || 
            "GBP".equals(currency) || 
            "AUD".equals(currency);
    }

    /**
     * Check if amount string is valid
     * @param amountStr string representation of amount
     * @return true if valid positive number
     */
    public boolean isValidAmount(String amountStr) {
        // TODO: Check if string represents positive number
        if (amountStr == null) {
            return false;
        }
        String str = amountStr.trim();
        if (str.isEmpty()) {
            return false;
        }
        try {
            double f = Double.parseDouble(str);
            if (Double.isFinite(f) && f >= 0.0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parse amount string to double
     * @param amountStr string to parse
     * @return parsed amount or 0.0 if invalid
     */
    public double parseAmount(String amountStr) {
        // TODO: Safely parse string to double
        if (isValidAmount(amountStr))
        {
            return Double.parseDouble(amountStr.trim());
        }
        return 0.0;
    }

    /**
     * Normalize currency code (uppercase, trim)
     * @param currency raw currency input
     * @return normalized currency code
     */
    public String normalizeCurrency(String currency) {
        // TODO: Clean up currency input (trim, uppercase)
        if (currency == null) {
            return null;
        }
        return currency.trim().toUpperCase();// Replace with normalization
    }
}
