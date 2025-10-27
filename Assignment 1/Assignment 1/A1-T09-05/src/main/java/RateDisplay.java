import java.util.Arrays;
import java.util.List;

/**
 * Core currency conversion logic
 *
 * TODO: Implement conversion calculations and exchange rate management
 */
public class RateDisplay {
	private final CurrencyConverter converter = new CurrencyConverter();

	/**
	 * Show all exchange rates in a formatted table (including reverse and same-currency)
	*/
	public void showAllRatesTable() {
		// TODO: Display formatted table of exchange rates
		List<String> currencies = Arrays.asList(converter.getSupportedCurrencies());

		// Print header
		System.out.println("Exchange Rates (base = 1 unit)");
		System.out.printf("%8s", "");
		for (String to : currencies) 
			System.out.printf("%8s", to);
		System.out.println();

		// Print each row
		for (String from : currencies) {
			System.out.printf("%8s", from);
			for (String to : currencies) {
				double rate = converter.getExchangeRate(from, to);
				System.out.printf("%8.2f", rate);
			}
			System.out.println();
		}
	}

	/**
	 * Show the exchange rate for a specific currency pair
	*/
	
    public void showRate(String from, String to) {
        if (from == null || to == null) {
            System.out.println("Invalid currency.");
            return;
        }
        String f_code = from.trim().toUpperCase();
        String t_code = to.trim().toUpperCase();
        try {
            double rate = converter.getExchangeRate(f_code, t_code);
            System.out.printf("%s -> %s : %.2f%n", f_code, t_code, rate);
        } catch (IllegalArgumentException ex) {
            System.out.println("Unsupported currency pair: " + f_code + " -> " + t_code);
        }
    }

	/**
	 * Show all rates for all supported currency pairs (including reverse)
	*/
	public void showAllPairs() {
		// TODO: Display all exchange rates for supported currency pairs
		String[] currencies = converter.getSupportedCurrencies();
		for(String from : currencies)
		{
			for(String to : currencies)
			{
				double rate = converter.getExchangeRate(from, to);
				System.out.printf("%s -> %s : %.2f\n", from, to, rate);
			}
		}
		System.out.println();
	}

	/**
	 * Show rates for same-currency conversions (should always be 1.0)
	*/
	public void showSameCurrencyRates() {
		// TODO: Display rates for same-currency conversions
		String[] currencies = converter.getSupportedCurrencies();
		for (String i : currencies) {
			System.out.printf("1.00 %s = 1.00 %s%n", i, i);
		}
	}
}
