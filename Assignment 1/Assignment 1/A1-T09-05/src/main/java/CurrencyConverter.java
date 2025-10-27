/**
 * Core currency conversion logic
 *
 * TODO: Implement conversion calculations and exchange rate management
 * You may distribute four of the following methods among your team:
 * - convert
 * - getSupportedCurrencies
 * - getExchangeRate
 * - roundToTwoDecimals
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    public final List<String> c_codes = new LinkedList<>(Arrays.asList("USD", "EUR", "GBP", "AUD"));
    /**
     * Convert amount from one currency to another
     * @param amount amount to convert
     * @param fromCurrency source currency (USD/EUR/GBP/AUD)
     * @param toCurrency target currency (USD/EUR/GBP/AUD)
     * @return converted amount
     */
    public double convert(double amount, String fromCurrency, String toCurrency) {
        // Use hardcoded exchange rates:
        // USD -> EUR: 0.85, USD -> GBP: 0.75, USD -> AUD: 1.30
        
        // Validate currencies
        if (fromCurrency == null || toCurrency == null || 
            c_codes.contains(fromCurrency) == false || c_codes.contains(toCurrency) == false) {
            throw new IllegalArgumentException("Currency codes invalid");
        }

        // Hardcoded exchange rates from USD to other currencies
        Map<String, Double> currency_code = new HashMap<>();
        currency_code.put(c_codes.get(0), 1.0);  // USD
        currency_code.put(c_codes.get(1), 0.85); // EUR
        currency_code.put(c_codes.get(2), 0.75); // GBP
        currency_code.put(c_codes.get(3), 1.30); // AUD

        // Calculate conversion rate
        double rate = roundToTwoDecimals(currency_code.get(toCurrency) / currency_code.get(fromCurrency));
        // Return converted amount rounded to two decimal places
        return roundToTwoDecimals(amount * rate);
    }

    /**
     * Get all supported currencies
     * @return array of currency codes
     */
    public String[] getSupportedCurrencies() {
        // TODO: Return array of supported currencies
        return c_codes.toArray(new String[0]);
    }

    /**
     * Get exchange rate between two currencies
     * @param fromCurrency source currency
     * @param toCurrency target currency
     * @return exchange rate
     */
    public double getExchangeRate(String fromCurrency, String toCurrency) {
        // TODO: Return exchange rate between currencies
        return convert(1, fromCurrency, toCurrency);

    }

    /**
     * Round a double value to two decimal places using standard rounding
     * @param value the value to round
     * @return rounded value
     */
    public double roundToTwoDecimals(double value) {
        // TODO: Round the value to two decimal places
        return Math.round(value * 100) /100.0;// Replace with actual rounding
    }
}
